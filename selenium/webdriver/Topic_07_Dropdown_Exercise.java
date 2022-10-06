package webdriver;


import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_07_Dropdown_Exercise {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	JavascriptExecutor jsExecutor;
	WebDriverWait expliciWait;
	Actions action;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		// Khởi tạo sau khi driver này được sinh ra
		// JavascriptExecutor/ WebDriverWait/ Actions/..
		jsExecutor = (JavascriptExecutor) driver;
		expliciWait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

/*	@Test
	public void TC_04_Dropdown_List() {
		driver.get("https://rode.com/en/support/where-to-buy");		
		Select select = new Select(driver.findElement(By.xpath("//select[@id='country']")));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText("Vietnam");
		sleepInSecond(5);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
		sleepInSecond(5);
		List<WebElement> storeName = driver.findElements(By.xpath("//div[@class='d-flex flex-wrap']//div[@class='p-1']//h4"));
		Assert.assertEquals(storeName.size(), 39);
		for (WebElement store : storeName) {
			System.out.println(store.getText());
			driver.quit();
		}
		
	}
		
*/	
	@Test
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);
	}
	@Test
	public void TC_03_NopCommerce() {
			String firstName = "Trang";
			String lastName= "Dinh";		
			String email= "mstrang"+getRandomNumber()+"@gmail.com";
			String password= "12345678";
			By firstNameTextboxBy = By.xpath("//input[@id='FirstName']");
			By lastNameTextboxBy = By.xpath("//input[@id='LastName']");
			By emailTextboxBy = By.xpath("//input[@id='Email']");
			By passwordTextboxBy = By.xpath("//input[@id='Password']");
			By confirmPasswordTextboxBy = By.xpath("//input[@id='ConfirmPassword']");
			
			
			driver.get("https://demo.nopcommerce.com/register");	
			driver.findElement(firstNameTextboxBy).sendKeys(firstName);
			driver.findElement(lastNameTextboxBy).sendKeys(lastName);
			
			Select selectDay = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
			selectDay.selectByVisibleText("1");
			List<WebElement> listOptionDay = selectDay.getOptions();
			Assert.assertEquals(listOptionDay.size(), 32);
			
			Select selectMonth = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
			selectMonth.selectByVisibleText("May");
			List<WebElement> listOptionMonth = selectMonth.getOptions();
			Assert.assertEquals(listOptionMonth.size(), 13);
			
			Select selectYear = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
			selectYear.selectByVisibleText("1980");
			List<WebElement> listOptionYear = selectYear.getOptions();
			Assert.assertEquals(listOptionYear.size(), 112);
			
			driver.findElement(emailTextboxBy).sendKeys(email);
			driver.findElement(passwordTextboxBy).sendKeys(password);
			driver.findElement(confirmPasswordTextboxBy).sendKeys(password);
			driver.findElement(By.xpath("//button[@id='register-button']")).click();		
			sleepInSecond(5);
			
			Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
			
			driver.findElement(By.xpath("//a[@class='ico-account']")).click();
			sleepInSecond(5);
			Select selectedDay = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
			Assert.assertEquals(selectedDay.getFirstSelectedOption().getText(), "1");
			Select selectedMonth = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
			Assert.assertEquals(selectedMonth.getFirstSelectedOption().getText(), "May");
			Select selectedYear = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
			Assert.assertEquals(selectedYear.getFirstSelectedOption().getText(), "1980");

			
			}


	
	public void Register_03_Incorrect_Confirm_Email() {

	}

	public void Register_04_Invalid_Password() {

	}

	public void Register_05_Invalid_Phone() {

	}

	@AfterClass
	public void afterClass() {
			driver.quit();
	}
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
