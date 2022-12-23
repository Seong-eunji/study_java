package threadTest;

public class Thread2 implements Runnable{
	
	@Override
	public void run() {
		// 자원
		for (int i = 0; i < 10; i++) {								// run() 안에 선언된 것을 자원이라고 부름
			System.out.println(Thread.currentThread().getName());	// 이 자원에 접근한 객체의 이름을 가져옴
			try {Thread.sleep(500);} catch (InterruptedException e) {;}
		}
	}
}
