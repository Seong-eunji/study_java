package classTest;

public class Quiz {
// 문제, 정답, 먹이개수(문제마다 보상개수가 다를 수 있으므로)

//	String[] quiz = {"2 X 2 = ?",
//					"4 + 2 = ?",
//					"9 - 4 = ?",
//					"3 X 5 = ?",
//					"4 + 6 = ?",
//					"3 - 2 = ?",
//					"3 * 3 = ?",
//					"8 - 6 = ?",
//					"9 % 3 = ?",
//					"6 + 1 = ?"};
//	String[] answer = {"4", "6", "5", "15", "10", "1", "9", "2", "3", "7"};
//	int food;
	
	// 강사님 코드
	String question;
	String answer;
	int feedCount;
	boolean jackPot;
		
	public Quiz() {;}
	
	public Quiz(String question, String answer, int feedCount) {
		super();
		this.question = question;
		this.answer = answer;
		this.feedCount = feedCount;
	}

	public Quiz(String question, String answer, int feedCount, boolean jackPot) {
		super();
		this.question = question;
		this.answer = answer;
		this.feedCount = feedCount;
		this.jackPot = jackPot;
	}
	
}
