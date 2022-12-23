package bakery;

public class BreadMaker implements Runnable{
   
   public static boolean check;
   public static int i;
   
   @Override
   public void run() {								// 자원
//     빵을 20개 만든다.
//     int i = 0; 
	   i = 0;
      for (i = 0; i < 20; i++) {					// 빵 만들기 20번 반복
    	 BreadPlate.getInstance().makeBread();		// BreadPlate의 객체를 불러오는 메소드로 주소에 접근하여 빵 만들기 메소드 수행
         if(check) {break;}							// 만든 빵이 9개가 넘으면 check가 true를 반환하므로 자원 break;
         try {Thread.sleep(1000);} catch (InterruptedException e) {break;}	// 혹은 어디서든 interrupt()를 사용하면 break;
         
      }
      if(i != 20) {									// 반복횟수 20을 채우지 못하고 탈출했다면(나가기를 선택)
         System.out.println("안녕히 가세요");
         return;									// 아래의 코드가 실행되지 않도록 return
      }
      System.out.println("재료 소진");					// 반복횟수 20을 다 돌고 난 후의 실행될 코드
   }
}
