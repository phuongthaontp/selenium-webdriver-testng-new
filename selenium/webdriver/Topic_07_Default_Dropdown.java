package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.List;
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

public class Topic_07_Default_Dropdown {


	private static final String String = null;
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	JavascriptExecutor jsExecutor;
	WebDriverWait expliciWait;
	Actions action;
	
	
	Select select;
	
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

	@Test
	public void TC_01_Rode() {
		driver.get("https://www.rode.com/wheretobuy");
		// Select dc khởi tạo khi sử dụng (khi element xuất hiện vì tham số truyền vào là 1 element)
		// Khởi tạo select đẻ thao tác vs element(country dropdown)
		select = new Select(driver.findElement(By.xpath("//select[@id='where country']")));
		
		// Afghanistan(index)
		//select.selectByIndex(2);
		
		// Argentina(value)
		//select.selectByValue("Argentina");
		// Belarus(Visible text)
		//select.selectByVisibleText("Belarus");
		
		// Ko support multiple select
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText("Vietnam");
		sleepInSecond(5);
		// Verify Vietnam selected success
		
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		
		driver.findElement(By.cssSelector("input#search_loc_submit")).click();
		sleepInSecond(5);
		
		// 32 results
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result_count>span")).getText(), "32");
		
		// Java Generic (List<KDL>)
		List<WebElement> storeName = driver.findElements(By.cssSelector("div.#search_results div.store_name"));
		
		// Verify tổng số lượng store name = 32
		Assert.assertEquals(storeName.size(), 32);
		
		for (WebElement store : storeName) {
			System.out.println(store.getText());
			
		}
	}
	

	@Test
	public void TC_02_NopCommerce() {

	}

	public void Register_03_Incorrect_Confirm_Email() {

	}

	public void Register_04_Invalid_Password() {

	}

	public void Register_05_Invalid_Phone() {

	}

	@AfterClass
	public void afterClass() {

	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
