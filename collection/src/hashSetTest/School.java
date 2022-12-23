package hashSetTest;

import java.util.HashSet;

public class School {
	public static void main(String[] args) {
		HashSet<Student> stds = new HashSet<Student>();

		stds.add(new Student(1, "한동석"));
		stds.add(new Student(1, "한동석"));

		System.out.println(stds.size());
		
		Student han = new Student(1, "한동석");
		System.out.println(han.equals(new Student(1, "한동석")));
			// Object에 정의된 equals 메소드이기 때문에 주소 비교라서 false가 나옴
			// 같은 학생이라고 나오도록 equals 재정의
			// 예전처럼 주소와 타입만 비교하면 해시코드를 비교하는 HashSet을 사용했을 때에 다른 사람을 인식하기 때문에
			// equals뿐만 아니라 hashcode도 재정의 해주어야 함
			// hashCode()는 해시코드가 아니라 number로 비교하여야 같은 사람인지 알 수 있기 때문에 number를 리턴
	}
}
