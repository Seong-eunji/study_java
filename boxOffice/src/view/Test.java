package view;

import java.io.IOException;

import dao.BoxOfficeDAO;
import vo.BoxOfficeVO;

public class Test {
	public static void main(String[] args) throws IOException {
		BoxOfficeDAO boxOfficeDAO = new BoxOfficeDAO();
		boxOfficeDAO.selectAll().forEach(System.out::println);
		
//		boxOfficeDAO.select("0").forEach(System.out::println);
		
//		if(boxOfficeDAO.delete(7)) {
//			System.out.println("삭제 성공");
//		}
		
//		BoxOfficeVO boxOfficeVO = boxOfficeDAO.select("아바타").get(0);
//		boxOfficeVO.setRanking(8);
//		boxOfficeVO.setName("아바타2");
		
//		if(boxOfficeDAO.update(boxOfficeVO)) {
//			System.out.println("수정 성공");
//		}else {
//			System.out.println("수정 실패");
//		}
		
//		BoxOfficeVO boxOfficeVO = new BoxOfficeVO();
//		boxOfficeVO.setRanking(7);
//		boxOfficeVO.setName("한동석짱2");
//		if(boxOfficeDAO.add(boxOfficeVO)) {
//			System.out.println("추가 성공");
//		}
	}
}
