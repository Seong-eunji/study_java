package threadTest;

public class ThreadTest {
	public static void main(String[] args) {
		Runnable runner = () -> {					// Runnable 타입, run() 메소드 재정의
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName());
				try {Thread.sleep(300);} catch (Exception e) {;}
			}
		};
		
		Runnable t1 = new Thread2();
		Thread2 t2 = new Thread2();
		
		// Runnable 타입을 매개로 받음
		// t1, t2는 부모가 Runnable이므로 upCasting하여 전달
		// Thread는 Thread2에 재정의한 run에 대해 모르기 떄문에
//		Thread thread1 = new Thread(t1, "!");
//		Thread thread2 = new Thread(t2, "?");
		
		Thread thread1 = new Thread(runner, "!");		// runner라는 객체 전달
		Thread thread2 = new Thread(() -> {				// 람다식을 이용하여 구현
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName());
				try {Thread.sleep(300);} catch (Exception e) {;}
			}
		}, "?");
												
		thread1.start();
		thread2.start();
		
		// join() : 사용한 객체의 쓰레드가 모두 종료되어야 다른 쓰레드가 실행된다.
		//			이미 start()된 쓰레드는 영향을 받지 않는다.
		//			만약 나중에 실행하고자 하는 쓰레드가 있다면, join() 다음에 start()를 실행해야 한다.
		try {
			thread1.join();						// join보다 밑에 선언된 코드들은 무조건 대기
			thread2.join();						// 이미 실행중인 쓰레드에는 영향을 끼치지 않음
		} catch (InterruptedException e) {;}
		
		System.out.println("main 쓰레드 종료");		// main 쓰레드에서 실행하는 코드는 이거 하나
												// Thread1, Thread2, main 총 3개의 쓰레드 진행
		
//		Thread1 t1 = new Thread1("★");
//		Thread1 t2 = new Thread1("♥");
//		
//		t1.start();								// start()는 Thread 클래스 안에 있음
//		t2.start();
		
//		t1.run();
//		t2.run();
	}
}
