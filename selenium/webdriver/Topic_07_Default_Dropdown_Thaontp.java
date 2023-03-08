package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown_Thaontp {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	JavascriptExecutor jsExecutor; // JavascriptExecutor là 1 interface
	WebDriverWait explicitWait;
	Actions action;
	Select select;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		// Khởi tạo browser lên
		driver = new FirefoxDriver();

		// Khởi tạo sau khi driver này được sinh ra, always là như thế vì nếu k dữ liệu truyền vào sẽ báo lỗi bị null
		// JavascriptExecutor/ WebDriverWait/ Action...
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		// Tại sao phải ép kiểu driver? Tại sao k new? Vì nó k phải là class nên mình k
		// new

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// Test
	public void TC_01_Rode() {
		driver.get("https://www.rode.com/wheretobuy");
		// Khởi tạo khi sử dụng (element xuất hiện)- vì tham số truyền vào là element
		// Khởi tạo select để thao tác với element( country dropdown)
		select = new Select(driver.findElement(By.xpath("//select[@id='country']")));

		// Check dropdown không hỗ trợ thuộc tính multiple select
		Assert.assertFalse(select.isMultiple());

		// Select giá trị Viet Nam
		select.selectByVisibleText("Vietnam");

		// Verify Vietnam selected success (Kiểm tra giá trị được chọn là VietNam)
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");

		// Click button Search
		driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
		sleepInSecond(5);

		// Kiểm tra giá trị search ra có 32KQ (Page thay đổi k show ra kết quả khi
		// search bỏ qua bước này
		// Assert.assertEquals(driver.findElement(By.cssSelector("div.result_count>span")).getText(),
		// "32");

		// List<WebElement> storeName =
		// driver.findElements(By.cssSelector("div#search_result div.store_name"));

		// Verify tổng số lượng store name = 32
		// Assert.assertEquals(storeName.size(), 32);

		// for (WebElement store : storeName) {
		// System.out.println(store.getText());
		// }

	}

	@Test
	public void TC_02_NopCommerce() {
		String firstName = "Automation";
		String lastName = "FC";
		String date = "10";
		String month = "October";
		String year = "1990";
		String emailAddress = "AutomationFc" + getRandomNumber() + "@hotmail.com";
		String password = "123456";

		driver.get("https://demo.nopcommerce.com/");

		// Click link register
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();

		// Input các thông tin hợp lệ vào form
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);

		// Date
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(date);

		// Month
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);

		// Year
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);

		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();

		// Verify vào Homepage khi đăng kí thành công
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),
				"Your registration completed");


		// Click Login
		driver.findElement(By.xpath("//a[@class='ico-login']")).click();
		
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
		driver.findElement(By.id("Password")).sendKeys(password);

		driver.findElement(By.xpath("//button[@class='button-1 login-button']")).click();

		// Khi click thì page HTML render lại rồi nên phải khai báo lại select

		// Verify lại thông tin đăng kí có đúng hay không?
		//Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		//Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);

		//select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		//Assert.assertEquals(select.getFirstSelectedOption().getText(), date);

		//select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		//Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

		//select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		//Assert.assertEquals(select.getFirstSelectedOption().getText(), year);

		//Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);

	}

	@Test
	public void TC_03() {

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

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);

	}
}
