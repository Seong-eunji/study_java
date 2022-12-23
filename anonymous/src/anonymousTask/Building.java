package anonymousTask;

public class Building {
	public static void main(String[] args) {
		// 잠실점 오픈
		// 잠실점은 무료 나눔 행사중이라서 판매 방식을 구현할 수 없다.
		// 본사에서는 전달받은 양식을 검사할 때
		// 무료나눔 행사중인 매장이라면 "무료 나눔 행사중" 출력하기
		
		Starbucks gangnam = new Starbucks();					// 강남점
		Starbucks jamsil = new Starbucks();						// 잠실점

		gangnam.register(new Form() {							// 등록 메소드에 새로운 Form을 선언하여 매개로 전달
																// Form에 아직 구현되지 않은 추상 메소드가 존재하므로 익명 클래스를 열어 구현
													
			@Override
			public void sell(String order) {					// 판매방식 메소드
				String[] menus = getMenu();						// Form의 getMenu 메소드를 이용하여 String 배열에 메뉴를 담음
				for (int i = 0; i < menus.length; i++) {		// 배열의 길이값을 알고 있으므로 for문 이용
					if(order.equals(menus[i])) {				// 매개변수로 전달받은 주문과 일치하는 메뉴가 있을 경우
						System.out.println("판매 완료");			// "판매 완료" 출력
					}
				}
			}
			
			@Override
			public String[] getMenu() {							// 메뉴 메소드
				String[] menus = {"아메리카노", "카푸치노", "캐모마일티"};
				return menus;
			}
		});
		
		// register 메소드의 매개변수는 Form타입이다.
		// FormAdapter는 Form의 자식타입이므로 up casting을 통해 전달한다.
		jamsil.register(new FormAdapter() {						// 잠실점은 무료 나눔 행사 진행 지점으로 판매방식 메소드를 구현할 수 없기 때문에
																// 추상 클래스인 FormAdapter를 이용하여 강제성을 소멸 시킨 후 upCasting하여 매개변수로 전달한다.
			@Override
			public String[] getMenu() {
				String[] menus = {"아메리카노", "카푸치노", "캐모마일티"};
				return menus;
			}
		});
	}
}
