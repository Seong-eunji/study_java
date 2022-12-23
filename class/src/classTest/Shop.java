package classTest;

import java.util.Scanner;

class SuperCar {
	// 브랜드, 색상, 가격 (인스턴스 변수)
	String brand;
	String color;
	int price;
	String password;
	
	// 엔진 상태, 외부에서 전달받는 값이 아닌, 내부적으로 사용되는 변수
	// boolean의 초기값은 false
	boolean check;
	
	// 기본 생성자
	public SuperCar() {
		// 초기 비밀번호를 "0000"로 설정한다.
		password = "0000";
	}
	
	// 오버로딩
	public SuperCar(String password) {
		this.password = password;
	}
	
	public SuperCar(String brand, String color, int price, String password) {
		super();
		this.brand = brand;
		this.color = color;
		this.price = price;
		this.password = password;
	}
	
	// 시동 켜기
	// 시동의 상태를 확인하고
	// 시동이 꺼져있다면, "시동 켜기" 출력
	// 이미 시동이 켜져있다면, "시동이 이미 켜져있습니다" 출력
//	String engineStart() {
//		String engineState = "";
//		if(!check) { // boolean은 그 자체로 조건식이기 때문에 check == false 이런식으로 쓰지 않음
//			check = true;
//			engineState = "시동 켜기";
//		} else {
//			engineState = "시동이 이미 켜져있습니다.";
//		}
//		return engineState;
//	}
	
	// 강사님 코드
	boolean engineStart() {
		// false일 때 true로 바뀌면서 if문 안에 있는 문장을 실행한다.
		// check가 false일 때 들어옴
		// boolean은 false가 초기값, 따라서 !check는 true가 되면서 if문 안의 코드 실행
		if(!check) {		
			// 시동이 꺼져있다면, 시동을 켜준다(check를 true로 바꿔준다)
			check = true;
			// 시동 켜기 성공
			return true;
		}
		// 시동 켜기 실패
		return false;
	}
	
	
	// 시동 끄기
	// 시동의 상태를 확인하고
	// 시동이 켜져있다면, "시동 끄기" 출력
	// 이미 시동이 꺼져있다면, "시동이 이미 꺼져있습니다" 출력
//	String engineStop() {
//		String engineState = "";
//		if(check) {
//			check = !check;
//			engineState = "시동 끄기";
//		} else {
//			engineState = "시동이 이미 꺼져있습니다.";
//		}
//		return engineState;
//	}
	
	// 강사님 코드
	boolean engineStop() {
		// check가 true일 경우 들어옴
		// 시동이 켜져 있을 때
		if(check) {
			// 시동 끄기
			check = false;
			// 시동 끄기 성공
			return true;
		}
		// 시동 끄기 실패
		return false;
	}
	
	// [심화]
	// 시동을 켜기 위해서는 비밀번호 4자리를 입력해야 한다.
	// 입력한 비밀번호가 3회 연속(누적 아님) 실패하면 "경찰 출동" 메세지를 출력한다.
	// 시동 여부를 먼저 확인하기
//	String checkPassword(String password) {
//		String result = "초기화값";
//		int error = 0;
//		
//		if(check) {
//			result = "시동이 이미 켜져있습니다.";
//			return result;
//		}
//		
//		while(!check) {
//			if(password.equals(this.password)) {
//				check = true;
//				result = "시동 켜기";
//				error = 0;
//			} else {
//				error++;
//				result = "틀렸습니다.";
//				if(error >= 3) {
//					error = 0;
//					result = "경찰 출동";
//					return result;
//				}
//			}		
//		}
//		return result;
//	}
	
	// 강사님 코드
	// 외부에서 사용자가 입력한 비밀번호를 매개변수로 전달받는다.
	boolean checkPassword(String password) {
		// this.password : 자동차 비밀번호
		// password : 입력받은 비밀번호
		// 두 개를 비교해서 일치하면 true, 일치하지 않으면 false
		return this.password.equals(password);
	}
}

