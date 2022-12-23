package methodTest;

public class MethodTask {

	// 1 ~ 10까지 println()으로 출력하는 메소
	void printNumber() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i + 1);
		}
	}

	
	// "홍길동"을 n번 println()으로 출력하는 메소드
	void printHonggildong(int n) {
		for (int i = 0; i < n; i++) {
			System.out.println("홍길동");
		}
	}

	
	// 이름을 n번 println()으로 출력하는 메소드
	void printName(String name, int n) {
		for (int i = 0; i < n; i++) {
			System.out.println(name);
		}
	}

	
	// 세 정수의 뺄셈을 해주는 메소드
	int minus(int num1, int num2, int num3) {
		int result = 0;
		if (num1 > num2 && num1 > num3) {
			result = num1 - num2 - num3;
		} else if (num2 > num3) {
			result = num2 - num1 - num3;
		} else {
			result = num3 - num1 - num2;
		}
		return result;
	}

	
//	int minus(int num1, int num2, int num3) {
//		int result = 0;
//		result = num1 > num2 && num1 > num3 ? num1 - num2 - num3 :
//			num2 > num3 ? num2 - num1 - num3 : num3 - num1 - num2;
//		return result;
//	}

//	// 강사님 코드
//	int substract(int firstNumber, int middleNumber, int lastNumber) {
//		return firstNumber - middleNumber - lastNumber;
//	}

	
	// 두 정수의 나눗셈을 해주는 메소드(몫과 나머지 둘 다)
	int[] division(int num1, int num2) {
		int[] result = new int[2];
		result[0] = num1 / num2;
		result[1] = num1 % num2;
		return result;
	}

//	// 강사님 코드
//	int[] divide(int num1, int num2) {
//		int[] result = null;
//		if (num2 != 0) {
//			result = new int[2];
//
//			result[0] = num1 / num2;
//			result[1] = num1 % num2;
//		}
//		return result;
//	}
//	
//	====================================================

	
	// 1 ~ n까지의 합을 구해주는 메소드
	int getTotal(int n) {
		int total = 0;
		for (int i = 0; i < n; i++) {
			total += i + 1;
		}
		return total;
	}

//	// 강사님 코드
//	int getTotalFrom1(int end){
//		int total = 0;
//		for (int i = 0; i < end; i++) {
//			total += i + 1;
//		}
//		return total;
//	}

	
	// 홀수를 짝수로, 짝수를 홀수로 바꿔주는 메소드
//	int changeNumber(int n) {
//		int result = 0;
//		if (n % 2 == 0) {
//			result = n * 2 + 1;
//		} else {
//			result = n * 2;
//		}
//		return result;
//	}

	int changeNumber(int n) {
		return n + 1;
	}

//	// 강사님 코드
//	int change(int number) {
//		return ++number;
//	}

	
	// 문자열을 입력받고 소문자는 대문자로, 대문자는 소문자로 바꿔주는 메소드
	String changeStr(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c > 64 && c < 91) {
				result += (char) (c + 32);
			} else if (c > 96 && c < 123) {
				result += (char) (c - 32);
			} else {
				result += c;
			}
		}
		return result;
	}

//	// 강사님 코드
//	String changeString(String string) {
//		String result = "";
//		
//		for (int i = 0; i < string.length(); i++) {
//			char c = string.charAt(i);
//
//			if (c >= 65 && c <= 90) {
//				result += (char)(c + 32);
//			} else if (c >= 97 && c < 122) {
//				result += (char)(c - 32);
//			} else {
//				result += c;
//			}
//		}
//		return result;
//	}

	
	// 문자열을 입력받고 원하는 문자의 개수를 구해주는 메소드
	int countChar(String str, char ch) {
		int result = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == ch) {
				result++;
			}
		}
		return result;
	}

//	// 강사님 코드
//	int getCount(String string, char c) {
//		int count = 0;
//		for (int i = 0; i < string.length(); i++) {
//			if(string.charAt(i) == c) {
//				count++;
//			}
//		}
//		return count;
//	}

	
	// 5개의 정수를 입력받은 후 원하는 번째 값을 구해주는 메소드
	int lookforIndex(int[] arr, int number) {
		return arr[number - 1];
	}

//	//강사님 코드
//	int getValue(int[] nums, int wantedNumber){
//		return nums[wantedNumber - 1];
//	}

	
	// 한글을 정수로 바꿔주는 메소드(indexOf 사용)
	int changeHantoNum(String hangeul) {
		String str = "공일이삼사오육칠팔구";
		int result = 0;

		for (int i = 0; i < hangeul.length(); i++) {
			int index = str.indexOf(hangeul.charAt(i));
			result = result * 10 + index;
		}
		return result;
	}

	// 강사님 코드
//	int changeToInteger(String hangle){
//		String hangleOriginal = "공일이삼사오육칠팔구", result = "";
//		for (int i = 0; i < hangle.length(); i++) {
//			result += hangleOriginal.indexOf(hangle.charAt(i));
//		}
//		return Integer.parseInt(result);
//	}

	
	// 5개의 정수를 입력받고 최대값과 최소값을 구해주는 메소드
	int[] lookforMaxMin(int[] arr) {
		int[] result = new int[2];
		int max = arr[0], min = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (max < arr[i]) {
				max = arr[i];
			}
			if (min > arr[i]) {
				min = arr[i];
			}
		}
		result[0] = max;
		result[1] = min;

		return result;
	}
	
