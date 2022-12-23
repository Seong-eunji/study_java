package jobTask;

import java.util.Random;
import java.util.Scanner;

public class Adventure {
	public static void main(String[] args) {
		Pirate[] pirates = {	// 캐릭터
				new Pirate("잭 스패로우", 35, 5000, "사과", 5, 5, 0),
				new Pirate("루피", 17, 3500, "고기", 3, 7, 1) };

		Work[] works = { 	// 약탈 목록
				new Work("고잉메리호를 약탈 중입니다.", 2_000), 
				new Work("타이타닉호를 약탈 중입니다.", 1_500),
				new Work("거북선을 약탈 중입니다.", 2_500) };

		Scanner sc = new Scanner(System.in);

		String title = "※대해적시대※";
		String menuMessage = "모험을 떠나자!\n1. 잭 스패로우\n2. 루피\n3. 나가기";
		String actionMessage = "=========\n1. 내 정보\n2. 밥 먹기\n3. 약탈\n4. 대결\n5. 상점\n6. 나가기\n=========";
		int characterChoice = 0, actionChoice = 0;

		while (true) {
			System.out.println(title);
			System.out.println(menuMessage);
			characterChoice = sc.nextInt();
			if (characterChoice == 3) {		// 3. 나가기 선택하면 break
				break;
			}

			while (true) {
				System.out.println(actionMessage);
				actionChoice = sc.nextInt();
				if (actionChoice == 6) {	// 6. 나가기 선택하면 break
					break;
				}

				Pirate pirate = pirates[characterChoice - 1];	// 선택한 캐릭터를 변수 pirate에 담음

				switch (actionChoice) {
				case 1: // 내정보
					System.out.println("=====내정보=====");
					System.out.println("이름 : " + pirate.name);
					System.out.println("나이 : " + pirate.age);
					System.out.println("보유금액 : " + pirate.money);
					System.out.println("좋아하는 음식 : " + pirate.food);
					System.out.println("식량 개수 : " + pirate.foodCount);
					System.out.println("체력 : " + pirate.life);
					System.out.println("직급 : " + pirate.gradeTable[pirate.gradeNumber]);
					System.out.println("===============");
					break;

				case 2: // 먹기
					if (pirate.foodCount > 0) { // food가 최소 하나는 있어야 먹기 가능
						pirate.eat();
						System.out.println("냠냠쩝쩝..\n" + pirate.food + "를 먹고 체력이 올라갑니다.");
						System.out.println(pirate.name + "의 체력 : " + pirate.life);
						System.out.println(pirate.name + "의 식량 개수 : " + pirate.foodCount);
						break;
					}
					System.out.println("식량이 부족합니다.\n상점에서 식량을 구매하세요.");
					break;

				case 3: // 약탈
					if (pirate.life > 0) {							// 생명이 0보다 커야 약탈 가능
						Random r = new Random();					// 난수 이용을 위해 Random 클래스 객체화
						Work work = works[r.nextInt(works.length)];	// 난수를 이용해 선택된 works 중 하나를 work에 담음
						boolean success = pirate.work();			// work의 결과값을 success에 저장
						System.out.println(work.working);

						for (int i = 0; i < 3; i++) {				// 약탈 결과를 알기 전 1.2초 컴파일러 정지
							try {
								Thread.sleep(400);
							} catch (InterruptedException e) {
								;
							}
							System.out.print(".");
						}

						if (success) {								// 메소드 work의 결과값이 true일 때 시행
							pirate.successCount++;					// 성공 시 successCount + 1
							pirate.failCount = 0;					// 연속 3회 시 진급 또는 강등이므로 성공 시 failCount 리셋
							pirate.money += work.money;
							System.out.println("약탈에 성공했습니다!\n약탈 금액은 " + work.money + "입니다!");
							System.out.println(pirate.name + "의 보유금액 : " + pirate.money);
							
							// 연속 성공 횟수가 3이거나 등급넘버가 2(선장)이 아닐 경우 실행
							if (pirate.successCount == 3 && pirate.gradeNumber != 2) {
								pirate.promote(works);				// 진급 시에 works의 보상금액을 올려주기 위해서 promote 메소드에 works 배열 매개변수로 전달
								pirate.successCount = 0;			// 진급됐으므로 successCount 리셋
								System.out.println();
								System.out.println(pirate.gradeTable[pirate.gradeNumber] + "으로 승급했습니다!");
								System.out.println("약탈 성공률이 증가합니다!");
							}
							break;
						}
							// 메소드 work의 결과값이 false일 때 실행
							pirate.failCount++;						// 강등 시 failCount + 1
							pirate.successCount = 0;				// 연속 3회 시 진급 또는 강등이므로 실패 시 successCount 리셋
							pirate.money -= (work.money / 2);
							pirate.life--;
							System.out.println("약탈에 실패했습니다.\n체력이 감소하고 돈을 강탈당했습니다.");
							System.out.println(pirate.name + "의 체력 : " + pirate.life);
							System.out.println(pirate.name + "의 보유금액 : " + pirate.money);
							
							// 연속 실패 횟수가 3이거나 등급넘버가 0(말단)이 아닐 경우 실행
							if (pirate.failCount == 3 && pirate.gradeNumber != 0 ) {
								pirate.demote(works);				// 강등 시에 works의 보상금액을 감소시키기 위해서 promote 메소드에 works 배열 매개변수로 전달
								pirate.failCount = 0;				// 강등됐으므로 failCount 리셋
								System.out.println();
								System.out.println(pirate.gradeTable[pirate.gradeNumber] + "으로 강등되었습니다..");	// gradeNumber를 인덱스로 이용하여 gradeTable에서 등급 불러오기
								System.out.println("약탈 성공률이 감소합니다!");
							}
						break;
					}
					System.out.println("약탈에 도전할 체력이 부족합니다.\n식량을 섭취해 체력을 올려주세요.");
					break;
					
				case 4: // 대결
					Random r = new Random();
					int dice = pirate.fight();						// 상대방의 주사위 결과값을 담음
					int mydice = 0;									// 내 주사위 결과값을 담을 변수 선언
					String resultMessage = "";
					int reward = 0;

					if (pirate.money >= 600) {						// 소지금액이 600 이상일 때 실행
						System.out.println("대결! 주사위 굴리기!");
						System.out.println("승리 시 이긴 숫자의 200배!\n패배 시 상대방 숫자의 100배만큼 차감");
						System.out.print("던지는 중");

						for (int i = 0; i < 3; i++) {				// 주사위 대결 결과값이 알기 전 1.2초 컴파일러 정지
							try {
								Thread.sleep(400);					// 1초가 1000밀리초
							} catch (InterruptedException e) {
								;
							}
							System.out.print(".");
						}
						System.out.println();
						mydice = r.nextInt(6) + 1;					// 난수 0~5까지 생성하므로 +1을 하여 내 주사위 값 생성

						if (mydice > dice) {						// 내 주사위가 더 클 경우(승리)
							reward = mydice * 200;
							pirate.money += reward;
							resultMessage = "이겼습니다! 상금 " + reward + "를 획득합니다.";

						} else if (mydice < dice) {					// 내 주사위가 더 작을 경우(패배)
							reward = dice * 100;
							pirate.money -= reward;
							resultMessage = "졌습니다.. 금액 " + reward + "만큼 차감됩니다.";
						} else {									// 내 주사위와 상대방 주사위가 같을 경우(비김)
							resultMessage = "비겼습니다. 상금을 획득하지 못했습니다.";
						}
						System.out.println("내 주사위 : " + mydice + " / 상대방 주사위 : " + dice);
						System.out.println(resultMessage);
						System.out.println(pirate.name + "의 보유금액 : " + pirate.money);
						break;
					}
					System.out.println("대결에 도전할 금액에 모자랍니다.\n약탈에 도전하세요!");
					break;
					
				case 5: // 상점
					if (pirate.money >= 500) {						// 최소금액이 500이상일 때 상점 이용 가능
						pirate.shopping();
						System.out.println(pirate.food + "를 구매했습니다.");
						System.out.println(pirate.name + "의 식량 개수 : " + pirate.foodCount);
						break;
					}
					System.out.println("식량을 구매할 돈이 없습니다.\n약탈에 도전하세요!");
					break;

				default:
					System.out.println("잘못 선택하셨습니다.\n다시 입력해주세요.");
					break;
				}
			}
		}
	}
}
