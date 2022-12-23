package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import vo.BoxOfficeVO;

public class BoxOfficeDAO {

	// 추가와 삽입
	/**
	 * 
	 * @param boxOfficeVO
	 * @return -1 : 추가 실패 0 : 삽입 실패 1 : 성공
	 * 
	 * @throws IOException
	 */
	public int add(BoxOfficeVO boxOfficeVO) throws IOException {
		return boxOfficeVO.getRanking() == 0 ? append(boxOfficeVO) ? 1 : -1 : insert(boxOfficeVO) ? 1 : 0;
	}

	// 추가
	// 화면에서 입력한 영화정보를 Model 객체로 전달받는다.
	// 마지막에 추가하는 정보이므로 순위는 전달받지 않는다.
	private boolean append(BoxOfficeVO boxOfficeVO) throws IOException {
		BufferedReader bufferedReader = DBConnecter.getReader();
		BufferedWriter bufferedWriter = DBConnecter.getWriter();
		String content = null, newLine = null, line = null;
		int lastRanking = 0; // 마지막 랭킹에 새로운 영화를 추가해주기 위해 원래의 영화 개수를 알아내기 위한 변수
		boolean check = true;

		while ((line = bufferedReader.readLine()) != null) {
			// 기존 정보 중 순위를 제외한 5개 정보
			String exclusiveRankingOrigin = line.substring(line.indexOf("\t")); // 원본 데이터에서 랭킹을 제외한 값
			// 전달받은 정보 중 순위를 제외한 5개 정보
			String exclusiveRankingNew = boxOfficeVO.toString().substring(boxOfficeVO.toString().indexOf("\t"));
																														
			// 새롭게 전달받은 정보 5개와 기존 정보 5개가 일치하면 중복된 정보이므로
			if (exclusiveRankingOrigin.equals(exclusiveRankingNew)) { // 두 값이 같다면
				// FLAG를 false로 변경한다.
				check = false; // 추가 실패
			}
			// 총 반복 횟수가 곧 마지막 순위이다.
			lastRanking++; // while의 반복 횟수만큼이 곧 영화 개수이므로 ++
		}
		bufferedReader.close();

		// 새로운 마지막 순위를 연산하여 전달받은 정보 중 순위에 넣어준다.
		boxOfficeVO.setRanking(lastRanking + 1); // 마지막 랭킹 위치 + 1

		// 전달한 경로의 파일 내용 전체를 byte[]로 가져온다.
		// byte[]를 String 생성자에 전달하여 디코딩한다.
		content = new String(Files.readAllBytes(Paths.get(DBConnecter.PATH))); // 해당 경로에 있는 모든 내용을 byte 배열로 모두 읽어옴
																				// byte[]는 읽기 힘드므로 String으로 바꿔서 담아줌

		// 가져온 전체 내용 중 가장 마지막 문자가 줄바꿈 문자인지 검사한다.
		// 줄바꿈 문자가 아니라면 newLine에 줄바꿈 문자를 넣어준다.
		newLine = content.charAt(content.length() - 1) == '\n' ? "" : "\n"; // 마지막 문자가(길이값 - 1)이 줄바꿈 문자인지 아닌지를 확인
																			// 줄바꿈이면 아무것도 추가하지 않고, 아니라면 줄바꿈을 추가함(그 다음에
																			// 새로운 줄을 추가해야 하므로)

		// 줄바꿈 문자 유무 검사의 결과를 새롭게 추가할 정보 앞에 연결해준다.
		bufferedWriter.write(boxOfficeVO.toString()); // write라는 메소드는 toString이 자동으로 붙지 않기 때문에 직접 붙여줘야함
		bufferedWriter.close();

		return check;
	}

	// 삽입
	// 화면에서 입력한 전체 정보를 Model 객체로 전달받는다.
	private boolean insert(BoxOfficeVO boxOfficeVO) throws IOException {
		BufferedWriter bufferedWriter = null;
		BufferedReader bufferedReader = DBConnecter.getReader();
		String line = null, temp = "";
		// 전달받은 순위를 newRanking에 담아준다.
		int newRanking = boxOfficeVO.getRanking();
		boolean check = false;

		while ((line = bufferedReader.readLine()) != null) {
			// 기존 정보 중 순위를 새롭게 전달받은 순위와 비교한다.
			if (Integer.valueOf(line.split("\t")[0]) == boxOfficeVO.getRanking()) {
				// 해당 순위에 새로운 정보를 삽입해준다.
				temp += boxOfficeVO + "\n";
				// FLAG는 true로 변경해준다.
				check = true;
			}
			// FLAG가 true라면 삽입된 후이기 때문에, 새로운 순위로 수정해주어야 한다.
			// 삽입할 순위 + 1 부터 1씩 증가시키면 모두 1순위씩 밀려나게 된다.
			temp += check ? ++newRanking + line.substring(line.indexOf("\t")) : line; // 추가하고 싶은 위치 다음부분부터는 check = true;
			temp += "\n"; // substring은 원하는 인덱스부터 String을 자를 수 있음
						  // 처음 \t를 만난 부분(랭킹을 제외한 부분)과 ++newRanking을 temp에 담음
		}
		bufferedReader.close();

		bufferedWriter = DBConnecter.getWriter();
		// 삽입 및 순위 수정이 모두 완료된 temp를 덮어씌워준다.
		bufferedWriter.write(temp);
		bufferedWriter.close();

		return check; // check가 true일 때 삽입 성공, false일 때 삽입 실패
	}

