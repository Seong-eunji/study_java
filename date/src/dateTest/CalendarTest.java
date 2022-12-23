package dateTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarTest {
	public static void main(String[] args) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd aaa hh:mm:dd");
		
//		// 추상클래스
//		// 싱글톤 패턴으로 구현, 달력은 하나니까
//		// 그래서 이미 선언되어 있는 객체를 호출하여 현재 시간을 가져올 수 있음
//		Calendar today = Calendar.getInstance();
//		System.out.println(today);
//		
//		// simpleDateFormat은 Date 타입을 전달받기 때문에
//		// Calendar 타입을 Date 타입으로 바꿔주는 메소드를 사용
//		System.out.println(simpleDateFormat.format(today.getTime()));
		
		Calendar date = Calendar.getInstance();
		date.set(2000, 11, 04);								// 2000.12.04를 전달
		date.set(Calendar.YEAR, 2000);						// 필드(Calendar 안에 선언된 년, 월, 일 등)을 전달 할 때 int 타입으로 전달해야함
															// 그러나 이미 YEAR, MONTH 등을 final 상수로 선언해놓았음
		System.out.println(simpleDateFormat.format(date.getTime()));
		
		System.out.println(date.get(Calendar.YEAR));
		System.out.println(date.get(Calendar.MONTH) + 1);	// 0~11이기 때문에 출력 시 1을 더해주어야 함
		
	}
}
