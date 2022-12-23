package interfaceTest;

public class Stone extends PetAdapter {
	// Stone은 보기에는 2개의 타입으로 보이지만
	// PetAdapter가 Pet이라는 인터페이스를 상속받고 있기 때문에
	// Stone의 타입은 3개
	// Stone, PetAdapter, Pet
	
	@Override
	public void sitDown() {
		System.out.println("앉는다.");
	}
	
	@Override
	public void waitNow() {
		System.out.println("기다린다.");
	}
	
}
