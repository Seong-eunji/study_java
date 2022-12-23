package collectionTask;

public class User {
	private String name;
	private String id;
	private String password;
	private String phoneNumber;
	
	public User() {;}
	
	public User(String name, String id, String password, String phoneNumber) {
		super();
		this.name = name;
		this.id = id;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		return this.getName() + " " + this.getId() + " " + this.getPhoneNumber();
//		return "이름 : " + this.getName() + " / 아이디 : " + this.getId() + " / 비밀번호 : " + this.getPassword() + " / 전화번호 : " + this.getPhoneNumber();
	}
	
	
}
