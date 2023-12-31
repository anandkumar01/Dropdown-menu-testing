package testing;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownMenu {
	public static WebDriver driver;
	public static List<String> countries = new ArrayList<String>();		// defining a list of string to store the country name.
	public static String selectedCountry;		// initializing a string variable for selected country on web page.
	
	public static WebDriver createDriver() {	// browser setup
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter browser name ie. chrome/edge/firefox: ");
		String browser = sc.nextLine();								// reading string input to input the browser name.
		driver = DriverSetup.getWebDriver(browser);
		String baseUrl = "https://mail.rediff.com/cgi-bin/login.cgi";
		driver.get(baseUrl);
		sc.close();
		return driver;
	}
	
	public static void createAccount(WebDriver driver) {
		WebElement newAccountLink = driver.findElement(By.cssSelector("div.create-new-account>a"));
		JavascriptExecutor js = (JavascriptExecutor) driver;			// click functionality using javascriptexecutor.
		js.executeScript("arguments[0].click();", newAccountLink);
	}
	
	public static List<String> fillDetails(WebDriver driver) throws InterruptedException, IOException {
		
			// The raw speed order of locators(fast -> slow): cssSelector -> name -> xpath -> id
			// passing values
			WebElement nameInput = driver.findElement(By.xpath("//input[starts-with(@name, \"name\")]"));
			nameInput.sendKeys(ReadExcelData.name);
			
			WebElement emailInput = driver.findElement(By.xpath("//input[starts-with(@name, \"login\")]"));
			emailInput.sendKeys(ReadExcelData.email);
			
			WebElement checkButton = driver.findElement(By.cssSelector("input.btn_checkavail"));
			checkButton.click();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			try {
				WebElement firstRadioButton = driver.findElement(By.cssSelector("input#radio_login"));
				firstRadioButton.click();
			} catch(Exception e) {
				System.out.println("\nId is already unique.");
			}
			
			WebElement passwordInput = driver.findElement(By.cssSelector("input#newpasswd"));
			passwordInput.sendKeys(ReadExcelData.password);
			
			WebElement checkBox = driver.findElement(By.cssSelector("input.nomargin"));
			checkBox.click();
			
			// selecting day from dropdown menu.
			WebElement day = driver.findElement(By.xpath("//select[starts-with(@name,\"DOB_Day\")]"));	// xpath using starts-with       
			Select selectDay = new Select(day);
			selectDay.selectByVisibleText(String.valueOf(ReadExcelData.day1));
			
			// selecting month from dropdown menu.
			WebElement month = driver.findElement(By.xpath("//select[starts-with(@name,\"DOB_Month\")]"));
			Select selectMonth = new Select(month);
			selectMonth.selectByVisibleText(ReadExcelData.month1);
			
			// selecting year from dropdown menu.
			WebElement year = driver.findElement(By.xpath("//select[starts-with(@name,\"DOB_Year\")]"));
			Select selectYear = new Select(year);
			selectYear.selectByVisibleText(String.valueOf(ReadExcelData.year1));
			
			// selecting country from dropdown menu.
			WebElement country = driver.findElement(By.cssSelector("select#country"));
			Select selectCountry = new Select(country);
			selectCountry.selectByVisibleText(ReadExcelData.actCountry);
			
			//storing all the web element of country.
			List<WebElement> countryList = driver.findElements(By.cssSelector("select#country>option"));
			System.out.println("\nTotal number of country is " + countryList.size());
			
			// adding all country name in countries.
			for(WebElement ele : countryList) {
				String str = ele.getText();
				countries.add(str);
			}
			selectedCountry = selectCountry.getFirstSelectedOption().getText();		// getting selected country on web page.
			System.out.println("Selected Country : " + selectedCountry);
			
			validate();			// calling validate function to validate country.
		
		return countries;
	}
	
	public static void validate() {		// creating a method to validate the country if it is selected on webpage.
		if(selectedCountry.equals(ReadExcelData.actCountry)) {
			System.out.println("Selected country and printed country on console is validated.");
		} else {
			System.out.println("Selected country and printed country on console is not validated.");
		}
	}
	
	public static void printCountry(List<String> list) {		// creating a method to print all the country name on console.
		System.out.println("\nCountries list are :");
		for(int i=0; i<list.size(); i++) {
			System.out.println((i+1) + ". " + list.get(i));
		}
	}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		driver = createDriver();
		createAccount(driver);
		ReadExcelData.excelDataRead();
		List<String> country = fillDetails(driver);
		String selCountry = selectedCountry;
		WriteExcelData.excelDataWrite(selCountry);
		printCountry(country);
		driver.quit();
	}

}