public class Shop {
	public static void main(String[] args) {
//		SuperCar scar = new SuperCar("", "", 0, 1234);
//		Scanner sc = new Scanner(System.in);
//		int password = 0;
//		
//		System.out.println(scar.engineStart());
//		System.out.println(scar.engineStart());
//		System.out.println(scar.engineStop());
//		System.out.println(scar.engineStop());
//		
//		System.out.println(scar.engineStart());
//		
//		do {
//			System.out.print("시동 비밀번호 입력하세요 : ");
//			password = sc.nextInt();
//			
//			System.out.println(scar.checkPassword(password));
//		} while(!scar.check); // 맞는 조건식을 못 찾음ㅠㅠ
		
		
		// 강사님 코드
		// 객체화(instance)
		SuperCar ferrari = new SuperCar();
		
		String msg = "1. 시동켜기\n2. 시동끄기", pwMsg = "비밀번호 : ", pw = null;
		Scanner sc = new Scanner(System.in);
		// choice : 사용자가 입력한 메뉴 번호
		// count : 비밀번호 오류 횟수
		int choice = 0, count = 0;
		
		// FLAG(깃발) : 해당 영역에서 연산된 결과를 다른 영역에서 사용하고자 할 때
		// 올라가서 깃발을 꽂아놓는 것같은 의미
		boolean stopedEngine = false;	// 시동을 끄는 메소드의 성공여부 확인변수
		
		do {
			System.out.println(msg);
			choice = sc.nextInt();	// 시동을 켤지 끌지 보기 선택
			
			switch(choice) {
			case 1 : // 시동 켜기
				// 시동이 꺼졌을 때 들어옴
				// check가 false인 상태, 시동이 꺼져있음, !check가 참이 되므로 아래 코드 실행
				if(!ferrari.check) {
					System.out.println(pwMsg);			// 비밀번호 입력요구메세지 출력
					pw = sc.next();						// 비밀번호 입력
					// 비밀번호가 일치할 때 들어옴
					if(ferrari.checkPassword(pw)) {		// 입력받은 비밀번호가 맞는지 확인
						ferrari.engineStart();			// 맞으면 시동 켜기
						// 시동 켜기에 성공했다면 오류 횟수를 초기화 해준다.
						count = 0;						// 오류 횟수 초기화
						System.out.println("시동 켜짐");	// 시동 켜짐 메세지 출력
					} else {
						count++;						// 비밀번호 오류 횟수 증가					
						System.out.println("비밀번호 오류 " + count + "회");
						// 연속 3회 오류 시 들어옴
						if(count == 3) {				// 비밀번호 오류가 3회가 되면 경찰 출동 메세지 출력
							System.out.println("경찰 출동");
						}
					}
					break;								// if문을 빠져나가고 메세지 출력
				}
				System.out.println("이미 시동이 켜져있습니다.");
				break;
			
			case 2:	// 시동 끄기
				// 시동 끄기를 시도한 결과를 stopedEngine Flag에 담아준다.
				if(stopedEngine = ferrari.engineStop()) {	// 시동을 끄는 메소드가 true를 반환, 시동이 켜져있던 걸 끔
					System.out.println("시동 꺼짐");			// 시동 꺼짐 메세지 출력 후 break
					break;
				}
				System.out.println("이미 시동이 꺼져있습니다.");	// 시동을 끄는 메소드가 false를 반환, 이미 꺼져있음
				break;
			}
		// stopedEngine은 시동이 켜진 후 시동을 끄기에 성공하면 true이다.
		// 시동 끄기에 성공하면 반복문을 탈출한다.
		// count는 오류횟수이다. count가 3이라는 뜻은, 연속 3회 비밀번호 오류인 경우이다.
		// 따라서 count가 3일 때 while문을 탈출해야 하고, count가 3일 때 조건식이 false가 되어야 한다.
		}while(!stopedEngine && count != 3);	// 둘 중 하나라도 false면 탈출
												// 시동이 켜져있고(시동 끄는 메소드가 실패, false 반환) count가 3이 아닌 동안만 반복
												// 시동이 꺼졌거나(시동을 끄는 메소드가 성공, true 반환) count가 3이 되면 탈출
	}
}
