package vo;

public class ManIncomesVO {
	private String company;
	private int income;
	
	public ManIncomesVO() {;}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}
	
	@Override
	public String toString() {
		String str = company + "\t" + income;
		return str;
	}
}
