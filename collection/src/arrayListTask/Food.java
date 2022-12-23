package arrayListTask;

public class Food {
	// 이름, 가격, 종류(한식, 중식, 일식, 양식)
	// private
	private String name;
	private int price;
	private String type;
	
	// 기본 생성자
	public Food() {;}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// toString()
	public String toString() {
		String str = type + ", " + name + ", " + price + "원";
		return str;
	}
	
	
}
