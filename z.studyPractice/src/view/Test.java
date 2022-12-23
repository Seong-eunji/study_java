package view;

import java.io.IOException;

import dao.IncomesDAO;

public class Test {
	public static void main(String[] args) throws IOException{
		IncomesDAO incomesDAO = new IncomesDAO();
		incomesDAO.merge("manIncomes.txt", "womanIncomes.txt", "incomes.txt");
		incomesDAO.updateRanking("incomes.txt");
	}
}
