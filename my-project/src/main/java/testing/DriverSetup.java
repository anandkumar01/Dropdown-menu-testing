package testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {
	public static WebDriver driver;
	
	public static WebDriver getWebDriver(String browser) {
		switch(browser.toLowerCase()) {		// applying switch case to handle multiple browser.
		case "chrome":
			driver = new ChromeDriver();	// invoking chrome driver to run in chrome browser.
			break;
		case "firefox":
			driver = new FirefoxDriver();	// invoking firefox driver to run in firefox browser.
			break;
		default:
			driver = new EdgeDriver();		// invoking edge driver to run in microsoft edge browser.
			break;
		}
		driver.manage().window().maximize();	// maximize the window size.
		return driver;
	}
}