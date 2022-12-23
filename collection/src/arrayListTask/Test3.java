package arrayListTask;

import java.util.ArrayList;

public class Test3 {
	public static void main(String[] args) {
//		Food spaghetti = new Food();
//		Food friedRice = new Food();
//		Food sushi = new Food();
//		Food jjajang = new Food();
//		Food kimchi = new Food();
//		
//		Restaurant res = new Restaurant();
//		ArrayList<Food> foodlist = new ArrayList<>();
//		
//		// 입력
//		spaghetti.setType("양식");
//		spaghetti.setName("스파게티");
//		spaghetti.setPrice(12_000);
//		
//		friedRice.setType("한식");
//		friedRice.setName("볶음밥");
//		friedRice.setPrice(6_000);
//		
//		sushi.setType("일식");
//		sushi.setName("초밥");
//		sushi.setPrice(10_000);
//		
//		jjajang.setType("중식");
//		jjajang.setName("짜장면");
//		jjajang.setPrice(7_000);
//		
//		kimchi.setType("한식");
//		kimchi.setName("김치");
//		kimchi.setPrice(15_000);
//		
//		// 추가
//		res.add(spaghetti);
//		res.add(friedRice);
//		res.add(sushi);
//		res.add(jjajang);
//		res.add(kimchi);
//		
//		// 종류 출력
//		System.out.println(res.checkType("스파게티"));
//		System.out.println(res.checkType("초밥"));
//		
//		// 원하는 종류 목록 조회 
//		foodlist = res.getList("한식");
//		for (Food food : foodlist) {
//			System.out.println(food);
//		}
//		
//		// 종류 수정 및 가격 수정
//		friedRice.setType("중식");
//		res.modify(friedRice);
//		System.out.println(friedRice);
//		
//		// 원하는 종류 개수 조회
//		System.out.println(res.count("중식") + "개");
		
		Restaurant restaurant = new Restaurant();
		Food food = new Food();
		
		// 얕은 복사 (하나만 수정해도 다 수정됨) <> 깊은 복사
		food.setName("치즈돈까스");
		food.setPrice(8500);
		food.setType("일식");
		
		Restaurant.foods.add(food);
		
		// 얕은 복사는 값이 모두 수정되어 버리기 때문에 새로운 주소를 할당하기 위해서 선언
		food = new Food();
		
		food.setName("한우갈비찜");
		food.setPrice(12000);
		food.setType("한식");
		
		Restaurant.foods.add(food);
		
		food = new Food();
		
		food.setName("잡채찜닭돼지고기두루치기");
		food.setPrice(1500);
		food.setType("한식");
		
		Restaurant.foods.add(food);
		
//		System.out.println(restaurant.checkType("한우갈비찜"));
//		System.out.println(restaurant.checkType("치즈돈까스"));

		for (Food selected : restaurant.foods) {
			System.out.println(selected);
		}
		
		food.setType("일식");
		restaurant.modify(food);
		System.out.println(Restaurant.foods);
		
		System.out.println(restaurant.count("일식"));
		System.out.println(restaurant.count("한식"));
		
		
	}
}
