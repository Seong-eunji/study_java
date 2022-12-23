package anonymousTest;

public interface Game {
	public void play();		// abstract가 생략되어 있지만 추상 메소드
	public void exit();		// 추상 메소드는 구현이 되지 않았으므로 메모리에 할당되지 않음
}
