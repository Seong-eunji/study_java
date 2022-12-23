package fileTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileTask {
	public static void main(String[] args) throws IOException {
		// 고등어, 갈치, 꽁치, 전어 ---> 0, 1, 2, 3
		// 배열로 출력하고 전체 내용 가져와서 콘솔에 출력하기
		String[] fishes = {"고등어", "갈치", "꽁치", "전어"};

		// 배열에 있는 문자열을 한 줄 씩 fish.txt에 입력
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("fish.txt")); 
		for (String fish : fishes) {														// 빠른 for문 사용하여 fishes 배열에 있는 fish 꺼내오기
			bufferedWriter.write(fish + "\n");												// 꺼내온 fish를 writer를 이용하여 경로로 지정한 txt에 한줄씩 쓰기
		}
		bufferedWriter.close();																// 쓰기가 끝났으면 저장을 위해 close();
		
		// 배열로 출력하고 전체 내용 가져와서 콘솔에 출력하기
		// fish.txt에 담긴 문자열 한 줄씩 가져와 출력하기
		try {																				// 외부에 접근할 때에는 오류 발생하는 것을 막기 위해 try~catch문 사용
			BufferedReader bufferedReader = new BufferedReader(new FileReader("fish.txt"));
			String line = null;																// fish.txt에서 한 줄씩 가져와 담아줄 변수 선언
			while((line = bufferedReader.readLine()) != null) {								// 더 이상 가져올 문자열이 없을 때까지
				System.out.println(line);													// 콘솔창에 한 줄 씩 출력
			}
			bufferedReader.close();															// 읽어오기가 끝났으면 close();
		} catch (FileNotFoundException e) {													// 파일을 찾지 못했을 경우
			System.out.println("없는 경로입니다.");												// 없는 경로입니다. 출력
		}
	}
}
