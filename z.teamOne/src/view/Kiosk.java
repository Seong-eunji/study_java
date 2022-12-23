package view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import dao.TicketingDAO;
import dao.UserDAO;
import vo.TicketingVO;
import vo.UserVO;

public class Kiosk {
	
	public static void main(String[] args) {
	    Kobis kobis = new Kobis();
	    TicketingDAO ticketingDAO = new TicketingDAO();
	    UserDAO userDAO = new UserDAO();
	    Scanner sc = new Scanner(System.in);
	    
	    String title = "안녕하세요 환영합니다";
	    String menuFirst = "1. 회원가입\n2. 로그인\n3. 실시간 예매율 순위\n4. 영화 검색\n5. 추천 영화\n6. 나가기";
	    String menuSecond = "1. 예매하기\n2. 예매 조회\n3. 예매 취소\n4. 실시간 예매율 순위\n5. 영화 검색\n6. 추천 영화\n7. 마이페이지\n8. 비밀번호 변경\n9. 로그아웃\n10. 회원 탈퇴";
	    String userId = null, userPassword = null, userPhone = null, userName = null;
	    int firstChoice = 0, secondChoice = 0, temp = 0;
	    boolean deleted = false;
	    
	    while(true) {
	    	System.out.println("=================");
	    	System.out.println(title);
	    	System.out.println(menuFirst);
	    	System.out.println("=================");
	    	try {
				firstChoice = sc.nextInt();
			} catch (InputMismatchException e1) {
				System.out.println("잘못 입력하세요.");
				break;
			}
	    	
	    	if(firstChoice == 6) {
	    		System.out.println("안녕히 가세요.");
	    		break;
	    	}
	    	
	    	switch(firstChoice) {
	    	// 회원가입
	    	case 1:
	    		UserVO userVO = new UserVO();
	    		
	    		System.out.println("<회원가입>");
	    		System.out.print("아이디 : ");
	    		userId = sc.next();
	    		if(userDAO.checkId(userId)) {
	    			userVO.setUserId(userId);
	    			System.out.println("[사용 가능한 아이디입니다.]");
	    		} else {
	    			System.out.println("[중복된 아이디입니다.]");
	    			break;
	    		}
	    		
	    		System.out.print("비밀번호 : ");
	    		userVO.setUserPassword(sc.next());
	    		
	    		System.out.print("이름 : ");
	    		userVO.setUserName(sc.next());
	    		
	    		System.out.print("전화번호 : ");
	    		userPhone = sc.next();
	    		if(userDAO.checkPhone(userPhone)) {
	    			userVO.setUserPhone(userPhone);;
	    			System.out.println("[중복되지 않은 전화번호입니다.]");
	    		} else {
	    			System.out.println("[중복된 전화번호입니다.]");
	    			break;
	    		}
	    		
	    		userDAO.join(userVO);
	    		System.out.println(userVO.getUserName() + "님! 가입을 환영합니다.");
	    		break;

	    	// 로그인
	    	case 2:	    		
	    		UserVO loginedUser = new UserVO();
	    		System.out.println("<로그인>");
	    		System.out.print("아이디 : ");
	    		userId = sc.next();
	    		
	    		System.out.print("비밀번호 : ");
	    		userPassword = sc.next();
	    		loginedUser = userDAO.login(userId, userPassword);
	    		
	    		if(loginedUser.getUserId() != null) {
	    			while(true) {
	    		    	System.out.println("==================");
	    		    	System.out.println(title);
	    		    	System.out.println(menuSecond);
	    		    	System.out.println("=================");
	    		    	try {
							secondChoice = sc.nextInt();
						} catch (InputMismatchException e2) {
							System.out.println("이전 메뉴로 돌아갑니다.");
							break;
						}
	    		    	if(secondChoice == 9) {
	    		    		System.out.println("안녕히 가세요.");
	    		    		break;
	    		    	}
	    		    	
	    		    	switch(secondChoice) {
	    		    	// 예매하기
	    		    	case 1:
	    		    		TicketingVO ticketingVO = new TicketingVO();
	    		    		String curTime = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
	    		    		
	    		    		ticketingVO.setUserPhone(loginedUser.getUserPhone());
	    		    		ticketingVO.setTicketingNumber(curTime);
	    		    		ticketingVO = kobis.reserveTicket(ticketingVO);
	    		    		
	    		    		if(ticketingDAO.checkSeat(ticketingVO)) {
	    		    			System.out.println("이미 예매된 좌석입니다.");
	    		    			break;
	    		    		}
	    		    		
	    		    		if(ticketingVO != null) {
	    		    			ticketingDAO.ticketing(ticketingVO);
	    		    			System.out.println("예매되었습니다.");
	    		    			System.out.println(ticketingVO.toString());
	    		    			break;	    		    			
	    		    		} else {break;}
	    		    	
	    		    	// 예매조회
	    		    	case 2:
	    		    		ArrayList<TicketingVO> reservedMovies = ticketingDAO.getTicketingList(loginedUser.getUserPhone());
	    		    		
	    		    		System.out.println("===== 예매 영화 내역 =====");
	    		    		if(reservedMovies.size() == 0) {
	    		    			System.out.println("예매 내역이 없습니다.");
	    		    			System.out.println("======================");
	    		    			
	    		    		}
	    		    		for (TicketingVO movie : reservedMovies) {
								System.out.println(movie.toString());
								System.out.println("======================");
							}
	    		    		break;
	    		    	
	    		    	// 예매취소
	    		    	case 3:
	    		    		reservedMovies = ticketingDAO.getTicketingList(loginedUser.getUserPhone());
	    		    		String movieNumber = null;
	    		    		int index = 0;
	    		    		
	    		    		System.out.println("===== 예매 영화 내역 =====");
	    		    		if(reservedMovies.size() == 0) {
	    		    			System.out.println("예매 내역이 없습니다.");
	    		    			System.out.println("======================");
	    		    			break;
	    		    		}

	    		    		for (TicketingVO movie : reservedMovies) {
	    		    			System.out.print(++index + ". ");
								System.out.println(movie.toString());
								System.out.println("======================");
							}
	    		    		
	    		    		System.out.println("삭제할 영화를 선택하세요 : ");
	    		    		index = sc.nextInt();
	    		    		try {movieNumber = reservedMovies.get(index - 1).getTicketingNumber();} catch (IndexOutOfBoundsException e) {
	                             System.out.println("잘못된 번호입니다.");
	                             break;
	    		    		}
	    		    		ticketingDAO.deleteTicketing(movieNumber, loginedUser.getUserPhone());
	    		    		
	    		    		System.out.println("영화가 취소되었습니다.");
	    		    		break;
	    		    	
	    		    	// 실시간 예매 순위
	    		    	case 4:
	    		    		kobis.advanceTicketSales();
	    		    		break;
	    		    		
	    		    	// 영화검색
	    		    	case 5:
	    		    		kobis.searchMovie();
	    		    		break;
	    		    	
	    		    	// 추천영화
	    		    	case 6: 
	    		    		if(kobis.movies.size() == 0) {
	    		    			kobis.crawlingGenre();	    			
	    		    		}
	    		    		kobis.recommendGenre();
	    		    		break;
	    		    	
	    		    	// 마이페이지
	    		    	case 7:
	    		    		System.out.println(loginedUser.toString());
	    		    		break;
	    		    	
	    		    	// 비밀번호 변경
	    		    	case 8:
	    		    		System.out.print("변경할 비밀번호를 입력하세요 : ");
	    		    		userPassword = sc.next();
	    		    		
	    		    		loginedUser.setUserPassword(userPassword);
	    		    		
	    		    		if(userDAO.changePassword(loginedUser)) {
	    		    			System.out.println("비밀번호 변경을 완료했습니다.");
	    		    		} else {
	    		    			System.out.println("비밀번호 변경에 실패했습니다.");
	    		    		}
	    		    		break;
	    		    		
	    		    	// 회원탈퇴
	    		    	case 10:
	    		    		if(userDAO.deleteUser(loginedUser.getUserPhone())) {
	    		    			System.out.println("탈퇴되었습니다.");
	    		    			deleted = !deleted;
	    		    		}
	    		    		if(deleted) {break;}
	    		    		break;
	    		    		
	    		    	default:
	    		    		break;
	    		    	}
	    		    	if(deleted) {break;}
	    			}
	    		} else {
	    			System.out.println("아이디 또는 비밀번호 정보가 일치하지 않습니다.");
	    		}
	    		break;

	    	// 실시간 예매율 순위
	    	case 3:
	    		kobis.advanceTicketSales();
	    		break;
	    		
	    	// 영화 검색
	    	case 4:
	    		kobis.searchMovie();
	    		break;

	    	// 추천 영화
	    	case 5:
	    		if(kobis.movies.size() == 0) {
	    			kobis.crawlingGenre();
	    		}
	    		kobis.recommendGenre();
	    		if(kobis.informations.size() == 0) {
	    			System.out.println("선택하신 태그에 해당하는 영화가 없습니다.");
	    		}
	    		break;

	    	default:
	    		System.out.println("잘못 선택하셨습니다.");
	    	}
	    }
	}
}







