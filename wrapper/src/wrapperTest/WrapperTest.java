package wrapperTest;

public class WrapperTest {
	public static void main(String[] args) {
		int data_i = 10;
		
		// boxing
//		Integer data_I = new Integer(data_i);	// 밑줄 : Deprecated, 더이상 쓰지말라는 뜻, 권장하지 않는 방법
//		Integer data_I = Integer.valueOf(data_i);
		
		// auto boxing
		Integer data_I = data_i;
		
		// unboxing
//		data_i = data_I.intValue();
		
		// auto unboxing
		data_i = data_I;
	}
}
