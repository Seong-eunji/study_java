package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

import vo.BoyVO;
import vo.GirlVO;
import vo.NameDTO;

public class NameDAO {

	public ArrayList<BoyVO> boys;
	public ArrayList<GirlVO> girls;

	// 병합
	// 두 개의 파일을 하나의 새로운 파일로 병합한다.
	// path1 : 기존 파일 경로 1
	// path2 : 기존 파일 경로 2
	// path3 : 병합된 내용을 출력할 경로
	// 각각의 경로를 매개로 받았으면 각각의 경로에 대한 버퍼를 설정해주어야 함
	public void merge(String path1, String path2, String path3) throws IOException {
		BufferedReader boyReader = DBConnecter.getReader(path1);
		BufferedReader girlReader = DBConnecter.getReader(path2);
		BufferedWriter bufferedWriter = null;

		BoyDAO boyDAO = new BoyDAO();
		GirlDAO girlDAO = new GirlDAO();

//      남자 정보 저장
		boys = new ArrayList<BoyVO>();
//      여자 정보 저장
		girls = new ArrayList<GirlVO>();

		String line = null, temp = "";

		while ((line = boyReader.readLine()) != null) {
			temp += line + "\n";
			boys.add(boyDAO.setObject(line));
		}

		boyReader.close();

		while ((line = girlReader.readLine()) != null) {
			temp += line + "\n";
			girls.add(girlDAO.setObject(line));
		}

		girlReader.close();

//      새로운 파일 생성 후 출력
		bufferedWriter = DBConnecter.getWriter(path3);
		bufferedWriter.write(temp);
		bufferedWriter.close();
	}

	// 랭킹 수정
	// 병합된 파일의 경로를 전달받는다.
	public void update(String path) throws IOException {
		BufferedWriter bufferedWriter = DBConnecter.getWriter(path);
		// boys, girls 두 ArrayList를 병합할 ArrayList
		// 두 개의 타입이 다르므로 Object로 생성
		ArrayList<Object> datas = new ArrayList<Object>();
		// 전체 정보 중 인구 수만 담아줄 ArrayList
		// population이 랭킹 기준이기 때문에 따로 내림차순해주기 위한 배열
		ArrayList<Integer> populations = new ArrayList<Integer>();
		// 인구 수 중에서 중복을 제거할 HashSet
		HashSet<Integer> populationSet = null;

		int ranking = 1;
		int count = 0;
		String temp = "";

		// boys, girls를 datas에 병합(합치기)
		datas.addAll(boys);
		datas.addAll(girls);

		// 각 인구수를 전부 populations에 담기
		boys.stream().map(v -> v.getPopulation()).forEach(populations::add);
		girls.stream().map(v -> v.getPopulation()).forEach(populations::add);

		// 인구 수 중복 제거
		populationSet = new HashSet(populations);
		// 순서 부여
		populations = new ArrayList<Integer>(populationSet);
		// 중복이 제거된 인구수 내림차순 정렬
		populations = (ArrayList<Integer>) populations.stream().sorted(Collections.reverseOrder())
				.collect(Collectors.toList());

		// 인수 하나씩 순서대로 population에 담기
		for (Integer population : populations) {
			count = 0;	
			// 모든 정보(남자 + 여자)를 하나씩 vo에 담아준다.
			for (Object vo : datas) {
				if (vo instanceof BoyVO) {
					// population필드에 접근하기 위해서 down casting 수행
					BoyVO boyVO = (BoyVO) vo;
					// 이미 내림차순으로 정렬된 남자아이의 인구 수를 전체 정보에서 비교한다.
					if (population == boyVO.getPopulation()) {
						// 해당 인구를 가진 객체를 찾았다면
						NameDTO nameDTO = new NameDTO();

						// NameDTO에 넣어준다.
						nameDTO.setName(boyVO.getName());
						nameDTO.setGender("B");
						nameDTO.setRanking(ranking);
						nameDTO.setPopulation(boyVO.getPopulation());

						// 해당 결과를 temp에 누적해준다.
						temp += nameDTO + "\n";
						// 남자 아이와 여자 아이 둘 다 동일한 인구 수일 경우 count는 2가 된다.
						count++;
					}
				}

				if (vo instanceof GirlVO) {
					GirlVO girlVO = (GirlVO) vo;
					if (girlVO.getPopulation() == population) {
						NameDTO nameDTO = new NameDTO();

						nameDTO.setName(girlVO.getName());
						nameDTO.setGender("G");
						nameDTO.setRanking(ranking);
						nameDTO.setPopulation(girlVO.getPopulation());
						
						temp += nameDTO + "\n";
						count++;
					}
				}
			}
			
//         공동 인구 수가 존재하면,
//         공동 인구 수만큼을 다음 랭킹에 반영한다.
			if (count > 1) {
				ranking += count - 1;	// 아래의 ranking++을 뺄 수 없으므로 그 경우를 감안하여 -1
			}
			ranking++;	// 공동 순위가 아닐 경우를 위한 랭킹 증가
			
//			if(count > 1) {
//				ranking += count;
//			} else {
//				ranking++;
//			}
		}
		bufferedWriter = DBConnecter.getWriter(path);
		bufferedWriter.write(temp);
		bufferedWriter.close();
	}
}
