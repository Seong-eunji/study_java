package anonymousTask;

// 본사가 각 지점으로부터 메뉴와 판매방식을 입력받기 위해 제공하는 틀
public interface Form {
	
	public String[] getMenu();			// 메뉴
	public void sell(String order);		// 판매방식
}
