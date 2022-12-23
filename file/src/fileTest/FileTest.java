package fileTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileTest {
	
	// throws를 붙이면 이 위치가 아닌 메소드를 사용하는 위치로 오류를 잡아서 던짐
	// 해당 위치가 아닌 메소드를 사용하여 예외가 발생하는 위치에서 catch하여 해결하도록 throw함
	public void a() throws IOException {
		BufferedWriter bufferedWriter1 = new BufferedWriter(new FileWriter(""));
		BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(""));
		BufferedWriter bufferedWriter3 = new BufferedWriter(new FileWriter(""));
	}
	
	public static void main(String[] args) throws IOException {
		// 절대 경로 : 어디에서 작성해도 찾아갈 수 있는 경로, C:/a/b(드라이브부터 시작)
		// 상대 경로 : 현재 위치에 따라 변경되는 경로, ../a/b(내가 있는 위치의 이전폴더부터 시작)

		// BufferedWriter는 Writer 타입을 매개로 받기 때문에
		// FileWriter 생성자를 사용
		// 외부에 접근할 때에는 항상 inputOutputException이 발생
//		try {
//			// false하면 새로 비워준 상태로 전달
//			// false가 기본값이기 때문에 true를 전달하면 기존의 텍스트를 저장한 상태로 전달
//			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test.txt", true));
//			bufferedWriter.write("한동석\n");		// \n을 구분점
//			bufferedWriter.write("한동석");
//			bufferedWriter.newLine();			// \r\n을 구분점, ex) .split(\r\n)
//			bufferedWriter.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

//		File file = new File("text.txt");
//		try {
//			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//			String line = null;
//			while((line = bufferedReader.readLine()) != null) {	// 가져온 문자열이 있으면(가져올 문자열이 없으면 null값을 갖고 있음)
//				System.out.print(line);							// 출력해보면 줄바꿈문자를 가지고 오지 않음
//			}
//			bufferedReader.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("해당 경로는 존재하지 않습니다.");
//		}
//		
//		if(file.exists()) { // 해당 경로가 존재하면
//			System.out.println(file.delete());	// 삭제
//		}
		
		// 수정
//		try {
//			BufferedReader bufferedReader = new BufferedReader(new FileReader("fish.txt"));
//			String line = null, temp = "";
//			
//			while((line = bufferedReader.readLine()) != null) {		// 가져온 문자열이 있다면(가져올 문자열이 없으면 null값을 갖고 있음)
//				if(line.equals("갈치")) {								// 가져온 문자열이 "갈치"라면
//					temp += "연어\n";									// "갈치" 대신 "연어" 입력
//					continue;										// 갈치가 temp에 들어갈 수 없게 countinue
//				}
//				temp += line + "\n";								// "갈치"와 일치하지 않는다면 가져온 문자열 그대로 temp에 담아줌
//			}
//			
//			bufferedReader.close();
//			
//			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("fish.txt"));
//			bufferedWriter.write(temp);								// temp를 fish.txt에 전달
//			bufferedWriter.close();									// close()하면서 flush()해줌
//			
//		} catch (FileNotFoundException e) {
//			System.out.println("없는 경로입니다.");
//		} 
		
		// 고등어 삭제
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("fish.txt"));
			String line = null, temp = "";
			
			while((line = bufferedReader.readLine()) != null) {			// 가져온 문자열이 있다면(가져올 문자열이 없으면 null값을 갖고 있음)
				if(line.equals("고등어")) {								// 가져온 문자열이 "고등어"라면
					continue;											// temp에 저장하지 않고 continue시킴
				}
				temp += line + "\n";									// "고등어"와 일치하지 않는다면 가져온 문자열 그대로 temp에 담아줌
			}
			bufferedReader.close();
			
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("fish.txt"));
			bufferedWriter.write(temp);								// temp를 fish.txt에 전달						
			bufferedWriter.close();									// close()하면서 flush()해줌
			
		} catch (FileNotFoundException e) {
			System.out.println("없는 경로입니다.");
		}
	}
}
