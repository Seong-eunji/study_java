package synchronizedTest;

public class ATM implements Runnable {
	
	public int money;
	
	// 값을 초기화할 때는 int money = 10000; 이렇게 쓰지말고
	// 무조건 기본 생성자에 입력하여 초기화 생성자를 호출하여 초기화하도록 해야함
	// 나중에 웹에서 이게 사용되기 때문
	public ATM() {
		this(10000);
	}
	
	public ATM(int money) {
		super();
		this.money = money;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			withdraw(1000);
			try {Thread.sleep(1000);} catch (InterruptedException e) {;}
		}
	}
	
	public synchronized void withdraw(int money) {
		// mutex : 동기화를 걸어줄 자원 객체
//		synchronized (this) {			// 동기화를 사용할 자원의 객체를 mutex로 전달
			this.money -= money;
//		}
		System.out.println(Thread.currentThread().getName() + "이(가)" + money + "원 출금");
		System.out.println("현재 잔액 : " + this.money + "원");
	}
}
