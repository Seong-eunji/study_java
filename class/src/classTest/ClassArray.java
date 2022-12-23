package classTest;

class Test {
	int data;
	
	public Test() {;}
	
	public Test(int data) {
		this.data = data;
	}
	
	void printData() {
		System.out.println(data);
	}
}

public class ClassArray {
	public static void main(String[] args) {
		Test[] arTest = new Test[12];
		// 밑에 arTest[i] = new Test();로 할당하기 전까지는 12칸 모두 null값이 들어있다
		// 할당 후에는 생성자에 의해 주소값 대입
		
		for (int i = 0; i < arTest.length; i++) {
			arTest[i] = new Test(i + 1);
		}
		
		arTest[10].printData();
	}
}
