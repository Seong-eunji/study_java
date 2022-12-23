package lambdaTest;

// 함수형 인터페이스
// 하나의 추상 메소드만 선언 가능
@FunctionalInterface
public interface OperCheck {
	// 전체 식을 전달받아서 연산자만 쏙쏙 분리해주는 메소드
	public String[] checkOper(String expression);
}
