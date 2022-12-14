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
		
		// Wait cho c??c tr???ng th??i c???a element: visible/ presence/ invisible/ staleness
		// Visible: Ph???i th???y ???????c tr??n UI/ Presence: Ph???i c?? ??? trong HTML



		// Wait cho vi???c t??m element (findElement)
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	

	public void TC_01_Invisible() {	
		driver.get("https://automationfc.github.io/dynamic-loading/");
		expliciWait = new WebDriverWait(driver, 15);
		// Click v??o Start button
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		
		// Wait cho loading icon  bi???n m???t 
		
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		// Get text v?? verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

		
	}
	
	public void TC_02_Ajac_Loading() {	
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		expliciWait = new WebDriverWait(driver, 15);
		
		// Wait cho Date Picker ???????c hi???n th???
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar")));
		
		
		// Verify No selected date to display
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
		
		
		// Wait cho ng??y 19 ???????c ph??p click
		// S??? d?? ph???i ch???n tobeclickable v?? c?? th??? page ch??a ready th?? click v??o n?? ko ???????c
		
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='19']")));
		
		// Click v??o ng??y 19
		driver.findElement(By.xpath("//a[text()='19']")).click();
		
		// Wait cho ?????n khi Ajac loading icon bi???n m???t trong 15s
		// id* c?? ngh??a l?? l???y content c???a id
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.Radiv")));
		
		// Wait cho ng??y v???a ???????c click l?? clickable
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']//a[text()='19']")));
		
		// Verify text Selected Date l?? ng??y 19 ???????c hi???n th???
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
		
		// N???u mu???n d??ng upload file = sendkey th?? ph???i t??m element //input[@type='file']
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(hungFilePath + "\n" + lamFilePath + "\n" + nguyenFilePath);
		
		expliciWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div#rowUploadProgress-list div.progress"))));
		
		WebElement rowLoading = driver.findElement(By.xpath("//div[@class ='text-center']/i[@class='fas fa-spinner fa-spin']"));
		
			
		expliciWait.until(ExpectedConditions.stalenessOf(rowLoading));
		
		//expliciWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h5[text()= 'Your files have been successfully uploaded']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()= 'Your files have been successfully uploaded']")).isDisplayed());
		
		expliciWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(uploadSuccessTextBy)));
		
		// C?? th??? v???a wait v???a click lu??n nh?? sau
		//expliciWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(uploadSuccessTextBy))).click();
		
		String homepageURL = driver.getCurrentUrl();
		
		driver.findElement(uploadSuccessTextBy).click();
		
		switchToWindowsByID(homepageURL);
		
		expliciWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='contentName']")));
		
	
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + hung + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + lam + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + nguyen + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")).isDisplayed());
		
		
		// C?? th??? vi???t g???p nh?? sau ????? v???a wait v???a confirm Element hi???n th??? nh?? sau
		// Verify button Download hi???n th???
		Assert.assertTrue(expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + hung + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		Assert.assertTrue(expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + lam + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		Assert.assertTrue(expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + nguyen + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		
		// Verify button Play hi???n th???
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
			// N???u nh?? c??i id n??o m?? kh??c vs id c???a homepage 
		if (!id.equals(currentURL)) {
			// Th?? switch qua id c???a tab ????
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
		// Load file b???ng Robot
		// Gi??? l???p h??nh vi COPY path c???a 1 file v??o Open File Dialog -> Java support
		// Gi??? l???p h??nh vi PASTE v?? ENTER v??o Open File Dialog

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

