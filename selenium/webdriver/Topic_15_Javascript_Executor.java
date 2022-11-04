package webdriver;


import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.SwitchToFrame;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.agent.builder.AgentBuilder.CircularityLock.Default;

public class Topic_15_Javascript_Executor {
	WebDriver driver;
	Actions action; 
	JavascriptExecutor jsExecutor;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");

	
	String osName = System.getProperty("os.name").toLowerCase();
	Alert alert;
	String emailAddress;
	String loginPageUrl, userID, userPassword, customerID;
	String customerName, gender, dateOfBirth, addressInput, addressOutput, city, state, pinNumber, phoneNumber, email, password;
	
	
	
	
	@BeforeClass
	public void beforeClass() {
		
		// WIN
		if (osName.startsWith("windows")) {
			
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}

		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);

		emailAddress = "mstrang"+getRandomNumber()+"@"+"gmail.com";
		loginPageUrl = "https://demo.guru99.com/v4/";
		customerName = "Brian Tracy";
		gender = "male";
		dateOfBirth = "1950-01-31";
		addressInput = "123 Los Angeles\nUnited States";
		addressOutput = "123 Los Angeles United States";
		city = "New York";
		state = "California";
		pinNumber = "632565";
		phoneNumber = "0976757850";
		email = "briantracy"+getRandomNumber()+"@gmail.com";
		password = "123456";
		
		
		
		// Wait cho các trạng thái của element: visible/ presence/ invisible/ staleness
		// Visible: Phải thấy được trên UI/ Presence: Phải có ở trong HTML
		expliciWait = new WebDriverWait(driver, 15);


		// Wait cho việc tìm element (findElement)
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	@Test
	public void TC_01_Javascript_Executor() {
		// Selenium: WebElement click();
		
		// Selenium: Action click();
		
		// Selenium: JavascriptExecutor: click();
		
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(3);
		
		String domain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(domain, "live.techpanda.org");
		
		String url = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(url, "http://live.techpanda.org/");		
		
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(3);
		
		clickToElementByJS("//a[@title='IPhone']/following-sibling::div/div/button");
		sleepInSecond(3);
		
		String shoppingCartText = getInnerText();
		Assert.assertTrue(shoppingCartText.contains("IPhone was added to your shopping cart."));
		
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(5);
		String customerPageTitle = (String) executeForBrowser("return document.title;");
		Assert.assertEquals(customerPageTitle, "Customer Service");
		
		scrollToElementOnDown("//input[@id='newsletter']");
		sleepInSecond(3);
		
		sendkeyToElementByJS("//input[@id='newsletter']", emailAddress);
		sleepInSecond(3);
		
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(3);
		
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(5);
		
		String homePageDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(homePageDomain, "demo.guru99.com");

	}
	
	@Test
	public void TC_02_HTML5_Validation_Message() {
		driver.get("https://www.pexels.com/vi-vn/join-contributor/");
		
		By firstName = By.id("user_first_name");
		By email = By.id("user_email");
		By password = By.id("user_password");
		By createButton = By.xpath("//button[contains(text(),'Tạo tài khoản mới')]");
		
		driver.findElement(createButton).click();
		sleepInSecond(2);
		Assert.assertEquals(getElementValidationMessage(firstName), "Please fill out this field.");
		
		
		driver.findElement(firstName).sendKeys("Automation");
		driver.findElement(createButton).click();
		sleepInSecond(2);
		Assert.assertEquals(getElementValidationMessage(email), "Please fill out this field.");
		
		driver.findElement(email).sendKeys("123@234@567");
		driver.findElement(createButton).click();
		sleepInSecond(2);
		//Assert.assertEquals(getElementValidationMessage(email), "Please enter an email address.");	
		
		driver.findElement(email).clear();
		driver.findElement(email).sendKeys("automation@gmail.com");
		driver.findElement(createButton).click();
		sleepInSecond(2);
		Assert.assertEquals(getElementValidationMessage(password), "Please fill out this field.");	
		}
	
	@Test
	public void TC_03_Remove_Attribute() {
		driver.get(loginPageUrl);
		
		driver.findElement(By.xpath("//a[text()='here']")).click();
		
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		userPassword = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		driver.get(loginPageUrl);
		
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(userPassword);
		
		driver.findElement(By.name("btnLogin")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(),  "Welcome To Manager's Page of Guru99 Bank");
		Assert.assertEquals(driver.findElement(By.cssSelector("tr.heading3>td")).getText(), "Manger Id :"+" "+ userID);
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(By.name("name")).sendKeys(customerName);
		
		// Remove type attribute
		
		removeAttributeInDOM("//input[@id='dob']", "type");
		sleepInSecond(4);
		
		driver.findElement(By.name("dob")).sendKeys(dateOfBirth);
		driver.findElement(By.name("addr")).sendKeys(addressInput);
		driver.findElement(By.name("city")).sendKeys(city);
		driver.findElement(By.name("state")).sendKeys(state);
		driver.findElement(By.name("pinno")).sendKeys(pinNumber);
		driver.findElement(By.name("telephoneno")).sendKeys(phoneNumber);
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("sub")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(), "Customer Registered Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()= 'Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()= 'Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()= 'Birthdate']/following-sibling::td")).getText(), dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()= 'Address']/following-sibling::td")).getText(), addressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()= 'City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()= 'State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()= 'Pin']/following-sibling::td")).getText(), pinNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()= 'Mobile No.']/following-sibling::td")).getText(), phoneNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()= 'Email']/following-sibling::td")).getText(), email);
		
	
	}
		
	@Test
	public void TC_04_Image() {
	
		driver.get("https://automationfc.github.io/basic-form/");
	
		Assert.assertTrue(isImageLoaded("//img[@alt='User Avatar 05']"));
		Assert.assertFalse(isImageLoaded("//img[@alt='broken_04']"));
	}
		

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	private void sleepInSecond(int i) {
		// TODO Auto-generated method stub
		
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(By byLocator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(byLocator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
		
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

