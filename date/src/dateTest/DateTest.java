package dateTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
	public static void main(String[] args) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		Date date = new Date();
		Date time = new Date();
		
		date.setYear(100);	// Deprecated, 권장하지 않음 Calendar.set으로 대체됨
							// 1900 + a 이기 때문에 2000년도는 100을 입력해주면 됨
		date.setMonth(11);	// 월은 0~11, 0이 1월
		
		// Date도 toString이 재정의되어 날짜가 나옴
		System.out.println(date);
		System.out.println(simpleDateFormat.format(date));
		
		// parse라는 메소드를 사용하면 parseException이라는 오류가 발생 가능하기 때문에
		// try~catch를 사용해야 함
		try {
			time = simpleDateFormat.parse("2002년 12월 04일 09시 00분 00초");
			System.out.println(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
