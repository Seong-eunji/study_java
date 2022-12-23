package bank;

public class Shinhan extends Bank {
	
	public Shinhan() {;}
	
	@Override
	public void deposit(int money) {
	      money *= 0.5;
	      super.deposit(money);
	}
}
