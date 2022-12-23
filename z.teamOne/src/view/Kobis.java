package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dao.TicketingDAO;
import dao.WebConnecter;
import vo.TicketingVO;

public class Kobis {

	private WebDriver driver;

	public static String urlBoxOffice = "https://www.kobis.or.kr/kobis/business/stat/boxs/findFormerBoxOfficeList.do";
	public static String urlThreaterSchedule = "https://www.kobis.or.kr/kobis/business/mast/thea/findTheaterSchedule.do";
	public static String urlSearch = "https://www.kobis.or.kr/kobis/business/main/main.do";
	public static String urlAdvanceTicketSales = "https://kobis.or.kr/kobis/business/stat/boxs/findRealTicketList.do";
	
	public static ArrayList<String> movies = new ArrayList<String>();
	public static ArrayList<String> informations = new ArrayList<String>();

	WebConnecter webConnecter = new WebConnecter();
	TicketingDAO ticketingDAO = new TicketingDAO();
	Scanner sc = new Scanner(System.in);
	Kiosk kiosk = new Kiosk();

	// 역대 영화 크롤링
	public void crawlingGenre() {
		driver = WebConnecter.getConnects();

		String selector = "";
		int count = 50;

		driver.get(urlBoxOffice);
		try {Thread.sleep(2000);} catch (InterruptedException e) {;}

		for (WebElement webElement : driver.findElements(By.cssSelector("#td_movie a"))) {
			movies.add(webElement.getAttribute("title"));
		}

		for (int i = 0; i < count; i++) {
			selector = "tr#tr_" + i + " td#td_movie > span > a";
			driver.findElement(By.cssSelector(selector)).click();

			informations.add(driver.findElement(By.cssSelector("div.ovf dd:nth-of-type(4)")).getText());
			
			driver.findElement(By.cssSelector("a.close")).click();
		}
		
		driver.quit();
	}
	
	// 영화 추천
	public void recommendGenre() {
		ArrayList<String> filters = new ArrayList<>(
				Arrays.asList("#극적인", "#역동적인", "#스릴넘치는", "#설레는", "#긴장되는", "#환상적인", "#고전적인", "#재밌는", "#귀여운",
							"#무서운", "#흥미진진한", "#따스한", "#기이한", "#음악적인", "#아이와함께", "#12세이상", "#15세이상", "#한국영화"));
		HashMap<Integer, String> genres = new HashMap<>() {{
			put(1, "드라마"); put(2, "액션"); put(3, "스릴러"); put(4, "멜로/로맨스"); put(5, "범죄"); put(6, "판타지");
			put(7, "사극"); put(8, "코미디"); put(9, "애니메이션"); put(10, "공포(호러)"); put(11, "어드벤처"); put(12, "가족");
			put(13, "미스터리"); put(14, "뮤지컬"); put(15, "전체관람가"); put(16, "12세이상관람가"); put(17, "15세이상관람가"); put(18, "한국");
			}};
		
		String selectGenre = "";
		String[] arrSelect = null;
		int i = 0;
		
		for (String filter : filters) {
			System.out.print(++i + ". " + filter + "\t\t");
			if(i % 3 == 0) {System.out.println();}
		}
		
		System.out.println();
		System.out.print("추천받고 싶으신 태그를 모두 선택해주세요 : ");
		selectGenre = sc.nextLine();
		arrSelect = selectGenre.split(" ");
		
		for (int k = 0; k < informations.size(); k++) {
			int index = 0;
			boolean check = false;
			
			while(index < arrSelect.length) {
				check = informations.get(k).contains(genres.get(Integer.valueOf(arrSelect[index])));
				if(!check) {break;}
				index++;
			}
			if(!check) {continue;}
			System.out.println("<" + movies.get(k) + ">");
			System.out.println(informations.get(k));
		}
	}

