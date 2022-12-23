package arrayListTask;

public class Fruit {
	// 과일 이름, 과일 가격
	// private
	private String name;
	private int price;
	
	// 기본 생성자
	public Fruit() {;}
	
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

	// toString()
	public String toString() {
		String str = name + ", " + price;
		return str;
	}
}
