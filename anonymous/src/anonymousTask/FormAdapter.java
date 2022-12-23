package anonymousTask;

// Form의 강제성 소멸

// 무료 이벤트를 진행하고 있는 지점은 판매방식을 구현할 수 없으므로
// 구현하지 못하는 메소드가 발생
// 추상 클래스로 지정받아 미리 구현함으로써 강제성을 없애서
// 원하는 메소드만 골라서 사용할 수 있도록 한다
public abstract class FormAdapter implements Form{
	
	@Override
	public String[] getMenu() {return null;}
	
	@Override
	public void sell(String order) {}
}
