package access1;

public class Access2 {
	public static void main(String[] args) {
		Access1 a1 = new Access1();
//		a1.data1;		// default		동일 패키지 허용
//		a1.data2;		// public		모든 패키지, 클래스 허용
//		a1.data3;		// protected	동일 패키지, 자식 클래스 허용
	}
}
