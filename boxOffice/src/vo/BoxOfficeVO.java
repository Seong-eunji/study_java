package vo;

public class BoxOfficeVO {
	private int ranking;
	private String name;
	private String releaseDate;
	private long income;
	private int guestCount;
	private int screenCount;

	public BoxOfficeVO() {
		;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public long getIncome() {
		return income;
	}

	public void setIncome(long income) {
		this.income = income;
	}

	public int getGuestCount() {
		return guestCount;
	}

	public void setGuestCount(int guestCount) {
		this.guestCount = guestCount;
	}

	public int getScreenCount() {
		return screenCount;
	}

	public void setScreenCount(int screenCount) {
		this.screenCount = screenCount;
	}
	
	// 숫자타입은 3자리마다 ',' 삽입
	public String comma(String data) {								// String 타입으로 매개변수 받음
		String comma = "";											// ,를 찍고 난 후의 문자열을 담을 변수
		
		for (int i = 0; i < data.length(); i++) {					// 전해받은 문자열의 길이값만큼 반복
			if(i % 3 == 0 && i != 0) {								// ,가 3번째 자리수마다 찍혀야 하므로 i가 3의 배수일 떄
																	// i가 0일 때는 제일 마지막 자리의 숫자(일의 자리)를 뜻하므로 포함시키지 않는다.
				comma = "," + comma;								// 참이면 ,를 포함하여 누적 연산
			}
			comma = data.charAt(data.length() - i - 1) + comma;		// 거짓이면 길이값 - i - 1의 자릿수를 연산한다. (i=0일 경우, 길이값 -1의 인덱스이므로 마지막 자릿수를 뜻함)
		}
		
		return comma;
	}
	
	@Override
	   public String toString() {
		String str = ranking + "\t" + name + "\t" + releaseDate + "\t"
					+ this.comma(String.valueOf(income)) + "\t"
					+ this.comma(String.valueOf(guestCount)) + "\t"
					+ this.comma(String.valueOf(screenCount));
	     return str;
	   }
	
	
//	// toString안에서 반복을 돌리는 버전
//	@Override
//	public String toString() {
//		String str = null;
//		String incomeWithComma = String.valueOf(income);
//		String guestCountWithComma = String.valueOf(guestCount);
//		String screenCountWithComma = String.valueOf(screenCount);
//		
//		for (int i = 0; i < incomeWithComma.length(); i++) {		// 이걸 3번 반복
//			if(i % 3 == 0 && i != 0) {								// 콤마를 먼저 붙이면 해당 조건식을 사용해야 함
//				incomeWithComma = "," + incomeWithComma;
//			}
//			incomeWithComma = incomeWithComma.charAt(incomeWithComma.length() - 1 - i) + incomeWithComma;
////			if(i % 3 == 2) {										// 콤마를 나중에 붙이면 해당 조건식을 사용해야 함
////				incomeWithComma = "," + incomeWithComma;
////			}
//		}
//		
//		str = ranking + "\t" + name + "\t" + releaseDate + "\t" + incomeWithComma + "\t" +  guestCountWithComma + "\t" +  screenCountWithComma;
//		return str;
//	}

}
