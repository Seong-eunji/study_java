package threadTest1;

public class Zoo {
	public static void main(String[] args) {
//		[실습]
//		동석이네 동물원에는 3마리의 동물이 있다.
//		각 동물은 울음 소리가 다르고 2마리의 동물은 동시에 운다.
//		나머지 1마리 동물은 2마리 동물이 모두 울고 나서 마지막에 운다.
//
//		package명은 threadTest1으로 만들고 클래스는 2개만 선언한다.
//		하나의 클래스에는 main 쓰레드가 있다.
//		Runnable 인터페이스로 멀티 쓰레드를 구현하고 반드시 join()을 사용한다.
//		※ 각 동물은 10번씩만 운다.
		
//		Animal cat = new Animal();
//		Animal dog = new Animal();
//		Animal rat = new Animal();
//		
//		Thread catSound = new Thread(cat, "야옹");
//		Thread dogSound = new Thread(dog, "멍멍");
//		Thread ratSound = new Thread(rat, "찍찍");
//		
////		Runnable animal = () -> {
////			for (int i = 0; i < 10; i++) {								
////				System.out.println(Thread.currentThread().getName());
////				try {Thread.sleep(100);} catch (Exception e) {;}
////			}
////		};
////		
////		Thread catSound = new Thread(animal, "야옹");
////		Thread dogSound = new Thread(animal, "멍멍");
////		Thread ratSound = new Thread(animal, "찍찍");
//		
//		catSound.start();
//		dogSound.start();
//		
//		try {
//			catSound.join();
//			dogSound.join();
//		} catch (InterruptedException e) {;}
//		
//		ratSound.start();
		
		
		// 강사님 코드
		String[] sounds = {"어흥", "야옹", "음메멍멍"};
		Animal[] animals = new Animal[sounds.length];				// 울음소리를 담은 배열 sounds의 길이값만큼
		Thread[] threads = new Thread[sounds.length];				// 울음소리를 담은 배열 sounds의 길이값만큼
		
		for (int i = 0; i < threads.length; i++) {
			animals[i] = new Animal();								// 배열 안에 각 Animal 객체화
			threads[i] = new Thread(animals[i], sounds[i]);			// Thread에 Runnable타입 전달, 쓰레드 이름으로 sounds 배열 요소 전달
		}
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();										// 쓰레드 실행			
			if(i != 0) {											// i가 0일 때부터 실행하면 단일 쓰레드처럼 하나하나씩 실행되므로 첫번째 쓰레드를 건너뛸 수 있도록 조건식 작성
				try {threads[i].join();} catch (Exception e) {;}	// 첫번째, 두번째 쓰레드가 함께 실행되고 그 동안 세번째 쓰레드는 대기 상태			
			}
		}
	}
}
