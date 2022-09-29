package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_05_Element_Part_II {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By fullNameTextbox = By.xpath("//label[@id='txtFirstname-error']"); 
	By emailTextbox = By.xpath("//label[@id='txtEmail-error']"); 
	By confirmEmailTextbox = By.xpath("//label[@id='txtCEmail-error']"); 
	By passwordTextbox = By.xpath("//label[@id='txtPassword-error']"); 
	By confirmPasswordTextbox = By.xpath("//label[@id='txtCPassword-error']"); 
	By phoneTextbox = By.xpath("//label[@id='txtPhone-error']"); 
	By submitButton = By.xpath("//button[@type='submit']");
	By fullnameInput = By.xpath("//input[@id='txtFirstname']");
	By emailInput = By.xpath("//input[@id='txtEmail']");
	By confirmEmailInput = By.xpath("//input[@id='txtCEmail']");
	By passwordInput = By.xpath("//input[@id='txtPassword']");
	By confirmPasswordInput = By.xpath("//input[@id='txtCPassword']");
	By phoneInput = By.xpath("//input[@id='txtPhone']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}
	@Test
	public void TC_01_RegisterWithEmptyData() {
		driver.findElement(fullnameInput).clear();
		driver.findElement(emailInput).clear();
		driver.findElement(confirmEmailInput).clear();
		driver.findElement(passwordInput).clear();
		driver.findElement(confirmPasswordInput).clear();
		driver.findElement(phoneInput).clear();	
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(fullNameTextbox).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(emailTextbox).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(confirmEmailTextbox).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(passwordTextbox).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(confirmPasswordTextbox).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(phoneTextbox).getText(), "Vui lòng nhập số điện thoại.");
}
	@Test
	public void TC_02_RegisterWithInvalidEmail() {
		driver.findElement(fullnameInput).sendKeys("Dinh Thi Huyen Trang");
		driver.findElement(emailInput).sendKeys("123aaa");
		driver.findElement(confirmEmailInput).sendKeys("123aaa");
		driver.findElement(passwordInput).sendKeys("Dtht@1602");
		driver.findElement(confirmPasswordInput).sendKeys("Dtht@1602");
		driver.findElement(phoneInput).sendKeys("0976757850");	
		driver.findElement(submitButton).click();
		Assert.assertEquals(driver.findElement(emailTextbox).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(confirmEmailTextbox).getText(), "Email nhập lại không đúng");
}
	@Test
	public void TC_03_RegisterWithIncorrectConfirmEmail() {
		driver.findElement(fullnameInput).sendKeys("Dinh Thi Huyen Trang");
		driver.findElement(emailInput).sendKeys("miss.trang57@gmail.com");
		driver.findElement(confirmEmailInput).sendKeys("123aaa");
		driver.findElement(passwordInput).sendKeys("Dtht@1602");
		driver.findElement(confirmPasswordInput).sendKeys("Dtht@1602");
		driver.findElement(phoneInput).sendKeys("0976757850");	
		driver.findElement(submitButton).click();
		Assert.assertEquals(driver.findElement(confirmEmailTextbox).getText(), "Email nhập lại không đúng");
}
	@Test
	public void TC_04_RegisterWithPasswordlessthan6characters() {
		driver.findElement(fullnameInput).sendKeys("Dinh Thi Huyen Trang");
		driver.findElement(emailInput).sendKeys("miss.trang57@gmail.com");
		driver.findElement(confirmEmailInput).sendKeys("miss.trang57@gmail.com");
		driver.findElement(passwordInput).sendKeys("Dtht@");
		driver.findElement(confirmPasswordInput).sendKeys("Dtht@");
		driver.findElement(phoneInput).sendKeys("0976757850");	
		driver.findElement(submitButton).click();
		Assert.assertEquals(driver.findElement(passwordTextbox).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(confirmPasswordTextbox).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
}
	@Test
	public void TC_05_RegisterWithInvalidPhoneNumber() {
		driver.findElement(fullnameInput).sendKeys("Dinh Thi Huyen Trang");
		driver.findElement(emailInput).sendKeys("miss.trang57@gmail.com");
		driver.findElement(confirmEmailInput).sendKeys("miss.trang57@gmail.com");
		driver.findElement(passwordInput).sendKeys("Dtht@123");
		driver.findElement(confirmPasswordInput).sendKeys("Dtht@123");
		driver.findElement(phoneInput).sendKeys("097675785");	
		driver.findElement(submitButton).click();
		Assert.assertEquals(driver.findElement(phoneTextbox).getText(), "Số điện thoại phải từ 10-11 số.");
}
	@Test
	public void TC_06_RegisterWithInvalidPhoneNumber() {
		driver.findElement(fullnameInput).sendKeys("Dinh Thi Huyen Trang");
		driver.findElement(emailInput).sendKeys("miss.trang57@gmail.com");
		driver.findElement(confirmEmailInput).sendKeys("miss.trang57@gmail.com");
		driver.findElement(passwordInput).sendKeys("Dtht@123");
		driver.findElement(confirmPasswordInput).sendKeys("Dtht@123");
		driver.findElement(phoneInput).sendKeys("1976757850");	
		driver.findElement(submitButton).click();
		Assert.assertEquals(driver.findElement(phoneTextbox).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

