package bank;

import java.util.Random;

//public class Bank {
//	String name;
//	String accountNumber;
//	String phoneNumber;
//	String password;
//	int balance;
//	
//	public static Bank[][] database = {
//			new Shinhan[100],
//			new Kukmin[100],
//			new SanwaMoney[100]
//	};
//	
//	public static int[] arCount = new int[3];
//	
//	Random r = new Random();
//	
//	public Bank() {;}
//
//	public Bank(String name, String accountNumber, String phoneNumber, String password, int balance) {
//		this.name = name;
//		this.accountNumber = accountNumber;
//		this.phoneNumber = phoneNumber;
//		this.password = password;
//		this.balance = balance;
//	}
//
//	public void getName(String name) {
//		this.name = name;
//	}
//	
//	public void getpassword(String password) {
//		this.password = password;
//	}
//	
//	public void payIn(int money) {
//		this.balance += money;
//	}
//
//	public void payOut(int money) {
//		this.balance -= money;
//	}
//
//	public int checkBalance() {
//		return this.balance;
//	}
//
//	public String productAccount() {
//		String account = "";
//		while(true) {
//		account = r.nextInt() * 1000 + "";
//		if(checkAccNumber(account) == null) {break;}
//		}
//		return account;
//	}
//
//	static Bank checkAccNumber(String account) {
//		Bank bank = null;
//		for (int i = 0; i < database.length; i++) {
//			int j = 0;
//			for (j = 0; j < arCount[i]; j++) {
//				if(database[i][j].accountNumber.equals(account)) {
//					bank = database[i][j];
//					break;
//				}
//			}
//			if(j != arCount[i]) {break;}
//		}
//		return bank;
//	}
//
//	static Bank checkPhNumber(String phNumber) {
//		Bank bank = null;
//		for (int i = 0; i < database.length; i++) {
//			int j = 0;
//			for (j = 0; j < arCount[i]; j++) {
//				if(database[i][j].accountNumber.equals(phNumber)) {
//					bank = database[i][j];
//					break;
//				}
//			}
//			if(j != arCount[i]) {break;}
//		}
//		return bank;
//	}
//
//	static Bank logIn(String account, String password) {
//		Bank bank = checkAccNumber(account);
//		if(!bank.password.equals(password)) {
//			bank = null;
//		}
//		return bank;
//	}
//}

// 강사님 코드
public class Bank {
	
	// 모든 클래스에서 사용할 거기 때문에 static으로 선언
	// 은행이 3개, 각 은행별 고객 100명씩 유치 가능
	public static Bank[][] arrBank = new Bank[3][100];
	//  은행별 가입 회원 수
	public static int[] arCount = new int[3];			// 각 은행마다 생성된 계좌의 개수
	
	private String name;
	private String account;
	private String phoneNumber;
	private String password;
	private int money;

	public Bank() {;}

	public Bank(String name, String account, String phoneNumber, String password, int money) {
		this.name = name;
		this.account = account;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.money = money;
	}

	// 모든 전역변수들이 private으로 선언되었기 때문에
	// 값을 입력받고 내보내기 위해서 getter, setter 필요
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	// static을 붙여서 선언한 메소드
	// 객체화 없이 사용해야 할 때가 있기 때문
	// 객체화 없이 사용하면 편하기 때문
	// 객체화 전에도 후에도 사용하고 또 모든 클래스에서 공통으로 사용하기 때문에
	
	// 계좌번호 중복검사
	// 외부에서 생성된 계좌번호를 전달받는다.
	public static Bank checkAccount(String account) {
		Bank bank = null;
		for (int i = 0; i < arrBank.length; i++) {					// 은행 수만큼 반복
			int j = 0;												// 아래의 for문 바깥에서도 j를 사용해주기 위해 바깥에 선언
		for (j = 0; j < arCount[i]; j++) {							// 각 은행별 가입된 회원 수만큼 반복
				if (arrBank[i][j].account.equals(account)) {		// 각 회원들의 계좌번호와 생성된 계좌번호를 비교한다.
					bank = arrBank[i][j];							// 만약 일치하는 계좌번호가 있다면 해당 객체를 bank에 담아준다.
					break;											// 더 이상 반복을 진행할 필요가 없다
				}
			}
			if (j != arCount[i]) {									// 위에서 강제로 break를 했다면 j가 arCount[i]까지 증가하지 못하기 때문에
				break;												// 위에서 break 후 밖에 있는 for문도 break해준다.
																	// if문으로 조건을 달아주는 이유는 그냥 break일 경우 첫번째 for문 때 첫번째 은행만 돌고 바로 탈출해버리기 때문
			}														// 위에서 break가 걸렸다면 여기서도 break를 써라라는 뜻
		}
		return bank;
	}

	// 핸드폰 번호 검사
	public static Bank checkPhoneNumber(String phoneNumber) {
		Bank bank = null;
		for (int i = 0; i < arrBank.length; i++) {					// arrBank의 행길이만큼 반복
			int j = 0;												// 아래의 for문 바깥에서도 j를 사용해주기 위해 바깥에 선언
			for (j = 0; j < arCount[i]; j++) {						// 은행에 저장되어있는 계좌 수만큼 반복
				if (arrBank[i][j].phoneNumber.equals(phoneNumber)) {// 입력받은 account가 어떤 은행의 어떤 고객의 계좌와 일치하는지
					bank = arrBank[i][j];							// 일치한다면 bank에 담음
					break;
				}
			}
			if (j != arCount[i]) {
				break;
			}
		}
		return bank;
	}

	// 로그인
	public static Bank login(String account, String password) {
		// 계좌번호는 checkAccount에 전달한다.
		// 1) 계좌가 있다면 해당 회원의 전체 정보를 가져온다.
		// 2) 계좌가 없다면 null을 가져온다.
		Bank bank = checkAccount(account);							// 계좌 중복검사 메소드를 이용하여 일치하는 계좌 조회
																	// 일치하면 Bank 리턴, 일치하는 것이 없으면 null 리턴
		// 계좌번호가 존재한다면,
		if (bank != null) {											// bank가 null이 아니라면(== 입력받은 계좌번호와 일치하는 것이 존재한다면)
			if (bank.password.equals(password)) {					// 그 계좌의 비밀번호가 입력받은 비밀번호와 일치하는지 확인
				return bank;										// 일치하면 bank 리턴
			}
		}
		return null;
	}

	// 입금
	public void deposit(int money) {
		this.money += money;
	}

	// 출금
	public void withdraw(int money) {
		this.money -= money;
	}

	// 잔액조회
	public int showBalance() {
		return money;
	}

}
