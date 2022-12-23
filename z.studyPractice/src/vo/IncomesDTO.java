package vo;

public class IncomesDTO {
	private int ranking;
	private String company;
	private String incomeInfo;	
	private int manIncome;
	private int womanIncome;
	private int totalIncome;
	
	public IncomesDTO() {;}
	
	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String isIncomeInfo() {
		return incomeInfo;
	}

	public void setIncomeInfo(String incomeInfo) {
		this.incomeInfo = incomeInfo;
	}

	public int getManIncome() {
		return manIncome;
	}

	public void setManIncome(int manIncome) {
		this.manIncome = manIncome;
	}

	public int getWomanIncome() {
		return womanIncome;
	}

	public void setWomanIncome(int womanIncome) {
		this.womanIncome = womanIncome;
	}

	public int getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(int totalIncome) {
		this.totalIncome = totalIncome;
	}

	@Override
	public String toString() {
		String str = ranking + "\t"
					+ company + "\t"
					+ incomeInfo + "\t"
					+ manIncome + "\t"
					+ totalIncome + "\t"
					+ womanIncome + "\t";
		
		return str;
	}
}
