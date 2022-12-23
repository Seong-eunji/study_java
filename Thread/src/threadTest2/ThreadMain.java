package threadTest2;

import java.util.Scanner;

public class ThreadMain {
	public static void main(String[] args) {
		String[] number = {"first", "second", "third"};					// 콘솔에 출력할 문자열 배열 선언
		Thread[] threads = new Thread[number.length];					// 각각의 문자열을 출력할 쓰레드 배열을 number의 길이값만큼 선언
		ThreadTask task = new ThreadTask();
		
		Scanner sc = new Scanner(System.in);
		int choice = 0;

		System.out.print("입력 : ");										// 문자열 출력 순서를 입력받음
		
		Runnable runner = () -> {										// runner 객체를 람다식, 익명클래스로 구현			
			System.out.println(Thread.currentThread().getName());		// 들어온 쓰레드의 이름 출력
		};
		
		for (int i = 0; i < threads.length; i++) {						// 쓰레드 배열의 길이만큼 반복
			choice = sc.nextInt();										// 값 입력
			threads[i] = new Thread(runner, number[choice-1]);			// 쓰레드에 Runnable 타입인 객체 runner 전달, number의 i번째 문자열(인덱스 i-1)을 쓰레드의 이름으로 전달
		}

		
		for (int i = 0; i < threads.length; i++) {						// 쓰레드를 시작시키기 위한 반복문
			threads[i].start();
		}
		
		
		
		System.out.print("입력 : ");										// 문자열 출력 순서 입력 받음
		
		for (int i = 0; i < 3; i++) {		
			Runnable run = () -> {										// Runnable 타입인 객체 run
				System.out.println(number[sc.nextInt() - 1]);			// number의 i번째 문자열(인덱스 i-1)을 출력하도록 람다식으로 구현
			};			
			
			if(i == 0) {												// i가 0이면, first를 출력하고 싶으면
				task.printFirst(run);									// ThreadTask 클래스의 printFirst 메소드에 run 전달
			} else if(i ==1) {
				task.printSecond(run);
			} else {
				task.printThird(run);
			}
		}
	
	}	
}