	// 수정
	// 화면에서 수정된 전체 정보를 Model 객체로 전달받는다.
	public boolean update(BoxOfficeVO boxOfficeVO) throws IOException { // 수정이 다 된 객체를 전달받음
		BufferedReader bufferedReader = DBConnecter.getReader();
		BufferedWriter bufferedWriter = null;
		String line = null, temp = "";
		boolean check = false;

		while ((line = bufferedReader.readLine()) != null) {
			// 기존 정보 중 순위를 비교하여
			if (Integer.valueOf(line.split("\t")[0]) == boxOfficeVO.getRanking()) {
				// 순위가 같다면 수정 대상이기 때문에
				// 기존 정보 대신 새롭게 수정된 정보를 temp에 담아준다
				temp += boxOfficeVO.toString();
				// FLAG는 true로 변경한다.
				check = true;
				// 기존 정보는 담아주면 안되기 때문에 continue로 막아준다.
				continue;
			}
			// 수정 대상이 아닌 정보들은 그대로 temp에 담아준다.
			temp += line + "\n";
		}
		bufferedReader.close();

		// 수정 완료된 정보를 기존 문서에 덮어씌워준다.
		bufferedWriter.write(temp);
		bufferedWriter.close();

		return check;
	}

	// 삭제
	// 외부에서 삭제할 순위를 전달받는다.
	public boolean delete(int ranking) throws IOException {
		BufferedReader bufferedReader = DBConnecter.getReader();
		BufferedWriter bufferedWriter = null;
		String line = null, temp = "";
		int deletedRanking = ranking;
		boolean check = false;

		while ((line = bufferedReader.readLine()) != null) {
			// 기존 정보 중 순위를 비교하여
			if (Integer.valueOf(line.split("\t")[0]) == ranking) {
				check = true;
				continue;
			}
			// 수정 대상이 아닌 정보들은 그대로 temp에 담아준다.
			temp += check ? deletedRanking++ + line.substring(line.indexOf("\t")) : line;
			temp += line + "\n";
		}
		bufferedReader.close();

		bufferedWriter = DBConnecter.getWriter();
		// 수정 완료된 정보를 기존 문서에 덮어씌워준다.
		bufferedWriter.write(temp);
		bufferedWriter.close();

		return check;
	}

	// 조회
	public ArrayList<BoxOfficeVO> select(String name) throws IOException {		// 전달받은 name 문자열을 포함하는 여러 영화에 대한 리스트를 리턴해주기 위해서 ArrayList 리턴타입 사용
		BufferedReader bufferedReader = DBConnecter.getReader();
		ArrayList<BoxOfficeVO> boxOfficeVOs = new ArrayList<BoxOfficeVO>();
		String line = null;
		int i = 0;

		while ((line = bufferedReader.readLine()) != null) {
			String[] datas = line.split("\t");
			if (datas[1].contains(name)) {														// 전달받은 이름을 포함하고 있으면
				BoxOfficeVO boxOfficeVO = new BoxOfficeVO();									// VO객체(모델 객체) 생성
				boxOfficeVO.setRanking(Integer.valueOf(datas[i++]));							// 랭킹 입력, int형 형변환
				boxOfficeVO.setName(datas[i++]);												// 이름 입력
				boxOfficeVO.setReleaseDate(datas[i++]);											// 발매날짜 입력
				boxOfficeVO.setIncome(Long.valueOf(removeComma(datas[i].equals("") ? "0" : datas[i++])));	// 숫자의 크기가 커서 long 타입 사용, 해당 칸이 공백이면 0, 아니면 datas[i++]
				boxOfficeVO.setGuestCount(Integer.valueOf(removeComma(removeS(datas[i++]))));	// 관객수 입력, int형 형변환, 콤마와 S마크 지우는 메소드 사용
				boxOfficeVO.setScreenCount(Integer.valueOf(removeComma(removeS(datas[i++]))));	// 스크린수 입력, int형 형변환, 콤마와 S마크 지우는 메소드 사용
				boxOfficeVOs.add(boxOfficeVO);													// VO객체 배열에 VO객체 추가
				i = 0;
			}
		}
		return boxOfficeVOs;

	}

	// 목록

	public ArrayList<BoxOfficeVO> selectAll() throws IOException {
		BufferedReader bufferedReader = DBConnecter.getReader();
		ArrayList<BoxOfficeVO> boxOfficeVOs = new ArrayList<BoxOfficeVO>();
		String line = null;
		int i = 0;

		while((line = bufferedReader.readLine()) != null) {
			String[] datas = line.split("\t");
			BoxOfficeVO boxOfficeVO = new BoxOfficeVO();
			boxOfficeVO.setRanking(Integer.valueOf(datas[i++]));
			boxOfficeVO.setName(datas[i++]);
			boxOfficeVO.setReleaseDate(datas[i++]);
			boxOfficeVO.setIncome(Long.valueOf(removeComma(datas[i].equals("") ? "0" : datas[i++])));
			boxOfficeVO.setGuestCount(Integer.valueOf(removeComma(removeS(datas[i].equals("") ? "0" : datas[i++]))));
			boxOfficeVO.setScreenCount(Integer.valueOf(removeComma(removeS(datas[i].equals("") ? "0" : datas[i++]))));
			boxOfficeVOs.add(boxOfficeVO);
			i = 0;
		}
		return boxOfficeVOs;
	}

	public String removeComma(String data) {		// 숫자에서 ,를 제거하기 위한 메소드
		return data.replaceAll(",", "");
	}

	public String removeS(String data) {			// 'S'로 표시된 마크를 없애기 위한 메소드
		return data.replaceAll("S ", "");
	}

}
