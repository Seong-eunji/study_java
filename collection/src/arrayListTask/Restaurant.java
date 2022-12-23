package arrayListTask;

import java.util.ArrayList;

public class Restaurant {
	// 음식 정보를 담을 DB 선언
	// 2차원 배열은 거의 사용하지 않음
	public static ArrayList<Food> foods = new ArrayList<>();
	
	// 음식 추가
	public void add(Food food) {
		foods.add(food);
	}
	
	// 양식인지 중식인지 일식인지 한식인지 검사(문자열로 리턴)
	// 예) 스파게티 입력 시 "양식" 리턴
	public String checkType(String name) {			// 수정할 때에는 객체를 통째로 받는 걸 추천
		for (Food food : foods) {					// 매개변수를 하나만 받을 때에는 객체를 통쨰로 받지말고 하나만 받는 것이 효율적
			if(food.getName().equals(name)) {		// 객체를 통째로 받으면 필드에 접근할 때에 Mapping이라는 과정을 거쳐야 하기 때문에
				return food.getType();
			}
		}
		return null;
	}
	
	// 사용자가 원하는 종류의 음식 목록 조회(종류는 한 가지만 입력가능)
	// 예) 한식 입력 시 한식만 조회
	public ArrayList<Food> getList(String type){
		ArrayList<Food> foodlist = new ArrayList<>();
		
		for (Food food : foods) {
			if(food.getType().equals(type)) {
				foodlist.add(food);
			}
		}
		return foodlist;
	}
	
	// 음식의 종류 수정(가격 10% 상승)
	// 예)한식 -> 중식, 음식 가격은 10% 상승
	public void modify(Food food) {
		for (Food item : foods) {
			if(item.getName().equals(food.getName())) {
				item.setType(food.getType());
				item.setPrice((int)(item.getPrice() * 1.1));
				break;
			}
		}
	}
	
	// 사용자가 원하는 종류의 음식 개수 조회
	// 예) 불고기, 제육볶음, 파스타, 초밥
	// 한식 입력 시 2개
	public int count(String type) {				// 메소드를 만들기 전에 기존에 만들어둔 알고리즘을 활용할 수 있는지 확인하기
//		int count = 0;
//		for (Food food : foods) {
//			if(food.getType().equals(type)) {
//				count++;
//			}
//		}
//		return count;
		return getList(type).size();			// 원하는 종류의 음식 리스트를 찾아주는 메소드를 활용한 후 개수를 세어 리턴
	}
	
}
