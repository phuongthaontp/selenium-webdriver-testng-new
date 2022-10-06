package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_05_Element_Part_IV {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, middleName, lastName, fullName, emailAddress, password;
	// Có thể khai báo như này, hoặc vừa khai báo vừa khởi tạo luôn, kiểu String firstName ="X";
	// Không được khởi tạo kiểu:
	// String firstName;
	// firstName = "X";
	// Tốt nhất nên khởi tạo nó trong beforeClass
	By emailAddressLogin = By.xpath("//input[@id='email']");
	By passwordLogin = By.xpath("//input[@id='pass']");
	By firstNameCreate = By.xpath("//input[@id='firstname']");
	By middleNameCreate = By.xpath("//input[@id='middlename']");
	By lastNameCreate = By.xpath("//input[@id='lastname']");
	By emailAddressCreate = By.xpath("//input[@id='email_address']");
	By passwordCreate = By.xpath("//input[@id='password']");
	By confirmPasswordCreate = By.xpath("//input[@id='confirmation']");
	By loginButton = By.xpath("//button[@id='send2']");
	By creatAccountButton = By.xpath("//a[@title='Create an Account']");
	By registerButton = By.xpath("//button[@title='Register']");
	By loginFailAlert = By.xpath("//span[normalize-space()='Invalid login or password.']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		firstName = "Trang";
		middleName = "Huyen";
		lastName = "Dinh";
		fullName = firstName +" "+ middleName +" "+ lastName;
		emailAddress = "msstrang" + getRandomNumber() + "@gmail.com";
		password = "Dtht@1602";
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// implicitlyWait ảnh hưởng cho 2 hàm: findElement/ findElements
		// Nguyên tắc khi chạy:
		// - Nếu như tìm thấy Element thì không cần chờ hết timeout(20s)
		// - Nếu chưa tìm thấy Element thì :
		// + Mỗi 0.5s sẽ tìm lại 1 lần
		// + Nếu như chưa tìm thấy thì cứ tìm lại cho đến khi nào hết timeout 20s thì thôi
		// + Sau 20s nó sẽ bị fail Testcase
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("http://live.techpanda.org/");
		WebElement myAccountfooter = driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']"));
		myAccountfooter.click();
	}

	@Test
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);
	}
	
	@Test
	public void Login_01_Empty_Email_Password() {
		driver.findElement(emailAddressLogin).clear();
		driver.findElement(passwordLogin).clear();
		driver.findElement(loginButton).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),
				"This is a required field.");
	}

	@Test
	public void Login_02_Invalid_Email() {
		driver.findElement(emailAddressLogin).sendKeys("12345@123.123");
		driver.findElement(passwordLogin).sendKeys("123456");
		driver.findElement(loginButton).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void Login_03_Password_Lessthan_6characters() {
		driver.findElement(emailAddressLogin).sendKeys(emailAddress);
		driver.findElement(passwordLogin).sendKeys("123");
		driver.findElement(loginButton).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void Login_04_Incorrect_Email_Password() {
		driver.findElement(emailAddressLogin).sendKeys(emailAddress);
		driver.findElement(passwordLogin).sendKeys("123456");
		driver.findElement(loginButton).click();
		Assert.assertEquals(driver.findElement(loginFailAlert).getText(), "Invalid login or password.");

	}

	@Test
	public void Login_05_Create_New_Account() {
		driver.findElement(creatAccountButton).click();
		driver.findElement(firstNameCreate).sendKeys(firstName);
		driver.findElement(middleNameCreate).sendKeys(middleName);
		driver.findElement(lastNameCreate).sendKeys(lastName);
		driver.findElement(emailAddressCreate).sendKeys(emailAddress);
		driver.findElement(passwordCreate).sendKeys(password);
		driver.findElement(confirmPasswordCreate).sendKeys(password);
		driver.findElement(registerButton).click();
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[normalize-space()='Thank you for registering with Main Website Store.']"))
				.getText(), "Thank you for registering with Main Website Store.");
		Assert.assertEquals(driver.findElement(By.xpath("//h1['My Dashboard']")).getText(), "MY DASHBOARD");
		String helloAccount = driver.findElement(By.xpath("//p[@class='hello']/child::strong")).getText();
		Assert.assertTrue(helloAccount.contains("Hello, " + fullName + "!"));
		String emailAddressInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(emailAddressInfo.contains(fullName));
		Assert.assertTrue(emailAddressInfo.contains(emailAddress));
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']//span[@class='icon']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Log Out']")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title img[src$='logo.png']")).isDisplayed());
	}

	@Test
	 public void Login_06_Valid_Email_Password() {  
		driver.findElement(emailAddressLogin).sendKeys(emailAddress);
	 	driver.findElement(passwordLogin).sendKeys(password);
	 	driver.findElement(loginButton).click();
	    Assert.assertEquals(driver.findElement(By.
	    		xpath("//h1[normalize-space()='My Dashboard']")).getText(), "MY DASHBOARD");
	    String helloAccount = driver.findElement(By.xpath("//p[@class='hello']/child::strong")).getText();
		Assert.assertTrue(helloAccount.contains("Hello, " + fullName + "!"));
		String emailAddressInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(emailAddressInfo.contains(fullName));
		Assert.assertTrue(emailAddressInfo.contains(emailAddress));
	 }
	 
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}