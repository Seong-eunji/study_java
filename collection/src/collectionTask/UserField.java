package collectionTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class UserField{
	// 암호화에 쓸 키를 선언
	private final int KEY = 3;
	
	// 회원 정보를 담을 DB를 ArrayList로 선언
	public ArrayList<User> db = new ArrayList<>();
	
	public UserField() {;}
	
	// 아이디 중복검사
	public User checkId(String id) {
		User user = null;
		// DB에 있는 회원 수 만큼
		for (int i = 0; i < db.size(); i++) {		// db의 길이만큼 반복
			// 회원별 아이디 검사							
			if(db.get(i).getId().equals(id)) {		// db의 각 인덱스의 id를 호출하여 입력받은 id와 비교
				// 일치하는 아이디가 있다면 user에 저장
				user = db.get(i);					// 일치한다면 user에 해당 인덱스의 정보를 담음
				break;								// user 리턴
			}
		}
		// null 또는 해당 회원 리턴
		return user;								// 일치하는 것이 없다면 중복이 없으므로 null값 리턴
	}
	
	// 회원가입
	public void join(User user) {
		// 암호화
		user.setPassword(encryption(user.getPassword()));
		// DB에 저장
		db.add(user);
	}
	
	// 로그인
	public User logIn(String id, String pw) {
		// 아이디 검사
		User user = this.checkId(id);				// 입력받은 id와 일치하는 user 호출
		// 아이디가 있다면
		if(user != null) {							// 일치하는 user가 있다면
			// 비밀번호 검사
			if(user.getPassword().equals(pw))		// 입력받은 pw와 user에 저장된 pw 비교
				// 로그인 성공(성공 시 로그인된 회원의 정보 리턴)
				return user;						// 일치하면 user 리턴
		}
		// 로그인 실패
		return null;								// user가 없거나 pw가 틀리면 null 리턴
	}												// null이라고 안쓰고 user라고 써주면 아이디만 맞고 비밀번호가 틀려도 user를 리턴함
	
	// 암호화
	public String encryption(String password) {		// 비밀번호 문자열 만들기
		String encryPW = "";						// 암호화된 비밀번호를 담을 변수 
		
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);			// 각 문자로 쪼개기
			encryPW += (char)((c + 9) * 2);			// 아스키코드로 연산하여 암호화 후 하나씩 담아줌
		}
		return encryPW;								// 암호화된 비밀번호 리턴
	}
	
	// 비밀번호 변경(비밀번호 찾기 서비스)
	public void changePassword(User user) {					// 화면에서 입력받은 값을 가지고 있는 user
		// 외부에서 사용자가 입력한 정보 중, 아이디로 전체 정보 조회
		User userOrigin = this.checkId(user.getId());		// 아이디는 변하지 않으므로 해당 아이디로 유저 받아옴(중복검사가 아니라 DB조회용)
		// 해당 회원의 비밀번호를 새로운 비밀번호로 변경
		userOrigin.setPassword(user.getPassword());			// 입력받은 비밀번호를 DB에서 호출한 user의 비밀번호에 입력
	}
	
	// 인증번호 전송
	// 인증번호 전송 시 전송된 인증번호가 화면에서 필요하다.
	// 따라서 전송한 인증번호를 사용한 화면쪽으로 리턴해준다.
	public String sendSms(String phoneNumber) {
		String api_key = "NCSSPLLWZCTD7ET7";
	    String api_secret = "QMRPJQGEX3TZFCLXL0E56JUCSE3UJTKX";
	    Message coolsms = new Message(api_key, api_secret);
	    String certificationNumber = getCertificationNumber();
	    
	    // 4 params(to, from, type, text) are mandatory. must be filled
	    HashMap<String, String> params = new HashMap<String, String>();
	    // 사용자가 입력한 핸드폰 번호로
	    params.put("to", phoneNumber);
	    params.put("from", "01000000000");
	    params.put("type", "SMS");
	    // 인증번호 발송
	    params.put("text", "인증번호 " + certificationNumber + "입니다.");
	    params.put("app_version", "test app 1.2"); // application name and version

	    try {
	      JSONObject obj = (JSONObject) coolsms.send(params);
	      System.out.println(obj.toString());
	    } catch (CoolsmsException e) {
	      System.out.println(e.getMessage());
	      System.out.println(e.getCode());
	    }
	    // 화면쪽에 발송한 인증번호를 리턴
		return certificationNumber;							// Page에서 비교를 위해 pin 리턴
	}
	
	// 인증번호 6자리 만들기
	public String getCertificationNumber() {
		Random r = new Random();
		String number = "";
		
		// 000000~999999
		// 앞에 0이 붙으면 정수이기 때문에 생략된다
		number = r.nextInt(1000000) + "";					// 나머지를 생성하지만 int형이기 때문에 앞자리의 0이 생략됨
		// 생략된 0만큼 앞에 직접 연결시켜주어야 한다.
		for (int i = 0; i < 6 - number.length(); i++) {		// 6자리의 난수를 만들거기 때문에 6에서 number의 길이만큼 빼줌
			number = "0" + number;							// 반복으로 number 앞에 0을 붙여줌
		}
		return number;
		
//	    for (int i = 0; i < 6; i++) {
//			number += r.nextInt(10);							// 0 ~ 9까지 6자리 난수 생성
//		}
	}
}
