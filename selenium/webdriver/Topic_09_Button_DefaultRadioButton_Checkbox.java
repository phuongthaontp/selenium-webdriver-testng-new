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

public class Topic_09_Button_DefaultRadioButton_Checkbox {

	WebDriver driver;
	WebDriverWait expliciWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
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
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.xpath("//a[text()='Đăng nhập']/parent::li")).click();

		By buttonLoginBy = By.xpath("//button[@class='fhs-btn-login']");
		By emailTextboxBy = By.xpath("//input[@id='login_username']");
		By passwordTextboxBy = By.xpath("//input[@id='login_password']");

		// Verify Login button is Disable
		Assert.assertFalse(driver.findElement(buttonLoginBy).isEnabled());
		driver.findElement(emailTextboxBy).sendKeys("Trangdth@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys("Trangdth@123");
		// Verify Login button is Enable
		Assert.assertTrue(driver.findElement(buttonLoginBy).isEnabled());

		// Verify background color = RED
		String loginButtonBackgroundColor = driver.findElement(buttonLoginBy).getCssValue("background-color");
		System.out.println("RGB = " + loginButtonBackgroundColor);

		// Verify = RGB
		Assert.assertEquals(loginButtonBackgroundColor, "rgba(201, 33, 39, 0.114)");

		// Convert qua Hexa
		String loginButtonBackgroundColorHexa = Color.fromString(loginButtonBackgroundColor).asHex();
		System.out.println("Hexa = " + loginButtonBackgroundColorHexa);
		Assert.assertEquals(loginButtonBackgroundColorHexa.toUpperCase(), "#C92127");

		driver.navigate().refresh();
		driver.findElement(By.xpath("//a[contains(text(),'Đăng nhập')]")).click();

		// Remove Disable Attribute
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled');", driver.findElement(buttonLoginBy));
		sleepInSecond(2);
		driver.findElement(buttonLoginBy).click();
		Assert.assertEquals(driver
				.findElement(By.xpath(
						"//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver
				.findElement(By.xpath(
						"//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");


	}
	@Test
	public void TC_02_DefaultCheckbox_RadioButton() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
	
		By dualZoneAirConditioningBy = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input[@type='checkbox']");
		checkToCheckbox(dualZoneAirConditioningBy);
		sleepInSecond(2);
		Assert.assertTrue(isElementSelected(dualZoneAirConditioningBy));
		uncheckToCheckbox(dualZoneAirConditioningBy);
		sleepInSecond(2);
		Assert.assertFalse(isElementSelected(dualZoneAirConditioningBy));		
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		By twoDotZeroPetrolBy = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input[@type='radio']");
		checkToRadio(twoDotZeroPetrolBy);
		sleepInSecond(2);
		Assert.assertTrue(isElementSelected(twoDotZeroPetrolBy));
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
	@Test
	public void TC_04_Multiple_CheckBox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		List<WebElement> listCheckbox = driver.findElements(By.cssSelector("input[type='checkbox"));
		System.out.println("Checkbox size= "+listCheckbox.size());
		
		// Action-select
		for (WebElement list : listCheckbox) {
			if (!list.isSelected()) {
			list.click();
			sleepInSecond(1);
			}
		}
		// Verify-selected
		for (WebElement list : listCheckbox) {
			Assert.assertTrue(list.isSelected());
		}
		// Action-deselect
		for (WebElement list : listCheckbox) {
			if (list.isSelected()) {
				list.click();
			}
		}
		// Verify-deselected
		for (WebElement list : listCheckbox) {
				Assert.assertFalse(list.isSelected());
			}
		}
	
	@Test
	public void TC_04_CustomCheckBox_RadioButton() {

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