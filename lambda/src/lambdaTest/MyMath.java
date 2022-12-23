package lambdaTest;

import java.util.Iterator;
import java.util.Scanner;

/*	[심화 실습]
 *	여러 개의 정수를 입력받아서 알맞는 덧셈, 뺄셈 결과를 확인하는 애플리케이션 제작
 *
 *	입력 예1) 7 + 35 - 9
 *	출력 예1) 33
 *
 *	입력 예2) -9 + 8 + 10
 *	출력 예2) 9
 *
 *	"ABC".split("")은 [A][B][C] 3칸 문자열 배열로 리턴된다.
 *	"A,B,C".split(",")은 [A][B][C] 3칸 문자열 배열로 리턴된다.
 *	split("구분점")을 전달하면 문자열에서 동일한 구분점을 기준으로 문자열 값을 잘라낸 후 문자열 배열로 리턴한다.
 *	구분점을 여러 개 사용할 떄에는 split("구분점|구분점")으로 사용하며, "+","-"를 구분점으로 사용할 때에는 "\\+","\\-"로 표현한다.
 *
 * 	사용자가 정상적으로만 입력한다는 가정 하에 구현하도록 한다.
 *	두 정수를 전달받은 후 int로 리턴하는 calc 추상메소드 선언(함수형 인터페이스 제작)
 *  두 정수의 덧셈, 뺄셈을 구해주는 함수형 인터페이스를 리턴하는 static 메소드 선언(람다식 리턴)
 * 	전체 식을 전달받은 후 String[]로 리턴하는 getOpers 추상메소드 선언(함수형 인터페이스 제작)
 * 	main메소드에 getOper를 람다식으로 구현
 * 	첫번째 정수가 음수일 경우 오류 해결
 */

public class MyMath {
	// 외부에서 연산자 한 개를 전달받는다.
	public static Calc calculator (String oper) {
		Calc c = null;
		
		switch(oper) {
		case "+" :								// 전달받은 연산자가 +일 때
			c = (n1, n2) -> n1 + n2;			// Calc 타입
			break;
		case "-" :
			c = (n1, n2) -> n1 - n2;			// 전달받은 연산자가 -일 때
			break;
		}
		return c;								// 구현이 된 Calc를 리턴
												// 덧셈, 뺄셈을 해주는 메소드가 아니라
												// 덧셈, 뺄셈을 할 준비를 해주는 메소드
	}
				
	public static void main(String[] args) {
		OperCheck getOpers = (ex) -> {					// 객체 선언, 추상 메소드를 람다식(익명클래스)로 구현
			String temp = "";
			
			// 전체 수식에서 연산자만 골라서 문자열에 담고
			for (int i = 0; i < ex.length(); i++) {		// 입력받은 수식의 길이만큼 반복
				char c = ex.charAt(i);					// 배열의 각각의 문자로 분리하여 c에 담아줌
				if(c == '-' || c == '+') {				// +이거나 -일 경우를 검사
					temp += c;							// String타입인 temp에 연산자만 저장
				}
			}
			// 문자열 배열로 리턴
			return temp.split("");						// 연산자만 들어있는 temp를 분리하여 문자열 배열로 리턴
		};
		
		String msg = "정수의 덧셈, 뺄셈에 대한 식을 입력하세요.";
		String exampleMessage = "예)9+7-5";
		String expression = null;
		String[] opers = null;
		String[] nums = null;
		int number1 = 0, number2 = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println(msg);
		System.out.println(exampleMessage);
		expression = sc.next();							// 수식 입력 받음
		
		opers = getOpers.checkOper(expression);			// getOpers의 checkOper 메소드에 수식을 매개변수로 전달
		nums = expression.split("\\+|\\-");				// 배열에서 정수를 뽑아내기 위해서 .split 이용하여 nums에 담아줌
		
		// -1 + 9 + 5
		// 음수를 해결하는 방법
		// 연산자는 값 사이에 존재하는 것으로 여기기 때문에 첫번째 숫자가 음수이면 -1이 아니라 " "와 1 사이의 연산자 -로 오해한다.
		// 그래서 [-][+][+]의 연산자 배열을 추출하고, [""][1][9][5]로 정수 배열을 추출한다.
		
		// 방법1. 정수배열의 가장 앞 인덱스에 0을 입력해준다. 0 - 1 + 9 + 5로 만들어준다.
		// 정수배열 nums[0]가 빈문자열("")와 같다면 첫번째 숫자가 음수란 뜻이기 때문에 "0"을 입력해주고
		// 아니라면 그대로 nums[0]을 유지한다.
//		nums[0] = nums[0].equals("") ? "0" : nums[0];				
		
		// 방법2. 빈 문자열인 인덱스를 건너뛰면서 직접 -1를 만들어준다.
		// 연산자배열 [-][+][+], 정수배열 [""][1][9][5]
		// 첫번째 인덱스 값이 빈 문자열("")이라면 첫번째 숫자가 음수라는 것으로
		// opers[0]인 '-'와 nums[1]인 '1'을 더해 -1을 만들어준다.
		// 빈 문자열이 아니라면 음수가 아니라는 뜻으로 그대로 nums[0]을 유지한다.
		number1 = Integer.parseInt(nums[0].equals("") ? opers[0] + nums[1] : nums[0]);
		
//		number1 = Integer.parseInt(nums[0]);
		
		for (int i = 0; i < opers.length; i++) {					// 연산자를 담고 있는 배열의 길이만큼 반복
			if(i == 0 && number1 < 0) {continue;}					// 2번의 방법에서 [-1][1][9][5]로 만들었으므로 하나의 인덱스를 건너뛰기 위한 if문
			number2 = Integer.parseInt(nums[i + 1]);				// number1의 다음 인덱스 값를 number2에 담아줌
			number1 = calculator(opers[i]).calc(number1, number2);	// calculator에 연산자를 전달하고 calc 메소드 구현 후 리턴받은 Calc 타입에
																	// 두 정수를 매개변수로 전달하여 number1가 number2가 연산한 결과값을 리턴받아 다시 number1에 담음
		}
		System.out.println(number1);
		
	}
}
