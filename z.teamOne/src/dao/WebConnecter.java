package dao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebConnecter {
	public static final String WEB_DRIVER_PATH = "C:/chromedriver.exe";
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
		
	public static WebDriver getConnects() {
		WebDriver webDriver = null;
		
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		ChromeOptions options = new ChromeOptions();
	    options.addArguments("headless");
		
		webDriver = new ChromeDriver(options);
		
		return webDriver;
	}
}
