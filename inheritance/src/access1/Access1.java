package access1;

public class Access1 {
	int data1 = 10;
	public int data2 = 20;
	protected int data3 = 30;
	private int data4 = 40;
	
	public Access1() {;}
	
	public Access1(int data4) {
		this.data4 = data4;
	}

	public int getData4() {			// private 변수의 외부 접근을 허용하기 위해서
		return data4;
	}

	public void setData4(int data4) { // private 변수의 외부 접근을 허용하기 위해서
		this.data4 = data4;
	}
}
