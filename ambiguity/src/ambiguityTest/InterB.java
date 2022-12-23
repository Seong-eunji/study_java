package ambiguityTest;

public interface InterB {
	// abstract가 생략되어 있기 때문에 default를 입력해주어야 한다.
	default void printData() {
		System.out.println("InterB");
	}
}
