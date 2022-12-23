package objectTest;

import java.util.Random;

import com.sun.jdi.Value;

class Employee{
	int number;
	String name;
	
	public Employee() {;}
	
	public Employee(int number, String name) {
		super();
		this.number = number;
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {						// 매개변수를 모든 타입을 다 받기 위해서 Object 타입으로 upCasting하여 받음
		if(this == obj) {									// 주소값이 같으면 무조건 값이 같으므로 true 반환
			return true;
			}
		if(obj instanceof Employee) {						// obj가 자식타입이라면
			Employee anotherEmployee = (Employee)obj;		// 우리가 검사하고자 하는 값은 자식타입의 값이므로 다시 downCasting(upCasting된 부모타입에선 +a이므로 잘려나감)
			if(this.number == anotherEmployee.number) {		// 값 비교
				return true;
			}
		}
		return false;
	}
}

public class EqualsTest {
	public static void main(String[] args) {
		
		Employee han = new Employee(1, "한동석");							// 객체 선언
		System.out.println(han.equals(new Employee(1, "한동석")));		// 또 다른 객체를 선언하여 같은지 비교를 위해 equals 메소드의 매개변수로 전달
		
		
		
		
//		String data1 = "ABC";
//		String data2 = "ABC";
//		String data3 = new String("ABC");
//		String data4 = new String("ABC");
//		
//		System.out.println(data1 == data2);
//		System.out.println(data1.equals(data2));
//		
//		System.out.println(data3 == data4);
//		System.out.println(data3.equals(data4));
		
//		Random r1 = new Random();
//		Random r2 = new Random();
//		
//		System.out.println(r1 == r2);
//		System.out.println(r1.equals(r2));
//		
//		r1 = r2;
//		System.out.println(r1 == r2);
//		System.out.println(r1.equals(r2));
		
	}
}
