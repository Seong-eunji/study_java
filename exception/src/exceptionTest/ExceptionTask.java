package exceptionTest;

import java.util.Scanner;

public class ExceptionTask {
	public static void main(String[] args) {
		// 5개의 정수만 입력받기
		// - 무한 입력 상태로 구현
		// - q 입력 시 나가기
		// - 5개의 정수는 배열에 담기
		// - if문은 딱 한 번만 사용하기

//		int[] arrData = new int[5];
//		int i = 0;
//		String input = "";
//
//		Scanner sc = new Scanner(System.in);
//
//		while (true) {
//			System.out.print(i + 1 + "번째 정수 입력: ");
//			input = sc.next();
//			if (input.equals("q")) {break;}
//			
//			try {
//				arrData[i] = Integer.parseInt(input);
//				i++;
//
//			} catch (Exception e) {
//				System.out.println("배열 길이를 초과했습니다.");
//				break;
//			}
//		}
//		System.out.println("끝");
	
		
		// 강사님 코드
		Scanner sc = new Scanner(System.in);
		int[] arData = new int[5];
		String msg = "번째 정수 : ", temp = null;
		int i = 0;
		
		while(true) {
			System.out.print(++i + msg);
			temp = sc.next();
			if(temp.equals("q")) {break;}
			
			try {
				arData[i-1] = Integer.parseInt(temp);
				
			} catch(NumberFormatException e) {					// 정수가 아닌 값을 입력했을 때의 오류 
				System.out.println("입력하기 전에 생각이란 걸 해봤나요? 제대로 입력하세요.");
				i--;
				
			} catch(ArrayIndexOutOfBoundsException e) {			// 배열의 범위를 벗어났을 때의 오류
				System.out.println("5개만 입력 가능합니다.");
				for (int j = 0; j < arData.length; j++) { 		// 입력이 완료된 배열 출력
					System.out.print(arData[j] + " ");
				}
				break;
				
			} catch(Exception e) {								// 그 외의 오류 발생 시
				System.out.println("?");
			}
		}
	
	}	
}
