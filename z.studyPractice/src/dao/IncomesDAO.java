package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Collectors;

import vo.IncomesDTO;
import vo.ManIncomesVO;
import vo.WomanIncomesVO;

public class IncomesDAO {
	
	public ArrayList<ManIncomesVO> men;
	public ArrayList<WomanIncomesVO> women;
	
//	1. 평균 공기업 연봉 정보 2개를 기업별로 통합
//	2. 각 기업의 두 연봉 정보를 평균내고, 평균 연봉이 높은 순서대로 정렬
//	3. 전체 평균 연봉이 같다면 연봉 순위는 똑같이 나와야함
//	4. 연봉 정보 유무 탭을 만들어, 남/녀 연봉 중 하나라도 연봉 정보가 없다면 '정보 없음' 출력, 둘 다 있으면 '정보 있음' 출력
//	5. 연봉 정보가 없는 경우에는 해당 연봉을 0으로 기재
//	6. 연봉 정보가 하나만 있는 경우, 그 정보를 전체 평균 연봉으로 기재
	
	public void merge(String path1, String path2, String path3) throws IOException{
		// manIncomes.txt를 불러올 BufferedReader 생성
		BufferedReader manReader = DBConnecter.getReader(path1);
		// womanIncomes.txt를 불러올 BufferedReader 생성
		BufferedReader womanReader = DBConnecter.getReader(path2);
		// 두 파일을 병합하여 새로운 파일에 전달해줄 BufferedWriter 선언
		BufferedWriter bufferedWriter = null;
		
		// DAO의 메소드 사용을 위한 객체 선언
		ManIncomesDAO manIncomesDAO = new ManIncomesDAO();
		WomanIncomesDAO womanIncomesDAO = new WomanIncomesDAO();
		
		// VO 객체들을 담아줄 ArrayList 선언
		men = new ArrayList<ManIncomesVO>();
		women = new ArrayList<WomanIncomesVO>();
		
		String line = null, temp = "";
		
		while((line = manReader.readLine()) != null) {
			temp += line + "\n";
			men.add(manIncomesDAO.setObject(line));
		}
		manReader.close();
		
		while((line = womanReader.readLine()) != null){
			temp += line + "\n";
			women.add(womanIncomesDAO.setObject(line));
		}
		womanReader.close();
				
		// womanIncomes.txt와 manIncomes.txt에서 한 줄씩 담은 temp를 병합해주기 위한 BufferedWriter 생성
		bufferedWriter = DBConnecter.getWriter(path3);
		bufferedWriter.write(temp);
		bufferedWriter.close();
	}
	
	public IncomesDTO setVO(Object vo) {
		IncomesDTO incomesDTO = new IncomesDTO();
		
		if(vo instanceof ManIncomesVO) {
			String orNot = "";
			ManIncomesVO man = (ManIncomesVO)vo;
			incomesDTO.setCompany(man.getCompany());
			incomesDTO.setManIncome(man.getIncome());
			incomesDTO.setIncomeInfo(incomesDTO.getManIncome() != 0 && incomesDTO.getWomanIncome() != 0 ? "정보있음" : "정보없음");
		}
		if(vo instanceof WomanIncomesVO) {
			WomanIncomesVO woman = (WomanIncomesVO)vo;
			incomesDTO.setCompany(woman.getCompany());
			incomesDTO.setManIncome(woman.getIncome());
			incomesDTO.setIncomeInfo(incomesDTO.getManIncome() != 0 && incomesDTO.getWomanIncome() != 0 ? "정보있음" : "정보없음");
		}
		return incomesDTO;
	}
	
	public void updateRanking(String path) throws IOException{
		// 수정한 정보를 다시 병합한 파일로 전달해줄 BufferedWriter 생성
		BufferedWriter bufferedWriter = DBConnecter.getWriter(path);
		ArrayList<IncomesDTO> datas = new ArrayList<IncomesDTO>();
		ArrayList<IncomesDTO> results = new ArrayList<IncomesDTO>();
		// 전체 정보 중 연봉만을 담을 ArrayList 선언
		ArrayList<Integer> incomes = new ArrayList<Integer>();
		// 중복 제거를 위한 HashSet
		HashSet<Integer> incomeSet = null;
		int income = 0, ranking = 1, count = 0;
		String temp = "";
		
		men.stream().map(v -> setVO(v)).forEach(datas::add);
		
		for (IncomesDTO data : datas) {
			for (WomanIncomesVO woman : women) {
				income = 0;
				if(data.getCompany().equals(woman.getCompany())) {
					data.setWomanIncome(woman.getIncome());
					income += data.getManIncome();
					income += data.getWomanIncome();
					income /= 2;
					data.setTotalIncome(income);
				}
				if(data.getManIncome() != 0 && data.getWomanIncome() != 0) {
					data.setIncomeInfo("정보있음");
				}
			}
		}
		
		datas.stream().map(v -> v.getTotalIncome()).forEach(incomes::add);
		incomeSet = new HashSet<Integer>(incomes);
		incomes = new ArrayList<Integer>();
		incomeSet.stream().sorted(Collections.reverseOrder()).forEach(incomes::add);;
		
		for (Integer item : incomes) {
			count = 0;
			for (IncomesDTO data : datas) {
				if(data.getTotalIncome() == item) {
					data.setRanking(ranking);
					results.add(data);
					temp += data + "\n";
					count++;
				}
			}
			if(count > 1) {
				ranking += count - 1;
			}
			ranking++;
		}
		
		bufferedWriter.write(temp);
		bufferedWriter.close();
	}
}






