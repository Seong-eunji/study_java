package anonymousTask;

public class Starbucks {
	public void register(Form form) {					// 등록, 인터페이스 Form을 매개변수로 받아오는 메소드
		String[] menus = form.getMenu();				// Form의 getMenu 메소드를 이용하여 String 배열에 메뉴를 담음
				
		System.out.println("======메뉴======");
		for (int i = 0; i < menus.length; i++) {		// 배열의 길이값을 알고 있으므로 for문 이용하여 출력
			System.out.println(i + 1 + ". " + menus[i]);
		}
		
		if(form instanceof FormAdapter) {				// 이 때, 입력받은 form의 타입이 FormAdapter일 경우
			System.out.println("무료 나눔 행사중");			// 무료 나눔 행사를 진행 중인 지점에 해당하므로
			return;										// 판매방식 메소드를 실행하지 않고 "무료 나눔 행사중" 출력 후 종료 
		}
		form.sell("카푸치노");								// 무료 나눔 행사를 진행하지 않는 일반 지점의 경우
														// 판매방식 메소드 실행
	}
}
