package collectionTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class HashSetTest {
	public static void main(String[] args) {
		// Object 클래스의 3가지 메소드 중 Hashcode의 줄임말 hash
		// 이 해시코드를 이용하여 해시코드값이 같으면 중복으로 처리
		HashSet<String> bloodTypes = new HashSet<String>();
		ArrayList<String> bloodTypeList = null;

		bloodTypes.add("A");
		bloodTypes.add("B");
		bloodTypes.add("O");
		bloodTypes.add("AB");
		bloodTypes.add("AB");
		bloodTypes.add("AB");
		bloodTypes.add("AB");
		bloodTypes.add("AB");
		bloodTypes.add("AB");
		bloodTypes.add("AB");

		bloodTypeList = new ArrayList<String>(bloodTypes);

		System.out.println(bloodTypeList.get(0));

		// Set 타입은 데이터 출력을 할 수 없는데 어떻게 출력을 할 수 있느냐?
		// Set 타입에서 재정의된 toString을 살펴보면
		// Iterator 메소드가 사용되어
		// Iterator<E> iterator = this.Iterator(); 타입으로 바뀌어서 순서를 부여해 출력함
//		System.out.println(bloodTypes.toString());
//		
//		Iterator<String> iter = bloodTypes.iterator();

		// 넣은 순서대로 출력할 수는 있느냐?
		// iterator는 그런 목적으로 사용되는 것이 아니기 때문에 입력된 순서는 중요하지 않음
//		while(iter.hasNext()) {
//			System.out.println(iter.next());
	}
}
