package vo;

public class NameDTO {
	private String gender;
	private String name;
	private int ranking;
	private int population;
	
	public NameDTO() {;}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}
	
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
	
	public String toString() {
		String str = gender + "\t" + name + "\t" + ranking + "\t" + this.comma(String.valueOf(population));
		return str;
	}
}
