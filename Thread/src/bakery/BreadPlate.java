package bakery;

// 싱글톤 패턴
// 객체는 무조건 한 개만 만들고 돌려 쓴다.
public class BreadPlate {
   
   public static BreadPlate breadPlate;				// 객체 선언
   
   public int breadCount;							// 빵 개수를 세어줄 변수
   public int eatCount;								// 먹은 개수를 세어줄 변수
   
   private BreadPlate() {;}							// 기본 생성자의 접근지정자가 private이면 싱글톤패턴
   
   public static BreadPlate getInstance() {			// 기본 생성자를 대신하여 객체의 주소를 리턴해줄 메소드
      if(breadPlate == null) {						// breadPlate(객체)가 아직 구현되지 않았다면
         breadPlate = new BreadPlate();				// 구현하여 주소값 전달
      }
      return breadPlate;							// 구현한 필드의 주소값을 담고 있는 객체를 리턴
   }
   
//   빵만들기
//   만든 빵이 9개가 넘어가면, 쓰레드를 멈춰준다.
   public synchronized void makeBread() {
      if(breadCount > 9) {															// 만든 빵이 9개가 넘어가면
         System.out.println("빵이 가득 찼습니다.");	
         try {wait();} catch (InterruptedException e) {BreadMaker.check = true;}	// 쓰레드 멈춤, InterruptedException 발생하면 check를 true로 반환
         																			// check는 자원(run메소드)를 break하기 위해 필요
      }else {																		// 만든 빵이 9개가 넘지 않는다면
         breadCount++;																// 빵 개수++
         System.out.println("빵을 1개 만들었습니다. 현재 빵 개수 : " + breadCount + "개");
      }
   }
   
//   빵먹기
//   만든 빵이 0개면 못먹고, 먹은 빵이 20개면 못먹는다.
//   만약 빵을 먹게 되면, 멈춰있던 쓰레드를 깨워준다.
   public synchronized void eatBread() {
      if(eatCount == 20) {															// 먹은 빵의 개수가 20개라면
         System.out.println("빵이 다 떨어졌습니다!");										// 더 이상 먹을 수 없음
         
      }else if(breadCount < 1) {													// 만든 빵의 개수가 0이라면
         System.out.println("🍩🍩🍩🍩🌮🌮🥪🥪🥓🥓🍗🍗🍕빵을 만들고 있어요~!🍕🍔🍔🌮🌮🥪🥪🥓🥓🍗🍗🍕🍕🍔🍔");
         
      }else {																		// 둘 다 해당하지 않으면 빵 먹기 가능
         eatCount++;
         breadCount--;
         System.out.println("빵을 1개 먹었습니다. 현재 빵 개수 : " + breadCount + "개");
         notify();																	// 빵을 먹어 개수가 줄어들었으므로 잠들었던 쓰레드를 꺠움
         BreadMaker.i--;
      }
   }
}

