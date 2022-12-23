package collectionTask;

import java.util.Scanner;

public class Page {
	public static void main(String[] args) {
//		User user = new User();
//		UserField userfield = new UserField();
//		Scanner sc = new Scanner(System.in);
//		
//		String title = "안녕하세요. 환영합니다.";
//		String menuMsg = "1. 회원가입\n2. 로그인\n3. 비밀번호 변경\n4. 나가기";
//		String idMsg = "아이디를 입력하세요 : ", pwMsg = "비밀번호를 입력하세요 : ", nameMsg = "이름을 입력하세요 : ", phMsg = "전화번호를 입력하세요 : ";
//		String id = "", pw = "", name = "", phNumber = "", pin = "";
//		int choice = 0;
//		
//		while (true) {
//			System.out.println(title);
//			System.out.println("======Menu=====");
//			System.out.println(menuMsg);
//			System.out.println("===============");
//			choice = sc.nextInt();
//			if (choice == 4) {
//				break;
//			}
//			
//			switch(choice) {
//			case 1:	// 회원가입
//				System.out.print(idMsg);
//				id = sc.next();
//				
//				System.out.print(nameMsg);
//				name = sc.next();
//				
//				System.out.print(pwMsg);
//				pw = sc.next();
//				pw = userfield.encryption(pw);
//				
//				System.out.print(phMsg);
//				phNumber = sc.next();
//				
//				user = userfield.checkId(id);
//				
//				if(user == null) {
//					userfield.join(new User(name, id, pw, phNumber));
//					System.out.println("가입을 축하합니다!");
//					break;
//				}
//				System.out.println("이미 가입된 계정입니다. 비밀번호 찾기를 이용하세요.");
//				break;
//				
//			case 2:	// 로그인
//				System.out.print(idMsg);
//				id = sc.next();
//				
//				System.out.print(pwMsg);
//				pw = sc.next();
//				pw = userfield.encryption(pw);
//				
//				user = userfield.logIn(id, pw);
//				
//				if(user != null) {
//					System.out.println(user.getName() + "님, 로그인되었습니다.");
//					break;
//				}
//				System.out.println("로그인에 실패하였습니다.");
//				break;
//				
//			case 3:	// 비밀번호
//				System.out.print(idMsg);
//				id = sc.next();
//				
//				user = userfield.checkId(id);
//				pin = userfield.sendSms(user.getPhoneNumber());
//				System.out.print("인증번호를 발송하였습니다.\n인증번호 입력 : ");
//				
//				if(pin.equals(sc.next())) {
//					System.out.print("새로운 " + pwMsg);
//					pw = userfield.encryption(sc.next());
//					user.setPassword(pw);
//					userfield.changePassword(user);
//					System.out.println("비밀번호가 변경되었습니다.");
//					break;
//				}
//				System.out.println("잘못된 인증번호를 입력하셨습니다.");
//				break;
//				
//			default:
//				System.out.println("잘못 입력하셨습니다.");
//				break;
//			}
//		}	
		
		// 강사님 코드
		 User user = new User();								// User 객체 선언
		 UserField userField = new UserField();					// UserField 객체 선언
		 Scanner sc = new Scanner(System.in);
		 String number = null;
	      
//	      입력받은 정보
	     user.setId("hds1234");									// 정보 입력
	     user.setName("한동석");
	     user.setPassword("1234");
	     user.setPhoneNumber("01012341234");
	      
//	      아이디 중복검사 테스트      
	      if(userField.checkId(user.getId()) == null) {			// 리턴값이 null이라면 중복 아이디가 없다는 뜻이므로 회원가입을 해준다.
//	      회원가입 테스트
	         userField.join(user);
//	         userField.users.forEach(System.out::println);
	         
//	         향상된 for문, 빠른 for문, forEach문
	         for (User member : userField.db) {
	            System.out.println(member);
	         }
//	         for (int i = 0; i < UserField.users.size(); i++) {
//	            System.out.println(userField.users.get(i));
//	         }
	         
	      }
	   
//	      로그인 테스트
//	      user = userField.login("hds1234", "1234");
//	      if(user != null) {
//	         System.out.println(user);
//	      }else {
//	         System.out.println("로그인 실패");
//	      }
	      
	      // 비밀번호 변경을 위한 인증번호 발송
	      // 인증번호 발송 전에 아이디 체크
	      user = userField.checkId("hds1234");
	      if(user != null) {
	         number = userField.sendSms(user.getPhoneNumber());
	         System.out.println(number);
	         
	         System.out.print("인증번호 : ");
	         if(number.equals(sc.next())) {
	            System.out.print("비밀번호 : ");
	            user.setPassword(sc.next());
	            userField.changePassword(user);
	            System.out.println(userField.db.get(0));
	         }
	      }
	      
	      if(userField.logIn("hds1234", "5555") != null) {
	         System.out.println("로그인 성공");
	         
	      }else {
	         System.out.println("로그인 실패");
	      }

	}
}
