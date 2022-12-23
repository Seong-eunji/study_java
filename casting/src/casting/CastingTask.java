package casting;

//import java.util.Scanner;
//
//class Mp4 {
//	public Mp4() {;}
//	
//	void function() {
//	}
//}
//
//class Animation extends Mp4 {
//	@Override
//	void function() {
//		System.out.println("자막지원");
//	}
//}
//
//class Movie extends Mp4 {
//	@Override
//	void function() {
//		System.out.println("4D");
//	}
//}
//
//class Drama extends Mp4 {
//	@Override
//	void function() {
//		System.out.println("굿즈");
//	}
//}
//
//public class CastingTask {
//	// 넷플릭스
//	// 애니메이션, 영화, 드라마 클래스 선언
//	// 사용자가 선택한 영상이
//	// 애니메이션이라면 "자막지원" 기능 사용
//	// 영화라면 "4D" 기능 사용
//	// 드라마라면 "굿즈" 기능 사용
//	
//	public void check(Mp4[] mp4, int choice) {
////		String genre = netflix[choice - 1];
//		for (int i = 0; i < mp4.length; i++) {
//			if(choice - 1 == i) {
//				
//			}
//		}
//		
//	
//		
////		switch(choice) {
////		case 1:
////			Animation animation = (Animation) animation;
////			animation.function();
////			break;
////		case 2:
/////			Movie movie = (Movie) movie;
////			movie.function();
////			break;
////		case 3:
////			Drama drama = (Drama) drama;
////			drama.function();
////			break;
////		}
//	}
//	
//	public static void main(String[] args) {
//	
//		String[] netflix = {
//				"1. 포켓몬스터",
//				"2. 닥터 스트레인지",
//				"3. 이상한 변호사 우영우"
//		};
//		
//		Mp4[] mp4 = {
//				new Animation(),
//				new Movie(),
//				new Drama()
//		};
//		
//		String msg = "영상을 선택하세요 : ";
//		int choice = 0;
//		
//		Scanner sc = new Scanner(System.in);
//		CastingTask cast = new CastingTask();
//		Mp4 choiceMp4 = new Mp4();
//		
//		for (int i = 0; i < netflix.length; i++) {
//			System.out.println(netflix[i]);
//		}
//		System.out.print(msg);
//		choice = sc.nextInt();
//		
//		cast.check(mp4, choice);
//		
//		
//		
//	}
//}

public class CastingTask{
//  넷플릭스
//  애니메이션, 영화, 드라마 클래스 선언
//  사용자가 선택한 영상이
//  애니메이션이라면 "자막지원"기능 사용
//  영화라면 "4D"기능 사용
//  드라마라면 "굿즈"기능 사용

	// 어떤 자식 클래스 타입이든 상관없이 한 번에 매개로 받아올 수 있도록 upCasting
	// 그 후에 각각 자식 클래스 안의 메소드를 사용하기 위해 다시 downCasting해준다
	// 그 이유는 upCasting된 상황에서는 자식 클래스의 메소드들이 다 잘려나간 상태이므로 실행시킬수가 없음
	public void check(Video video) {				// Video 타입 매개변수로 받아서 upCasting
		if(video instanceof Animation) {			// video가 Animation 타입일 경우
			Animation ani = (Animation) video;		// video를 Animation 타입으로 강제 형변환 하여 downCasting
			ani.printSubtitle();					// Animation 클래스의 printSubtitle 메소드 실행
		} else if(video instanceof Film) {			// video가 Film 타입일 경우
			Film film = (Film) video;				// video를 Film 타입으로 강제 형변환하여 downCasting
			film.print4D();							// Film 클래스의 print4D 메소드 실행
		} else {									// video가 Drama 타입일 경우
			Drama drama = (Drama) video;			// video를 Drama 타입으로 강제 형변환하여 downCasting
			drama.sellGoods();						// Drama 클래스의 sellGoods 메소드 실행
		}
	}
	
	public static void main(String[] args) {
		CastingTask ct = new CastingTask();			// check 메소드 사용을 위한 CastingTask 객체화
		
		ct.check(new Animation());					// check 메소드에게 Animation 타입의 자식 클래스 전달
		ct.check(new Film());						// check 메소드에게 Film 타입의 자식 클래스 전달
		ct.check(new Drama());						// check 메소드에게 Drama 타입의 자식 클래스 전달
	}
}