	// 예매하기
	public TicketingVO reserveTicket(TicketingVO ticketingVO) {
		String[] seats = new String[]{"A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3"};
		
		driver = WebConnecter.getConnects();
		
		ArrayList<String> reservedSeats = null;
		
		ArrayList<String> cities = new ArrayList<String>();
		ArrayList<String> locations = new ArrayList<String>();
		ArrayList<String> threaters = new ArrayList<String>();
		ArrayList<String> threaterNumbers = new ArrayList<String>();
		ArrayList<String> movieNames = new ArrayList<String>();
		ArrayList<String> times = new ArrayList<String>();
		
		String selector = "";
		int seq = 0, choice = 0;
		boolean input = false;

		driver.get(urlThreaterSchedule);
		try {Thread.sleep(2000);} catch (InterruptedException e) {;}
		
		// 광역
		for (WebElement webElement : driver.findElements(By.cssSelector("div.fl.step1.on > ul > li"))) {
			System.out.print(++seq + ". ");
			System.out.println(webElement.getText());
			cities.add(webElement.getText());
		}

		System.out.print("원하는 지역 선택하세요 : ");
		while(!input) {
			choice = sc.nextInt();
			if(choice > 0 && choice < cities.size() + 1) {
				input = !input;
			}			
		}
		System.out.println("------------------------");

		selector = "div.fl.step1.on ul li:nth-child(" + choice + ") label";
		driver.findElement(By.cssSelector(selector)).click();
		try {Thread.sleep(2000);} catch (InterruptedException e) {;}

		// 기초
		seq = 0;
		for (WebElement webElement : driver.findElements(By.cssSelector("#sBasareaCd li label"))) {
			System.out.print(++seq + ". ");
			System.out.println(webElement.getText());
			locations.add(webElement.getText());
		}

		System.out.print("원하는 지역을 선택하세요 : ");
		input = !input;
		while(!input) {
			choice = sc.nextInt();
			if(choice > 0 && choice < locations.size() + 1) {
				input = !input;
			}			
		}
		System.out.println("------------------------");

		selector = "#sBasareaCd li:nth-child(" + choice + ") label";
		driver.findElement(By.cssSelector(selector)).click();
		try {Thread.sleep(2000);} catch (InterruptedException e) {;}

		// 영화관
		try {
			seq = 0;
			for (WebElement webElement : driver.findElements(By.cssSelector("ul#sTheaCd li label"))) {
				System.out.print(++seq + ". ");
				System.out.println(webElement.getText());
				threaters.add(webElement.getText());
			}
		} catch (NoSuchElementException e) {
			System.out.println("※해당 지역에 영화관이 없습니다.※");
			return null;
		} catch (Exception ee) {
			System.out.println("※해당 지역에 영화관이 없습니다.※");
			return null;
		}
		
		if(threaters.size() == 0) {
			System.out.println("※해당 지역에 영화관이 없습니다.※");
			return null;
		}
		
		System.out.print("원하는 영화관을 선택하세요 : ");
		input = !input;
		while(!input) {
			choice = sc.nextInt();
			if(choice > 0 && choice < threaters.size() + 1) {
				input = !input;
			}			
		}
		System.out.println("------------------------");
		ticketingVO.setTicketingTheater(threaters.get(choice - 1));

		selector = "ul#sTheaCd li:nth-child(" + choice + ") label";
		driver.findElement(By.cssSelector(selector)).click();
		try {Thread.sleep(2000);} catch (InterruptedException e) {;}

		// 상영관, 영화 이름
		try {
			seq = 0;
			for (WebElement webElement : driver.findElements(By.cssSelector("ul#schedule li div.tit"))) {
				System.out.print("======" + ++seq + ". =======\n");
				System.out.println(webElement.getText());
				System.out.println("=================");

				threaterNumbers.add(webElement.findElement(By.cssSelector("span.screen")).getText());
				movieNames.add(webElement.findElement(By.cssSelector("a")).getText());
				}
			} catch (NoSuchElementException e) {
				System.out.println("※다른 영화관을 이용해주세요※");
				return null;
			} catch (Exception ee) {
				System.out.println("※다른 영화관을 이용해주세요※");
				return null;
			}
		
		System.out.print("예매를 원하는 영화를 선택하세요 : ");
		input = !input;
		while(!input) {
			choice = sc.nextInt();
			if(choice > 0 && choice < movieNames.size() + 1) {
				input = !input;
			}			
		}
		System.out.println("------------------------");
		ticketingVO.setTicketingTheaterNumber(threaterNumbers.get(choice - 1));
		ticketingVO.setTicketingMovie(movieNames.get(choice - 1));

		// 영화 시간
		for (WebElement webElement : driver.findElements(By.cssSelector("ul#schedule li:nth-child(" + choice + ") div.times label"))) {
			times.add(webElement.getText());
		}
		for (int i = 0; i < times.size(); i++) {
			System.out.print(i + 1 + "." + "[" + times.get(i) + "]" + "  ");
		}

		System.out.println();
		System.out.print("원하는 상영시간을 선택하세요 : ");
		input = !input;
		while(!input) {
			choice = sc.nextInt();
			if(choice > 0 && choice < times.size() + 1) {
				input = !input;
			}			
		}
		System.out.println("------------------------");
		ticketingVO.setTicketingTime(times.get(choice - 1));
		
		// 좌석
		reservedSeats = ticketingDAO.getSeats(ticketingVO);
		
		seq = 0;
		if (reservedSeats.size() != 0) {
			for (String reservedSeat : reservedSeats) {
				for (int j = 0; j < seats.length; j++) {
					System.out.print(++seq);
					if (reservedSeat.equals(seats[j])) {
						System.out.print(".[■" + seats[j] + "]\t\t");
					} else {
						System.out.print(".[□" + seats[j] + "]\t\t");
					}
					if(seq % 3 == 0) {System.out.println();}
				}
			} 
		} else {
			for (int i = 0; i < seats.length; i++) {
				System.out.print(++seq);
				System.out.print(".[□" + seats[i] + "]\t\t");
				if(seq % 3 == 0) {System.out.println();}
			}
		}
		
		System.out.println();
		System.out.print("원하시는 좌석을 선택해주세요 : ");
		input = !input;
		while(!input) {
			choice = sc.nextInt();
			if(choice > 0 && choice < seats.length + 1) {
				input = !input;
			}			
		}
		ticketingVO.setTicketingSeat(seats[choice - 1]);
		
		driver.quit();
		return ticketingVO;
	}
	
