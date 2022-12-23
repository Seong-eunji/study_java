package lambdaTest;

public class LambdaTest{
	public static void main(String[] args) {
		
		
		// 선언
		// 구현되어 할당된 필드의 주소값을 추상메소드에 리턴함
		// 아래의 생성자와 같은 코드이나 람다식으로 한 줄로 표현
		// 람다식 통째로 값으로 볼줄 알아야 함(주소값을 리턴!)
		// 람다식의 타입은 이 추상메소드가 선언되어 있는 인터페이스의 타입
		// 값과 저장공간은 타입이 같아야하므로 lambdaInter타입
		LambdaInter lambdaInter1 = (number) -> number % 10 == 0;
		
		// 선언
		// 생성자이므로 구현되어 할당된 필드의 주소값을 추상메소드에 리턴함	
		// 이 통째로 값으로 볼줄 알아야 함
		// 값과 저장공간은 타입이 같아야하므로 lambdaInter
		
		// 재사용하기 쉽도록 객체를 생성하여 생성자가 할당한 주소값을 담아주는 것이지
		// 생성자 호출만 하는 것도 가능! 낯설다고 헷갈리지 말기!
		new LambdaInter() {											// 생성자를 호출하고 메모리에 할당하기 위해서 익명 클래스 사용

			@Override
			public boolean checkMultipleOf10(int number) {			// 구현되지 않은 메소드(추상 메소드)를 구현
				return number % 10 == 0;
			}
		};
		
		boolean result1 = lambdaInter1.checkMultipleOf10(11);		// false, LambdaInter1의 checkMultipleOf10의 결과값을 담음
		System.out.println(result1);								// 13줄에 구현되어 있는 람다식으로 구현된 메소드의 결과
		
		LambdaInter lambdaInter2 = (n) -> {							// 람다식을 이용하여 LambdaInter2의 checkMultipleOf10 메소드 구현 및 메모리 할당
			System.out.println("10의 배수 검사");
			return n % 10 == 0;
		};
		boolean result2 = lambdaInter2.checkMultipleOf10(20);		// true, LambdaInter2의 checkMultipleOf10의 결과값을 담음
		System.out.println(result2);
		
	}
}
