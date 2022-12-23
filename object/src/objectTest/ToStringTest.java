package objectTest;

import java.util.Random;

class Student {
	int number;
	String name;
	
	public Student() {;}

	public Student(int number, String name) {
		super();
		
		this.number = number;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return number + ", " + name;
	}
}

public class ToStringTest {
	public static void main(String[] args) {
		Random r = new Random();
		System.out.println(r.toString());	// 출력 : java.util.Random@53bd815b
											// @53bd815b만 나오면 주소값이지만 객체의 소속이 같이 출력되면서 문자열 타입으로 봐야됨
											// r만 출력해도 같은 결과
											// 왜냐하면 이때까지 컴파일러가 .toString()을 붙여서 출력해줌
		
		Student std = new Student(1, "한동석");
		System.out.println(std);
	}
}
