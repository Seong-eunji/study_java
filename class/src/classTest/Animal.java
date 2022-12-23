package classTest;

import java.util.Random;
import java.util.Scanner;

public class Animal {
//	String name;
//	int age;
//	int food;
//	int heart;
//
//	public Animal(String name, int age) {
//		super();
//		this.name = name;
//		this.age = age;
//		this.food = 3;
//		this.heart = 3;
//	}
//
//	void eat() {
//		if (food != 0) {
//			food--;
//			heart++;
//		}
//	}
//
//	void sleep() {
//		System.out.println("잠이 들었습니다.");
//		System.out.println("[3... 2... 1...]");
//		heart++;
//		System.out.println("생명력이 1 회복됩니다. [남은 생명 " + this.heart + "]");
//	}
//
//	// 외부에서 모든 문제를 전달받는다.
//	// 전달받은 문제 중 랜덤한 한 개의 문제만 리턴한다.
//	void walk(String[] quiz, int[] random) {
//		Quiz q = new Quiz();
//
//		heart--;
//		if (heart == 0) {
//			this.sleep();
//		}
//
//		random[0] = (int)(Math.random() * 10);
//		quiz[0] = q.quiz[random[0]];
//	}
//
//	public static void main(String[] args) {
//		Animal ani = new Animal("토깽이", 2);
//		Scanner sc = new Scanner(System.in);
//		String msg = "=Menu=\n1. 먹기\n2. 자기\n3. 산책가기\n4. 작별인사하기", answer = "";
//		int choice = 0;
//
//		while (ani.heart != 0) {
//			System.out.println(msg);
//			choice = sc.nextInt();
//			
//			switch (choice) {
//			case 1:
//				if(ani.food == 0) {
//					System.out.println("먹이가 없습니다.");
//					continue;
//				}
//				ani.eat();
//				System.out.println("먹이를 먹습니다. 냠냠");
//				System.out.println("[남은 먹이 : " + ani.food + "]");
//				System.out.println("[생명 : " + ani.heart + "]");
//				break;
//			case 2:
//				ani.sleep();
//				break;
//			case 3:
//				String[] quiz = { "" };
//				int[] random = new int[1];
//				Quiz checkAnswer = new Quiz();
//				
//				ani.walk(quiz, random);
//				
//				System.out.println("산책을 떠납니다.");
//				System.out.println("문제 : " + quiz[0]);
//				System.out.print("정답은? ");
//				answer = sc.next();
//				boolean correct = answer.equals(checkAnswer.answer[random[0]]) ? true : false;
//				
//				if (correct) {
//					ani.food = ani.food + 2;
//					System.out.println("정답입니다. 먹이를 드릴게요! [현재 먹이 개수 " + ani.food + "개]");
//				} else {
//					ani.heart--;
//					System.out.println("틀렸습니다. 생명이 차감됩니다. [현재 생명 " + ani.heart + "]");
//					if (ani.heart == 0) {
//						ani.sleep();
//					}
//				}
//				break;
//			case 4:
//				System.out.println("잘 가!");
//				break;
//			}
//			if(choice == 4) break;
//			
//		}
//
//		System.out.println("-끝-");
//	}
	
	
	// 강사님 코드
	String name;
	int age;
	String feed;
	int feedCount;
	int life;
	
	public Animal() {;}

	public Animal(String name, int age, String feed, int feedCount, int life) {
		super();
		this.name = name;
		this.age = age;
		this.feed = feed;
		this.feedCount = feedCount;
		this.life = life;
	}
	
	void eat() {	// 먹기
		feedCount--;
		life++;
	}
	
	void sleep() {	// 자기
		life++;
	}
	
	Quiz walk(Quiz[] quizes) {	// 퀴즈
		life--;
		
		// 잭팟 문제, 확률은 10%
		// 확률이 10단위라면 10칸 배열을, 1단위라면 100칸 배열을 선언한다.
		// int배열로 선언한 뒤 사용자가 원하는 확률 만큼 1로 값을 바꿔준다.
		
		// 예) 30% 확률이라면 10칸 배열에 3개의 값만 1로 바꿔준다.
		// 따라서 원하는 확률 / 10을 하여 반복횟수를 결정해주고,
		// 그 만큼 1이 들어가게 된다.
		
		// 위의 배열이 완성되었으면 랜덤한 인덱스번호로 값에 접근하여
		// 1이 나왔을 때에는 원하는 확률로 성공한 것이고,
		// 0이 나왔을 때에는 실패한 것이다.
		
		// 맞추면 먹이 개수를 백개 천개 만개 주기
		// 일반 문제와 잭팟 문제를 구분
		// 퀴즈 클래스에 필드를 하나 더 만들어야함
		// 사용자가 아닌 개발자가 확률을 정해주는 것이기 때문에
		// 외부에서 랜덤 확률을 매개변수로 받아올 필요 없음
		
		Random r = new Random();	// 난수 입력을 위해
		Quiz quiz = null;
		int[] percent = new int[10];	// 난수 생성을 위해 1을 담기 위한 배열
		int rating = 10;

		
		for (int i = 0; i < rating / 10; i++) {
			percent[i] = 1;
		}
		
		if(percent[r.nextInt(percent.length)] == 1) {	// 배열의 값이 1일 경우(=잭팟일 경우)
			for (int i = 0; i < quizes.length; i++) {
				if(quizes[i].jackPot) {	// 잭팟일 때 잭팟 문제를 리턴
					return quizes[i];
				}
			}
		}
	
		while(true) {
			quiz = quizes[r.nextInt(quizes.length)];
			if(!quiz.jackPot) {break;}	// 잭팟이 아닐 경우 break
										// 잭팟이 아닌 문제들은 false가 담겨있으므로 !quiz.jackpot은 true
		}
			
		return quiz;
	}
}
