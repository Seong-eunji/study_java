package bank;

public class Dongseok extends Bank {
	
	public Dongseok() {;}
	
	@Override
	public int showBalance() {
	      setMoney(getMoney() / 2);
	      return super.showBalance();
	}
}
