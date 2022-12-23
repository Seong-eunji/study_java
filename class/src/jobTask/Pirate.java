package jobTask;

import java.util.Random;

public class Pirate {
	String name;
	int age;
	int money;
	String food;
	int foodCount;
	int life;
	int gradeNumber;
	int successCount;
	int failCount;
	int successRating;
	
	String[] gradeTable = {"말단", "행동대장", "선장"};

	Random r = new Random();

	public Pirate() {;}

	public Pirate(String name, int age, int money, String food, int foodCount, int life, int gradeNumber) {
		this.name = name;
		this.age = age;
		this.money = money;
		this.food = food;
		this.foodCount = foodCount;
		this.life = life;
		this.gradeNumber = gradeNumber;
		this.successCount = 0;
		this.failCount = 0;
		this.successRating = 40;
	}

	void eat() {	// 먹기
		foodCount--;
		life++;
	}
	
	boolean work() {	// 약탈
		if(rating(this.successRating)) {
			return true;
		}
		return false;
	}
	
	void shopping() {	// 상점
		money -= 1000;
		foodCount++;
	}
	
	int fight() {	// 대결
		int[] dice = {1, 2, 3, 4, 5, 6};
		return dice[r.nextInt(6)];
	}
	
	void promote(Work[] work) {	// 승급 시 배열 works의 money(보상금액) 변화를 위해 매개변수로 받아옴
								// 리턴타입이 void이지만 Work[]의 주소를 통해 값을 저장하므로 리턴값 없이도 다른 클래스에서 변경된 Work의 값 이용 가능
			gradeNumber++;
			this.successRating += 5;
			for (int i = 0; i < work.length; i++) {	// for문 반복을 이용하여 work의 모든 인덱스의 보상금액을 증액
				work[i].money += 500;
			}
	}
	
	void demote(Work[] work) {	// 강등 시 배열 works의 money(보상금액) 변화를 위해 매개변수로 받아옴
								// 리턴타입이 void이지만 Work[]의 주소를 통해 값을 저장하므로 리턴값 없이도 다른 클래스에서 변경된 Work의 값 이용 가능
			gradeNumber--;
			this.successRating -= 5;
			for (int i = 0; i < work.length; i++) {	// for문 반복을 이용하여 work의 모든 인덱스의 보상금액을 감액
				work[i].money += 500;
			}
	}
	
	boolean rating(int rating) {			// 확률계산
		int[] percent = new int[100];		// 1의 단위 확률을 난수로 생성 시 100칸 배열 필요
		
		for (int i = 0; i < rating; i++) {	// rating에 입력된 확률만큼 i에 1 입력
			percent[i] = 1;
		}
		return percent[r.nextInt(percent.length)] == 1;
	}
	
	
}
