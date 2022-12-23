package arrayListTask;

public class Love {
	// 이름, 나이	
	// private
	private String name;
	private int age;
	private int number;
	
	// 기본 생성자
	public Love() {;}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	// toString()
	public String toString() {
		String str = number + ", " + name + ", " + age + "살";
		return str;
	}
	
}
