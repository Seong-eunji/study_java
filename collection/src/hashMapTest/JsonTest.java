package hashMapTest;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonTest {
	public static void main(String[] args) {
		HashMap<String, Object> userMap = new HashMap<String, Object>();	// <키, 값>
		JSONObject userJSON = null;
		JSONArray users = new JSONArray();
		
		userMap.put("id", "hds1234");		// "키", "값"
		userMap.put("pw", "1234");			// 키는 중복된 값을 넣으면 값이 최근값으로 수정, 중복되지 않은 데이터를 넣으면 추가
		userMap.put("name", "한동석");		// 값은 수정 가능
		userMap.put("age", 20);

		
		// HashMap 타입인 userMap을 JSONObject 타입에 담음
		userJSON = new JSONObject(userMap);
		// toJSONString 메소드 사용 시 JSONObject로 변경된 Map을 JSON형식으로 바꾸어줌(String 타입)
		System.out.println(userJSON.toJSONString());					// 한동석만 출력
		
		// JSONArray에 JSONObject 타입인 userJSON 추가
		users.add(userJSON);
		
		userMap.put("id", "hgd1234");
		userMap.put("pw", "5555");
		userMap.put("name", "홍길동");
		userMap.put("age", 25);
		
		userJSON = new JSONObject(userMap);
		users.add(userJSON);
		
		// JSONArray 출력
		System.out.println(users.toJSONString());						// 한동석, 홍길동 출력
		
		// 인덱스 1인 홍길동 호출
		System.out.println(((JSONObject)users.get(1)).toJSONString());	// 홍길동 출력
	}
}
