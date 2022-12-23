package collectionTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class HashMapTest {
	public static void main(String[] args) {
		HashMap<String, Object> userMap = new HashMap<String, Object>();	// <키, 값> 모든 타입의 값을 받기 위해서 Object 타입 설정
		userMap.put("id", "hds1234");
		userMap.put("pw", "1234");
		userMap.put("name", "한동석");
		userMap.put("age", 20);
		
		// 값은 8개지만 Map은 한 쌍으로 저장하므로 사이즈 4 출력
		System.out.println(userMap.size());
		System.out.println(userMap);
		// Key 값을 전달하면 Value를 리턴, key가 존재하지 않을 경우 null 리턴
		System.out.println(userMap.get("id"));
		
		// entry : 한 쌍, entry 타입은 key와 value 한 쌍을 가짐
		Iterator<Entry<String, Object>> iter = userMap.entrySet().iterator();
	
		// iterator 타입으로 변환하여 출력
		while(iter.hasNext()) {									// 다음 요소가 있는지 없는지 검사, 더 이상 가져올 다음 값이 없으면 false를 반환
			Entry<String, Object> entry = iter.next();			// next() : 다음에 올 요소의 값을 반환
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
		Iterator<String> iter2 = userMap.keySet().iterator();	// key값만 모은 Iterator
		
		System.out.println("====================");
		
		// key 분리
		while(iter2.hasNext()) {			
			System.out.println(iter2.next());					// key값만 모아놓은 Iterator에서 계속 다음값을 가져와 key값만 출력
		}
		
		System.out.println("====================");
		
		// value 분리
		for (Object value : userMap.values()) {					// 키,값 쌍이 담긴 userMap에서 .values()는 값 목록을 반환
			System.out.println(value);							// value라는 함수에 값을 하나씩 받아서 출력
		}
		
	}
}