	// 영화정보 검색
	public void searchMovie() {
		driver = WebConnecter.getConnects();
		Scanner sc = new Scanner(System.in);
		WebElement input = null, form = null;

		List<WebElement> movieTitleElements = null;
		ArrayList<String> movieTitles = new ArrayList<String>();

		String movieTitle = null;
		
		driver.get(urlSearch);
		input = driver.findElement(By.id("inp_solrSearch"));

		System.out.print("영화 제목 > ");
		input.sendKeys(sc.nextLine());
		input.sendKeys(Keys.RETURN);
		try {Thread.sleep(1000);} catch (InterruptedException e) {;}

		form = driver.findElement(By.className("tbl_comm"));
		movieTitleElements = form.findElements(By.cssSelector("tr td"));

		for (WebElement webElement : movieTitleElements) {
			movieTitle = webElement.getText();
			movieTitles.add(movieTitle);
		}
		
		if(movieTitles.size() != 0) {
			for (int i = 0; i < movieTitles.size(); i++) {
				 if(movieTitles.get(i).equals("")) {
	                  System.out.print(("정보없음"));
	               }
				System.out.print(movieTitles.get(i) + " | ");
				if((i+1) % 10 == 0 && i != 0) {
					System.out.println();
					System.out.println();
				}
			}			
		}
	}
	
	// 실시간 예매 순위
	public void advanceTicketSales() {
		driver = WebConnecter.getConnects();
		
		WebElement form = null;
        
        List<WebElement> NumberElement = null;
        List<WebElement> TagElement = null;
        
        ArrayList<String> Tag = new ArrayList<String>();
        ArrayList<String> advanceTicketSales = new ArrayList<String>();
        
        String Numbers = null, NameTags = null;
        
        driver.get(urlAdvanceTicketSales);
     
		TagElement = driver.findElements(By.cssSelector("div.rst_sch th"));
		for (WebElement webElement : TagElement) {

			Numbers = webElement.getText();
			Tag.add(Numbers);
		}

		System.out.println(Tag);

		NumberElement = driver.findElements(By.cssSelector("tr#tr_ td"));
		for (WebElement webElement : NumberElement) {

			Numbers = webElement.getText();
			advanceTicketSales.add(Numbers);
		}

		for (int i = 0; i < 400; i++) {
			if (i % 8 == 0) {
				System.out.println("\n");
			}

			if (advanceTicketSales.get(i).equals("")) {
				System.out.print("정보없음");
			}
			System.out.print(advanceTicketSales.get(i) + " | ");
		}
		System.out.println();
	}
}
