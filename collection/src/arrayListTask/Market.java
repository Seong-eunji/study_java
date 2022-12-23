package arrayListTask;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Market {
	// 과일들을 저장할 DB 선언
	public static ArrayList<Fruit> fruits = new ArrayList<>();
	
	// 과일 추가
	public void add(Fruit fruit) {
		fruits.add(fruit);
	}
	
	// 과일 삭제
	// 과일의 이름을 외부에서 전달받는다.
	public void remove(String name) {
//		for (int i = 0; i < fruits.size(); i++) {
//		}
		
		// 빠른 for문에서 반복자 자리에 있는 객체의 데이터에 변화가 생기면
		// 반복에 대한 기준점이 수정되는 것이므로 오류가 발생한다. (일반 for문 사용하면 발생x)
		try {
			for (Fruit fruit : fruits) {
				// DB에서 과일의 이름을 검색한다.
				if(fruit.getName().equals(name)) {
					// 과일의 이름으로 삭제한다.
					// 과일의 이름이 검색되면 해당 객체를 저장한다.
					// 저장한 객체를 DB에서 삭제한다.
					fruits.remove(fruit);
				}
			}
		} catch (ConcurrentModificationException e) {
			e.printStackTrace();
		}
		// 빠른 for문은 fruits의 값을 기준으로 하여 반복을 돌고 있는데
		// 삭제로 인해 그 값에 변동이 생기면서 반복에 문제가 생김
		// 무한루프에 빠질 위험도 있기 때문에
		// 그래서 concurrentmodificationexception라는 오류를 발생시켜 강제종료시켜버림
		// break를 써주거나 try~catch문을 사용해줘야 함
	}
	
	
	// 과일 가격이 평균 가격보다 낮은 지 검사
	public boolean compare(int price) {
		return price < getAverage();
	}
	
	// 과일의 평균 가격을 구함
	public double getAverage() {
//		double avg = 0;
//		for (Fruit fruit : Market.fruits) {
//			avg += fruit.getPrice();
//		}
//		avg /= fruits.size();
//		return avg;
		
		// 강사님 코드
		int total = 0;
		double avg = 0.0;
		
		for (Fruit fruit : fruits) {
			total += fruit.getPrice();
		}
		return avg = Double.parseDouble(String.format("%.2f", total / (double)fruits.size()));
	}
	
	public static void main(String[] args) {
		Fruit apple = new Fruit();
		Fruit strawberry = new Fruit();
		Market market = new Market();
		
		apple.setName("사과");
		apple.setPrice(2_000);
		strawberry.setName("딸기");
		strawberry.setPrice(9_000);
		
		market.add(apple);
		market.add(strawberry);
		
		market.remove("사과");		
	
		if(!market.compare(strawberry.getPrice())) {
			System.out.println(strawberry.getName() + "는 평균가보다 비싸다.");
		} else {
			System.out.println(strawberry.getName() + "는 평균가보다 비싸지 않다.");
		}
				
		for (Fruit fruit : fruits) {
			System.out.println(fruit);
		}
	}
}

