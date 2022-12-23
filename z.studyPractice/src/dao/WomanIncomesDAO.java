package dao;

import vo.WomanIncomesVO;

public class WomanIncomesDAO {
	public WomanIncomesVO setObject(String line) {
		String[] datas = line.split("\t");
		WomanIncomesVO womanIncomesVO = new WomanIncomesVO();

		womanIncomesVO.setCompany(datas[0]);
		womanIncomesVO.setIncome(removeNull(datas));

		return womanIncomesVO;
	}
	
	public int removeNull(String[] datas) {
		return datas.length == 1 ? 0 : Integer.valueOf(datas[1]);
	}
}
