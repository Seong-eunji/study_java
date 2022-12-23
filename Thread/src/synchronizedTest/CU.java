package synchronizedTest;

public class CU {
	public static void main(String[] args) {
		ATM atm = new ATM();					// 자원
		
		Thread mom = new Thread(atm, "엄마");		// 같은 주소의 필드를 갖고 있는 Runnable 객체를 전달받고 있으므로 두 쓰레드가 자원을 공유함
		Thread son = new Thread(atm, "아들");
		
		mom.start();
		son.start();
	}
}
