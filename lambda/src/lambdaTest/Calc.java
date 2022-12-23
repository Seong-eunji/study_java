package lambdaTest;

// 함수형 인터페이스
// 하나의 추상 메소드만 선언 가능
@FunctionalInterface
public interface Calc {
	// 두 정수를 전달받은 후 더하거나 빼서 리턴한다.
	// 더할지 뺄지 몰라서 사용자에게 입력받아야 하므로 추상메소드로 구현함
	public int calc(int number1, int number2);
}