//	// 강사님 코드
//	int[] getMaxAndMin(int[] nums){
//		int[] results = new int[2];
//		results[0] = nums[0];	// 최대값
//		results[1] = nums[0];	// 최소값
//		
//		for (int i = 0; i < nums.length; i++) {
//			if(results[0] < nums[i]) {
//				results[0] = nums[i];
//			}
//			if(results[1] > nums[i]) {
//				results[1] = nums[i];
// 			}
//		}
//		return results;
//	}

	
	// 어려움★★★★★
	// 5개의 정수를 입력받고 최대값과 최소값을 구해주는 메소드(void로 하되, 출력 없이 사용하는 부분에 결과를 전달한다.)
	// 매개변수는 2개 전달해야 한다.
	// 예) getMaxAndMin();
	// 여기서부터 최대값, 최소값 사용 가능
	// Hint) 그냥 변수를 이용하면 같은 이름의 변수를 선언할 수도 지역변수라 값이 저장되지도 않기 때문에
	// 배열을 이용하여 주소값을 이용하여 값을 대입시켜 유지한다.
	void getMaxAndMin(int[] arr, int[] results) {
		int max = arr[0], min = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (max < arr[i]) {
				max = arr[i];
			}
			if (min > arr[i]) {
				min = arr[i];
			}
		}
		results[0] = max;
		results[1] = min;
	}
	
//	// 강사님 코드
//	void getMaxAndMin(int[] nums, int[] results) {
//		results[0] = nums[0];	// 최대값
//		results[1] = nums[0];	// 최소값
//		
//		for (int i = 0; i < nums.length; i++) {
//			if(results[0] < nums[i]) {
//				results[0] = nums[i];
//			}
//			if(results[1] > nums[i]) {
//				results[1] = nums[i];
// 			}
//		}
//	}
	

	public static void main(String[] args) {
		MethodTask mt = new MethodTask();

//		mt.printNumber();
//		mt.printHonggildong(3);
//		mt.printName("은지", 4);
//		System.out.println(mt.minus(9, 3, 4));
//		for (int i = 0; i < 2; i++) {
//			int[] arr = mt.division(5, 2);
//			System.out.print(arr[i] + " ");
//		}

//		// 강사님 코드
//		int[] results = mt.divide(10, 3);
//		String msg = null;
//		msg = results == null ? "0으로 나눌 수 없습니다." : "몫 : " + results[0] + "\n나머지 : " + results[1];
//
//		System.out.println(msg);
//
//		====================================================

		
		System.out.println(mt.getTotal(10));
		System.out.println(mt.changeNumber(7));
		System.out.println(mt.changeStr("AbCd!"));
		System.out.println(mt.countChar("abcccdr", 'c'));

		int[] arr = { 1, 2, 3, 4, 5 };
		System.out.println("찾는 숫자 : " + mt.lookforIndex(arr, 1));

		System.out.println(mt.changeHantoNum("일공이사"));

		int[] result = mt.lookforMaxMin(arr);
		System.out.println("최대값 : " + result[0] + ", 최소값 : " + result[1]);

		int[] results = new int[2];
		mt.getMaxAndMin(arr, results);
		System.out.println("최대값 : " + results[0] + ", 최소값 : " + results[1]);
		
		
		// 강사님 코드
//      1~n까지의 합을 구해주는 메소드
//      int getTotalFrom1(int end)
//      System.out.println(mt.getTotalFrom1(10));
      
//      홀수를 짝수로, 짝수를 홀수로 바꿔주는 메소드
//      int change(int number)
//      boolean result = mt.change(11) % 2 == 0;
//      System.out.println(result ? "홀수에서 짝수로 바뀌었습니다." : "짝수에서 홀수로 바뀌었습니다.");
      
//      문자열을 입력받고 소문자는 대문자로, 대문자는 소문자로 바꿔주는 메소드
//      String changeString(String string)
//      System.out.println(mt.changeString("aPplE1234!@#$"));
   
//      문자열을 입력받고 원하는 문자의 개수를 구해주는 메소드
//      int getCount(String string, char c)
//      System.out.println(mt.getCount("apple", 'p') + "개");
      
//      5개의 정수를 입력받은 후 원하는 번째 값을 구해주는 메소드
//      int getValue(int[] nums, int wantedNumber)
//      int[] arData = {1, 2, 5, 7, 8};
//      System.out.println(mt.getValue(arData, 3));
      
//      한글을 정수로 바꿔주는 메소드
//      int changeToInteger(String hangle)
//      int result = mt.changeToInteger("삼공오이사");
//      System.out.println(result - 3);
      
//      5개의 정수를 입력받고 최대값과 최소값을 구해주는 메소드
//      void getMaxAndMin(int[] nums, int[] results)
//		int[] arData = {1, 2, 5, 7, 8};
//		int[] results = new int[2];
//		mt.getMaxAndMin(arData, results);
//		System.out.println("최대값 : " + results[0]);
//		System.out.println("최소값 : " + results[1]);


	}
}
