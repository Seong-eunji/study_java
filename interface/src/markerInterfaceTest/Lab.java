package markerInterfaceTest;

public class Lab {
	// 외부에서 모든 동물을 받아와서
	// 초식동물인지 육식동물인지 그리고 잡식동물인지 구분하는 메소드
	// 초식 동물 : Herbivore
	// 육식 동물 : Carnivore
	public void checkKinds(Animal[] animals) {					// 클래스 배열을 매개변수로 받음
		// 외부에서 Animal 타입의 배열을 전달받고
		// instanceof를 사용하여 종을 검사한다.
		for (int i = 0; i < animals.length; i++) {				// 배열의 길이를 알고 있으므로 for문 사용
			if(animals[i] instanceof HerbivoreMarker) {			// 타입이 HerbivoreMarker일 경우
				System.out.println("초식동물입니다.");				// "초식동물입니다." 출력
			}else if(animals[i] instanceof CarnivoreMarker) {	// 타입이 CarnivoreMarker일 경우
				System.out.println("육식동물입니다.");				// "육식동물입니다." 출력
			}else {												// 타입이 잡식성 동물일 경우
				System.out.println("잡식동물입니다.");				// "잡식동물입니다." 출력
			}
		}
	}
	
	public static void main(String[] args) {
		Lab l = new Lab();				// 메소드 사용을 위해 객체화 선언
		Animal[] animals = {			// 각 클래스를 Animal 타입응로 upCasting하여 배열로 선언
				new Cow(),
				new Bear(),
				new Tiger(),
				new Dog()
		};
		
		l.checkKinds(animals);			// Animal[]을 매개변수로 전달
	}
}
