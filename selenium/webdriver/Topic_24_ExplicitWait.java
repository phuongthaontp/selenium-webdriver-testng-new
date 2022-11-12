package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_ExplicitWait {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");

	
	String osName = System.getProperty("os.name").toLowerCase();

	
	// Image Name
	String hung = "H.jpg";
	String lam = "L.jpg";
	String nguyen = "N.jpg";
	String cat = "C.jpg";
	
	// Upload file folder
	String uploadFileFolderPath = projectPath + File.separator + "uploadFiles"+ File.separator;
	
	// Image Path
	String hungFilePath = uploadFileFolderPath + hung;
	String lamFilePath = uploadFileFolderPath + lam;
	String nguyenFilePath = uploadFileFolderPath + nguyen;
	String catFilePath = uploadFileFolderPath + cat;
	
	List<String> fileNames = getFileNameInFolder();
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();
		
		//System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		//driver = new EdgeDriver();
		
		// Wait cho các trạng thái của element: visible/ presence/ invisible/ staleness
		// Visible: Phải thấy được trên UI/ Presence: Phải có ở trong HTML



		// Wait cho việc tìm element (findElement)
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	

	public void TC_01_Invisible() {	
		driver.get("https://automationfc.github.io/dynamic-loading/");
		expliciWait = new WebDriverWait(driver, 15);
		// Click vào Start button
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		
		// Wait cho loading icon  biến mất 
		
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

		
	}
	
	public void TC_02_Ajac_Loading() {	
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		expliciWait = new WebDriverWait(driver, 15);
		
		// Wait cho Date Picker được hiển thị
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar")));
		
		
		// Verify No selected date to display
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
		
		
		// Wait cho ngày 19 được phép click
		// Sở dĩ phải chọn tobeclickable vì có thể page chưa ready thì click vào nó ko được
		
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='19']")));
		
		// Click vào ngày 19
		driver.findElement(By.xpath("//a[text()='19']")).click();
		
		// Wait cho đến khi Ajac loading icon biến mất trong 15s
		// id* có nghĩa là lấy content của id
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.Radiv")));
		
		// Wait cho ngày vừa được click là clickable
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']//a[text()='19']")));
		
		// Verify text Selected Date là ngày 19 được hiển thị
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "Saturday, November 19, 2022");

		
	}
			
	@Test
	public void TC_03_Upload_File() throws AWTException {	
		driver.get("https://gofile.io/?t=uploadFiles");
		
		expliciWait = new WebDriverWait(driver, 15);
		
		By addFileButtonBy = By.xpath("//button[@class='btn btn-primary btn-lg mb-1 uploadButton']");
		
		By uploadSuccessTextBy = By.xpath("//a[@id='rowUploadSuccess-downloadPage']");
		
		// Wait cho Add files button visible

		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(addFileButtonBy));
		
		//driver.findElement(addFileButtonBy).click();
		//sleepInSecond(3);
		
		//loadFileByRobot(hungFilePath);
		
		// Nếu muốn dùng upload file = sendkey thì phải tìm element //input[@type='file']
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(hungFilePath + "\n" + lamFilePath + "\n" + nguyenFilePath);
		
		expliciWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div#rowUploadProgress-list div.progress"))));
		
		WebElement rowLoading = driver.findElement(By.xpath("//div[@class ='text-center']/i[@class='fas fa-spinner fa-spin']"));
		
			
		expliciWait.until(ExpectedConditions.stalenessOf(rowLoading));
		
		//expliciWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h5[text()= 'Your files have been successfully uploaded']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()= 'Your files have been successfully uploaded']")).isDisplayed());
		
		expliciWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(uploadSuccessTextBy)));
		
		// Có thể vừa wait vừa click luôn như sau
		//expliciWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(uploadSuccessTextBy))).click();
		
		String homepageURL = driver.getCurrentUrl();
		
		driver.findElement(uploadSuccessTextBy).click();
		
		switchToWindowsByID(homepageURL);
		
		expliciWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='contentName']")));
		
	
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + hung + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + lam + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + nguyen + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")).isDisplayed());
		
		
		// Có thể viết gộp như sau để vừa wait vừa confirm Element hiển thị như sau
		// Verify button Download hiển thị
		Assert.assertTrue(expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + hung + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		Assert.assertTrue(expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + lam + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		Assert.assertTrue(expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + nguyen + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		
		// Verify button Play hiển thị
		Assert.assertTrue(expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + hung + "']/parent::a/parent::div/following-sibling::div//button[contains(@class, 'contentPlay')]"))).isDisplayed());
		Assert.assertTrue(expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + lam + "']/parent::a/parent::div/following-sibling::div//button[contains(@class, 'contentPlay')]"))).isDisplayed());
		Assert.assertTrue(expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + nguyen + "']/parent::a/parent::div/following-sibling::div//button[contains(@class, 'contentPlay')]"))).isDisplayed());

	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void switchToWindowsByID (String currentURL){
		Set<String> listPageWindowsID = driver.getWindowHandles();
		for (String id : listPageWindowsID) {
			// Nếu như cái id nào mà khác vs id của homepage 
		if (!id.equals(currentURL)) {
			// Thì switch qua id của tab đó
			driver.switchTo().window(id);
		}
		}
		}
	public  List<String> getFileNameInFolder(){
	File directoryPath = new File(uploadFileFolderPath);
	// List of all files and directories
	String contents[] = directoryPath.list();
	
	List<String> fileNames= new ArrayList<String>();
	for (int i = 0; i < contents.length; i++) {
		System.out.println(contents[i]);
		fileNames.add(contents[i]);
	}
	return fileNames;
	}
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void loadFileByRobot(String filePath) throws AWTException {
		// Load file bằng Robot
		// Giả lập hành vi COPY path của 1 file vào Open File Dialog -> Java support
		// Giả lập hành vi PASTE và ENTER vào Open File Dialog

		StringSelection select = new StringSelection(hungFilePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		
		// Load file
		Robot robot = new Robot();
		sleepInSecond(1);
		
		// Nhan phim Enter
		robot.keyPress(KeyEvent.VK_ENTER);		
		robot.keyRelease(KeyEvent.VK_ENTER);		
		
		// Nhan xuong Ctrl - V
		robot.keyPress(KeyEvent.VK_CONTROL);		
		robot.keyPress(KeyEvent.VK_V);		
		
		// Nha Ctrl - V
		robot.keyRelease(KeyEvent.VK_CONTROL);		
		robot.keyRelease(KeyEvent.VK_V);	
		
		// Nhan phim Enter
		robot.keyPress(KeyEvent.VK_ENTER);		
		robot.keyRelease(KeyEvent.VK_ENTER);
		
	}
	
}

