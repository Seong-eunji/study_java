package lambdaTest;

/*
 *	[실습]
 *	함수형 인터페이스를 선언하고 이름과 성을 전달받는 추상메소드를 선언한다.
 *	클래스를 선언하고 함수형 인터페이스를 매개변수로 받는 static 메소드를 선언한다.
 *	외부에서 구현된 값을 전달받은 후 static 메소드에서 이름과 성을 전달하여 전체 이름을 출력하도록 구현한다.
 *	main메소드에서는 static메소드를 사용하여 전체 이름을 출력한다.
 */

public class PrintNameTest {
// 외부에서 람다식으로 구현한 값을 name으로 전달받기
	public static void printFullName(PrintName name) {			// PrintName 인터페이스를 매개변수로 받음
		System.out.println(name.getFullName("성", "은지"));		// PrintName의 getFullName 메소드를 호출하여 매개변수 성과 이름 전달하고, 성+이름을 String으로 리턴받고 출력
	}

	public static void main(String[] args) {
		// 매개변수 2개를 설정하고, 두 문자열을 연결하도록 구현한다.

		// 같은 클래스 내 다른 메소드를 호출할 때에는 객체화를 해야 하는데
		// 그 이유는 static이 붙은 메소드(보통은 main 메소드)가 먼저 정의되기 때문에
		// static이 없는 메소드는 아직 정의되지 않아 객체화를 해준 후에 메소드 호출이 가능하다.
		// 그러나 사용하려는 메소드와 사용되는 메소드가 모두 static 메소드라면 정의되는 시점이 같기 때문에
		// 객체화 없이 바로 호출하여 사용이 가능하다.
		printFullName((l, f) -> l + f);							// printFullName 메소드를 호출하며 매개변수로 PrintName 타입을 람다식으로 구현하여 전달
	}
}
