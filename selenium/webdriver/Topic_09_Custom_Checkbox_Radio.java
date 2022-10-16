package webdriver;

import static org.testng.Assert.assertEquals;

import java.security.PublicKey;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

public class Topic_09_Custom_Checkbox_Radio {

	WebDriver driver;
	WebDriverWait expliciWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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
	public void TC_05_Custom_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		
		By winterCheckboxInput = By.cssSelector("input[value='Winter']");
		//By winterCheckboxSpan = By.xpath("//label[@for='mat-radio-2-input']//span[@class='mat-radio-inner-circle']");
		// Case 1: Dùng thẻ input
		// Selenium click(); -> ElementNotInteractableException
		// isSelected() ->Work

		// Case 2: Dùng thẻ span
		// Selenium click(); -> Work
		// isSelected() -> Not Work
		
		// Case 3: Dùng thẻ span - click()
		// Dùng thẻ input - isSelected()
		// 1 Element phải define tới 2 locator
		// Dễ nhầm lẫn, khó bảo trì
		/*driver.findElement(winterCheckboxSpan).click();
		sleepInSecond(2);
		// Verify: isSelected();
		Assert.assertTrue(driver.findElement(winterCheckboxInput).isSelected());
		*/
		// Case 4: Dùng thẻ input
		// Javascript - click (quan tâm element ẩn/ hiện)
		// isSelected - verify
		clickByJavascript(winterCheckboxInput);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(winterCheckboxInput).isSelected());
		if(!driver.findElement(winterCheckboxInput).isSelected());
			clickByJavascript(winterCheckboxInput);
			sleepInSecond(2);
	}
		// Hàm này giống như code trực tiếp trên Console
	public void clickByJavascript(By by) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
	
	}

	@Test
	public void TC_06_Custom_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
		By indeterminateCheckbox = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");
		clickByJavascript(checkedCheckbox);
		sleepInSecond(1);
		Assert.assertTrue(isElementSelected(checkedCheckbox));
		clickByJavascript(indeterminateCheckbox);
		sleepInSecond(1);
		Assert.assertTrue(isElementSelected(indeterminateCheckbox));
		clickByJavascript(checkedCheckbox);
		sleepInSecond(1);
		Assert.assertFalse(isElementSelected(checkedCheckbox));		
		clickByJavascript(indeterminateCheckbox);
		sleepInSecond(1);
		Assert.assertFalse(isElementSelected(indeterminateCheckbox));			
				
	}
	@Test
	public void TC_07_Custom_Checkbox() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		
		By myselfRadio = By.xpath("//div[text()='Đăng ký bản thân']/preceding-sibling::div/input");
		By myfamilyRadio = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		By registerFullname = By.xpath("//input[@formcontrolname='registerFullname']");
		By registerPhoneNumber = By.xpath("//input[@formcontrolname='registerPhoneNumber']");
		
		clickByJavascript(myfamilyRadio);
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(registerFullname));
		Assert.assertTrue(isElementDisplayed(registerPhoneNumber));
		clickByJavascript(myselfRadio);		
		sleepInSecond(2);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Assert.assertEquals(driver.findElements(registerFullname).size(), 0);
		Assert.assertEquals(driver.findElements(registerPhoneNumber).size(), 0);
		
	}
	@Test
	public void TC_08_Custom_Radio_Google_Doc() {
		
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		By haiPhongCityRadio = By.xpath("//div[@aria-label='Hải Phòng']");
		By quangNamCityCheckbox = By.xpath("//div[@aria-label='Quảng Nam']");
		By quangBinhCityCheckbox = By.xpath("//div[@aria-label='Quảng Bình']");
		
		Assert.assertEquals(driver.findElement(haiPhongCityRadio).getAttribute("aria-checked"), "false");		
		Assert.assertEquals(driver.findElement(quangNamCityCheckbox).getAttribute("aria-checked"), "false");		
		Assert.assertEquals(driver.findElement(quangBinhCityCheckbox).getAttribute("aria-checked"), "false");		
		
		driver.findElement(haiPhongCityRadio).click();
		driver.findElement(quangNamCityCheckbox).click();
		driver.findElement(quangBinhCityCheckbox).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(haiPhongCityRadio).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(quangNamCityCheckbox).getAttribute("aria-checked"), "true");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Hải Phòng' and @aria-checked='true']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked='true']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Bình' and @aria-checked='true']")).isDisplayed());
		
		
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