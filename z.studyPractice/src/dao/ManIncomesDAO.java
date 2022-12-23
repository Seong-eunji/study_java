package dao;

import vo.ManIncomesVO;

public class ManIncomesDAO {
	public ManIncomesVO setObject(String line) {
		String[] datas = line.split("\t");
		ManIncomesVO manIncomesVO = new ManIncomesVO();
		
		manIncomesVO.setCompany(datas[0]);
		manIncomesVO.setIncome((removeNull(datas)));
		
		return manIncomesVO;
	}
	
	public int removeNull(String[] datas) {
		return datas.length == 1 ? 0 : Integer.valueOf(datas[1]);
	}
}
