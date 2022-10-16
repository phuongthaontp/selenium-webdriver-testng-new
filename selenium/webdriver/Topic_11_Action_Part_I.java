package webdriver;

import static org.testng.Assert.assertEquals;

import java.security.PublicKey;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Action_Part_I {

	WebDriver driver;
	WebDriverWait expliciWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String authenChrome = projectPath + "\\autoITScript\\authen_chrome.exe";
	String authenFirefox = projectPath + "\\autoITScript\\authen_firefox.exe";
	
	String osName = System.getProperty("os.name");
	Alert alert;
	@BeforeClass
	public void beforeClass() {
		
		// WIN
		if (osName.startsWith("Windows")) {
			
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}
		// MAC	
		/*if (osName.startsWith("Mac")) {
			
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver_mac");
		}
		else
		{		
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver_linux");	
		}
		*/
		driver = new ChromeDriver();

		// Wait cho các trạng thái của element: visible/ presence/ invisible/ staleness
		// Visible: Phải thấy được trên UI/ Presence: Phải có ở trong HTML
		expliciWait = new WebDriverWait(driver, 15);

		// Ép kiểu tường minh
		jsExecutor = (JavascriptExecutor) driver;

		// Wait cho việc tìm element (findElement)
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	@Test
	public void TC_00_Login_Empty_Data() {
		driver.get("https://demo.guru99.com/v4/index.php");
		
		driver.findElement(By.name("btnLogin")).click();
		
		alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "User or Password is not valid");
		
		alert.accept();
		sleepInSecond(3);
	}
	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);
		
		alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		alert.accept();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
	}
	@Test
	public void TC_02_Confirm_Alert() {	
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(3);
		
		alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		alert.dismiss();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");		
	}
	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		String textToSendkey = "Trangdth";
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(3);
		
		alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		alert.sendKeys(textToSendkey);
		sleepInSecond(3);		
		
		alert.accept();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: "+ textToSendkey);			
	}
	@Test
	public void TC_04_Authentication_Alert_I() {
		// Cách 1: Nhập username, password vào URL
		// http://username:password@the-internet.herokuapp.com/basic_auth
		String username = "admin";
		String password ="admin";
		driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		sleepInSecond(2);
	}
	
	@Test
	public void TC_04_Authentication_Alert_II() {
		String username = "admin";
		String password ="admin";
		driver.get("http://the-internet.herokuapp.com/");
		String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		driver.get(getAuthenticateLink(basicAuthenLink, username, password));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	@Test
	public void TC_04_Authentication_Alert_III() {
		String username = "admin";
		String password ="admin";
		driver.get("http://the-internet.herokuapp.com/");
		
		//Script sẽ chạy trước để chờ Authen alert bật lên sau
		if(driver.toString().contains("Firefox")) {
	
		//Runtime.getRuntime().exec(new String[] { authenFirefox, username, password });
		}
		else
		{
		//Runtime.getRuntime().exec(new String[] { authenChrome, username, password });	
		}	
		driver.findElement(By.xpath("//a[text()='Basic Auth']")).click();
		sleepInSecond(8);

		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	public String getAuthenticateLink(String url, String username, String password) {
		String[] links = url.split("//");
		url = links[0] + "//" + username + ":" + password + "@" + links[1];
		return url;
	}
	// Hàm này giống như code trực tiếp trên Console
	public void clickByJavascript(By by) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
	}

    public void checkToCheckbox(By by) {
        	if (!driver.findElement(by).isSelected());
        	driver.findElement(by).click();
        	
        }
    public void uncheckToCheckbox(By by) {
    	if (driver.findElement(by).isSelected());
    	driver.findElement(by).click();
    	
    }	
    public void checkToRadio(By by) {
    	if (!driver.findElement(by).isSelected());
    	driver.findElement(by).click();
    }
    public boolean isElementSelected(By by) {
    	if (driver.findElement(by).isSelected()) {
    	return true;
    	}
    	else {
    		return false;
    	}
    }
    public boolean isElementDisplayed(By by) {
    	if (driver.findElement(by).isDisplayed()) {
    	return true;
    	}
    	else {
    		return false;
    	}
    }
    public boolean isElementEnabled(By by) {
    	if (driver.findElement(by).isEnabled()) {
    	return true;
    	}
    	else {
    		return false;
    	}
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
