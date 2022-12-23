package access2;

import access1.Access1;

public class Access3 extends Access1{
	public static void main(String[] args) {		
		Access3 a3 = new Access3();
//		a3.data2;		// public		모든 패키지, 클래스 허용
//		a3.data3;		// protected	동일 패키지, 자식 클래스 허용
		
	}
}
