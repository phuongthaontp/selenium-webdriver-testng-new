package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea {

	private static final String String = null;
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	By firstNameTextboxBy = By.xpath("//input[@placeholder='First Name']");
	By lastNameTextboxBy = By.xpath("//input[@placeholder='Last Name']");
	By employeeIdTextboxBy = By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Add_Employee() throws InterruptedException {
		driver.get("https://opensource-demo.orangehrmlive.com/");

		// Textbox
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");

		// Button
		driver.findElement(By.cssSelector("button[type='submit']")).click();

		Thread.sleep(5000);

		// Hoặc có thể dùng hàm sau
		// sleepInSecond(5);

		// At Dashboard page: 'Add Employee' sub-menu is not displayed
		
		// Assert.assertFalse(driver.findElement(By.cssSelector("a#menu_pim_addEmployee")).isDisplayed());

		// Open 'Add Employee' page
		// driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");

		// At Add Employee page" 'Add Employee' sub-menu link is displayed
		// Assert.assertTrue(driver.findElement(By.cssSelector("a#menu_pim_addEmployee")).isDisplayed());
		
		// Click Add button
		driver.findElement(By.cssSelector("button[class='oxd-button oxd-button--medium oxd-button--secondary']")).click();
		String firstName, lastName, editFirstName, editLastName;	
		firstName = "Luis";
		lastName = "Vuiton";
		editFirstName = "Steven";
		editLastName = "Gerrard";

		// Enter to Firstname, Lastname
		driver.findElement(firstNameTextboxBy).sendKeys(firstName);
		driver.findElement(lastNameTextboxBy).sendKeys(lastName);

		String employeeID = driver.findElement(employeeIdTextboxBy).getAttribute("value");

		// Click Save
		driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
		sleepInSecond(3);

		// Verify 'Firstname/ Lastname/ Employee' Textbox are Enable
		Assert.assertTrue(driver.findElement(firstNameTextboxBy).isEnabled());
		Assert.assertTrue(driver.findElement(lastNameTextboxBy).isEnabled());
		Assert.assertTrue(driver.findElement(employeeIdTextboxBy).isEnabled());
		sleepInSecond(5);

		// Verify 'Firstname/ Lastname/ Employee' matching vs Inputed value
		Assert.assertEquals(driver.findElement(firstNameTextboxBy).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameTextboxBy).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-content']//div[2]//div[1]//div[1]//div[1]//div[2]//input[1]")).getAttribute("value"), employeeID);
		sleepInSecond(5);
		
		// Edit FirstName / LastName
		driver.findElement(firstNameTextboxBy).clear();
		sleepInSecond(3);
		driver.findElement(firstNameTextboxBy).sendKeys(editFirstName);

		driver.findElement(lastNameTextboxBy).clear();
		sleepInSecond(3);
		driver.findElement(lastNameTextboxBy).sendKeys(editLastName);
		sleepInSecond(3);
		// Click Save button
		driver.findElement(By.cssSelector("div[class='orangehrm-horizontal-padding orangehrm-vertical-padding'] button[type='submit']")).click();
		sleepInSecond(3);

		// Verify 'Firstname/ Lastname/ Employee' Textbox bị Disable
		Assert.assertTrue(driver.findElement(firstNameTextboxBy).isEnabled());
		Assert.assertTrue(driver.findElement(lastNameTextboxBy).isEnabled());
		Assert.assertTrue(driver.findElement(employeeIdTextboxBy).isEnabled());
		sleepInSecond(3);
		// Verify 'Firstname/ Lastname/ Employee' matching vs Inputed value
		//Assert.assertEquals(driver.findElement(firstNameTextboxBy).getAttribute("value"), editFirstName);
		//Assert.assertEquals(driver.findElement(lastNameTextboxBy).getAttribute("value"), editLastName);
		//Assert.assertEquals(driver.findElement(employeeIdTextboxBy).getAttribute("value"), employeeID);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		driver.findElement(By.xpath("//body/div[@id='app']/div[@class='oxd-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='orangehrm-card-container']/div[@class='orangehrm-edit-employee']/div[@class='orangehrm-edit-employee-content']/div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']/div[@class='orangehrm-action-header']/button[1]")).click();
		driver.findElement(By.xpath("(//input)[4]")).sendKeys("533380009");
		driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).sendKeys("Comment a");
		driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
		
		driver.findElement(By.xpath("//div[contains(text(),'Passport')]")).click();
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']")).click();
		
		String passport = driver.findElement(By.xpath("//body/div[@id='app']/div[@class='oxd-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='orangehrm-card-container']/div[@class='orangehrm-edit-employee']/div[@class='orangehrm-edit-employee-content']/div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']/form[@class='oxd-form']/div[@class='oxd-form-row']/div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[1]/div[1]/div[2]/input[1]")).getAttribute("value");
		Assert.assertEquals(passport, "533380009");
	}

	@Test
	public void Register_02_Invalid_Email() {

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
