package collectionTest;

import java.util.ArrayList;		// 내부 API
import java.util.Collections;

public class ArrayListTest {
	public static void main(String[] args) {
		// <?> : 제네릭(이름이 없는)
		// 객체화 시 타입을 지정하는 기법
		// 설계할 때에는 타입을 지정할 수 없기 때문에 임시로 타입을 선언해 사용한다.
		// 따로 downCasting을 할 필요가 없다.
		// 지정할 타입에 제한을 둘 수 있다.
		ArrayList<Integer> datas = new ArrayList<>();
		
		datas.add(10);
		datas.add(20);
		datas.add(40);
		datas.add(50);
		datas.add(90);
		datas.add(80);
		datas.add(70);
		datas.add(60);
		
//		System.out.println(datas.size());
		
		for (int i = 0; i < datas.size(); i++) {
			System.out.println(datas.get(i));
		}
		
		// datas를 출력했을 때 주소값이 아닌 값이 나오는 것은
		// toString()이 재정의되어 있음
		System.out.println(datas);
		
		Collections.sort(datas);		// 오름차순 정렬
		System.out.println(datas);
		
		Collections.reverse(datas);		// 순서를 뒤집어주는 메소드
		System.out.println(datas);		// 내림차순 정렬이 됨
		
		Collections.shuffle(datas);		// 무작위로 섞어주는 메소드
		System.out.println(datas);		// 실행마다 순서가 뒤바뀜
		
		
		// 추가(삽입)
		// 50 뒤에 500 삽입
		datas.add(datas.indexOf(50) + 1, 500);
		System.out.println(datas);
		
		// 강사님 코드
		if(datas.contains(50)) {					// datas가 50을 포함하고 있는지부터 검사
			datas.add(datas.indexOf(50) + 1, 500);	// 포함하고 있다면 50의 인덱스를 검사해 그 뒤에 500 삽입
		}
		System.out.println(datas);
		
		
		// 수정
		// 90을 9로 수정
		datas.set(datas.indexOf(90), 9);
		System.out.println(datas);
		
		// 강사님 코드
		if(datas.contains(90)) {					// datas가 90을 갖고 있는지 검사
			System.out.println(datas.set(datas.indexOf(90), 9) + "이 9로 변경되었습니다.");	
		}											// 가지고 있다면 90을 9로 대체
													// 지정된 인덱스의 값이 9로 대체되고
													// 지정된 인덱스가 원래 가지고 있던 요소(변경전 요소)가 return값으로 나옴. 이걸 pop이라고 함
													// 리턴값으로 인해 datas.set(datas.indexOf(90), 9) 자체가 값(90)이 됨.
		System.out.println(datas);
		
		
		// 삭제
		// 80 삭제
		// 1. 인덱스로 삭제
		// 2. 값으로 삭제
		
		datas.remove(datas.indexOf(80));
		System.out.println(datas);
		
		Integer data = 80;
		datas.remove(data);
		System.out.println(datas);
		
		// 강사님 코드
		if(datas.contains(80)) {
			System.out.println(datas.remove(datas.indexOf(80)) + " 삭제");	// 지정된 인덱스의 값을 삭제하고 가지고 있던 그 인덱스의 값을 return함
		}																	// 그래서 "80 삭제"가 출력됨
		System.out.println(datas);			
		
		if(datas.remove(Integer.valueOf(80))) {								// 메소드 실행 시 boolean 타입을 return하므로 조건식으로 사용
			System.out.println("삭제 완료");									// 삭제 성공 시 "삭제 완료" 출력
		}
		System.out.println(datas);
		
		
	}
}
