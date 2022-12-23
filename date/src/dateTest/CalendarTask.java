package dateTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarTask {
	public static void main(String[] args) {
		// 본인의 생년월일 출력
		Calendar birthday = Calendar.getInstance();	// 현재시간
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy월 MM월 dd일");
		
		birthday.set(Calendar.YEAR, 1993);
		birthday.set(Calendar.MONTH, 3);
		birthday.set(Calendar.DATE, 15);
		
		birthday.set(1993, 3, 15);
		
		System.out.println(simpleDateFormat.format(birthday.getTime()));
	}
}
