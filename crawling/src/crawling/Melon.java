package crawling;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Melon {
	private WebDriver webDriver;
	private String url;
	public static final String WEB_DRIVER_PATH = "C:/chromedriver.exe";
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";

	public Melon() {
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		url = "https://www.melon.com/";
		
		// 처음 url에 접근했을 때에 크롬 페이지를 보이지 않고 바로 url로 넘어가고 싶으면
		// ChromeOptions 객체를 선언하여 addArguments 메소드에 "headless" 전달
		ChromeOptions options = new ChromeOptions();
//      options.addArguments("headless");

		webDriver = new ChromeDriver(options);
		webDriver.get(url);

	}

	public WebDriver selectLyric() {
		Scanner sc = new Scanner(System.in);
		WebElement input = null, form = null;

		List<WebElement> songNumberElements = null;
		List<WebElement> songTitleElements = null;
		List<WebElement> songArtistElements = null;

		ArrayList<String> songNumbers = new ArrayList<String>();
		ArrayList<String> songTitles = new ArrayList<String>();
		ArrayList<String> songArtists = new ArrayList<String>();

		String songNumber = null, songTitle = null, songArtist = null;

		input = webDriver.findElement(By.className("ui-autocomplete-input"));

		System.out.print("노래 제목 > ");
		input.sendKeys(sc.nextLine());		// 입력받은 노래 제목을 전달
		input.sendKeys(Keys.RETURN);		// 노래를 검색하여 엔터
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			;
		}

		form = webDriver.findElement(By.id("frm_searchSong"));		// 이동한 url에서 다시 요소 검색

		songNumberElements = form.findElements(By.cssSelector("td.no div"));
		songTitleElements = form.findElements(By.cssSelector("td.t_left a.fc_gray"));

		for (int i = 0; i < songNumberElements.size(); i++) {
			songNumber = songNumberElements.get(i).getText();
			songTitle = songTitleElements.get(i).getText();

			songNumbers.add(songNumber);
			songTitles.add(songTitle);
		}

		for (WebElement webElement : form.findElements(By.cssSelector("div#artistName"))) {
			songArtistElements = webElement.findElements(By.cssSelector("span a.fc_mgray"));

			songArtist = songArtistElements.get(0).getAttribute("title").split(" - ")[0];
			if (songArtistElements.size() > 1) {
				// Collectors.joining(",") : 각각의 요소를 전달한 연결자로 연결해줌
				songArtist = songArtistElements.stream().map(v -> v.getAttribute("title")).map(v -> v.split(" - ")[0])
						.collect(Collectors.joining(","));
			}
			songArtists.add(songArtist);
		}

		for (int i = 0; i < songNumbers.size(); i++) {
			System.out.println(songNumbers.get(i) + " / " + songTitles.get(i) + " / " + songArtists.get(i));
		}

		return webDriver;
	}

	public void operate() {
		selectLyric();
		webDriver.quit();
	}

	public static void main(String[] args) {
		new Melon().selectLyric().quit();
	}
}
