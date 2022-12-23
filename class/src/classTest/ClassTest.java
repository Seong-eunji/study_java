package classTest;

class A {
	int data = 100;
	
	A(int data){
		this.data = data;
	}
	
	void printData() {
		System.out.println(data);
	}
}

public class ClassTest {
	public static void main(String[] args) {
		A a = new A(10);
		
		a.printData();
	}
}
