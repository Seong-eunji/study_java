package arrayListTask;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;

public class DateApp {
	// 이상형 정보를 담을 DB
	public static ArrayList<Love> loves = new ArrayList<>();
	
	// 이상형 추가
	public void add(Love love) {
		loves.add(love);
	}
	
	// 사용자가 입력한 나이인 이상형 목록 조회
	public ArrayList<Love> getList(int age) {
		ArrayList<Love> selected = new ArrayList<>();
		
		for (Love love : loves) {
			if(love.getAge() == age) {
				selected.add(love);
			}
		}
		return selected;
	}
	
	// 이상형의 나이 수정
	// 이상형의 번호를 추가하고 번호로 조회한다.
	// 이름이 똑같으면 어떻게 수정하지?
	// 구분점이 필요하다, 고유한 값이 필요하다
	// 수정하기 페이지에 들어가서 수정완료를 누르면 이 메소드가 실행
	public void update(Love love) {		// 매개변수로 받은 이 love에는 이미 수정된 나이가 담겨있음
		for (Love db : loves) {
			if(db.getNumber() == love.getNumber()) {
				// 객체 통째로 변경
				loves.set(loves.indexOf(db), love);		// db의 해당 인덱스의 객체를 통째로 바꿈(주소값이 바뀜)
				// 기존 객체의 필드 변경
				db.setAge(love.getAge());				// db에 저장된 필드의 주소에 접근하여 getAge로 받아온 값으로 바꿈(주소값이 바뀌지 않음)
				break;
			}
		}
	}
	
	// 이상형의 나이 순으로 정렬하기
	public void sort() {
		// 이상형의 나이만 담을 ArrayList
		ArrayList<Integer> ages = new ArrayList<>();
		
		// 오름차순으로 정렬된 이상형 정보를 담을 ArrayList
		ArrayList<Love> loves = new ArrayList<Love>();
		
		for (Love love : DateApp.loves) {
			// 이상형의 나이만 담아주기
			ages.add(love.getAge());
		}
		
		// 나이 오름차순 정렬
		Collections.sort(ages);
		
		for (int i = 0; i < DateApp.loves.size(); i++) {
			for (Love love : DateApp.loves) {
				// 정렬된 나이 ArrayList에서 첫번째 나이부터 비교
				if(ages.get(i) == love.getAge()) {
					// 찾았다면 순서대로 loves에 담아주기
					loves.add(love);
				}
			}
		}
		// 오름차순으로 정렬된 전체 정보를 DB에 저장
		DateApp.loves = loves;
	}
	
	public static void main(String[] args) {
		Love first = new Love();
		Love second = new Love();
		DateApp dateApp = new DateApp();
		
		first.setName("아이유");
		first.setAge(25);
		second.setName("박보영");
		second.setAge(23);
		
		dateApp.add(first);
		dateApp.add(second);
		
		for (Love love : loves) {
			System.out.println(love);
		}
		
		ArrayList<Love> selected = dateApp.getList(23);
		for (Love love : selected) {
			System.out.println(love);
		}
		
		
		
	}
}
