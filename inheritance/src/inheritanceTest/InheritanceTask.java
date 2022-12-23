package inheritanceTest;

class Car {							// 부모 클래스
	// 브랜드, 색상, 가격
	// 열쇠로 시동 킴
	// 열쇠로 시동 끔

	String brand;					// Car 클래스의 필드
	String color;
	int price;

	public Car() {;}				// 기본 생성자

	public Car(String brand, String color, int price) {
									// 초기화 생성자
		this.brand = brand;
		this.color = color;
		this.price = price;
	}

	void engineStart() {			// 엔진 켜짐 메소드
		System.out.println("열쇠로 시동 킴");
	}

	void engineStop() {				// 엔진 꺼짐 메소드
		System.out.println("열쇠로 시동 끔");
	}
}

class SuperCar extends Car {		// 자식 클래스
	// 모드
	// 음성으로 시동 킴
	// 음성으로 시동 끔
	// 지붕 열기
	// 지붕 닫기
									// 적혀있지는 않지만 Car 클래스의 필드도 상속받아 가지고 있음
	String mode;					// SuperCar 클래스의 필드

	// 자식 기본 생성자는 부모의 기본 생성자를 호출한다.
	// 부모에 기본 생성자가 없으면 자식의 기본 생성자는 오류가 발생한다.
	public SuperCar() {				// 기본 생성자
		super();					// 자식 클래스의 기본 생성자 안에는 super();가 생략되어 있는 것과 같으므로
	}								// 호출해올 부모 클래스의 기본 생성자가 없으면 에러 발생

	public SuperCar(String brand, String color, int price, String mode) {
	// 부모의 생성자 중 3개의 값을 전달받는 생성자 호출
									// 초기화 생성자
		super(brand, color, price);	// 부모클래스(Car)의 생성자
									// 자식클래스만 객체화하더라도 자동으로 부모클래스까지 메모리에 할당되도록 해줌 
		this.mode = mode;
	}
	
	@Override
	void engineStart() {			// Car 클래스의 메소드를 오버라이딩
		super.engineStart();		// super는 부모클래스의 이름을 대신하므로 Car의 engineStart 메소드를 가져옴
		System.out.println("음성으로 시동 킴");
	}
	
	@Override
	void engineStop() {				// Car 클래스의 메소드를 오버라이딩										
		System.out.println("음성으로 시동 끔");
		super.engineStop();			// super는 부모클래스의 이름을 대신하므로 Car의 engineStop 메소드를 가져옴
									// 이 super는 생성자가 아니고 메소드를 가져오는 것이기 때문에 꼭 상단에 적어줄 필요 없음
	}
	
	void openRoof() {				// 지붕 열기 메소드
		System.out.println("지붕 열기");
	}
	
	void closeRoof() {				// 지붕 닫기 메소드
		System.out.println("지붕 닫기");
	}

}

public class InheritanceTask {
	public static void main(String[] args) {
		// SuperCar 클래스 객체화
		// SuperCar 클래스만 객체화했지만 SuperCar의 초기화생성자 안에 super();가 선언되어 있으므로
		// 부모클래스인 Car 클래스가 먼저 메모리에 할당된다.
		SuperCar rollsroyce = new SuperCar("롤스로이스", "하얀색과 검정색", 55000, "Sports");
		
		rollsroyce.engineStart();
		rollsroyce.openRoof();
		rollsroyce.closeRoof();
		rollsroyce.engineStop();
	}
}
