package interfaceTest;

public interface Pet {
	// 인터페이스는 상수만 정의 가능하므로 final이 붙는다.
	// 그렇지만 추상메소드로 인해서 메모리에 할당이 되지 않기 때문에
	// 변수를 메모리에 할당시키기 위해서 static으로 선언해준다.
	final static int eyes = 2; 
	int nose = 1;
	
	public void bang();
	public void giveYourHand();
	public void bite();
	public void sitDown();
	public void waitNow();
	public void getNose();
}
