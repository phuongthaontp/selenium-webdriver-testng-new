package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Upload_Part_II {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String firefoxSinglePath = projectPath + "\\autoIT\\firefoxUploadOneTime.exe";
	String chromeSinglePath = projectPath + "\\autoIT\\chromeUploadOneTime.exe";
	String firefoxMultiplePath = projectPath + "\\autoIT\\firefoxUploadMultiple.exe";
	String chromeMultiplePath = projectPath + "\\autoIT\\chromeUploadMultiple.exe";
	
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
		//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		//driver = new EdgeDriver();
		
		// Wait cho c??c tr???ng th??i c???a element: visible/ presence/ invisible/ staleness
		// Visible: Ph???i th???y ???????c tr??n UI/ Presence: Ph???i c?? ??? trong HTML
		expliciWait = new WebDriverWait(driver, 15);


		// Wait cho vi???c t??m element (findElement)
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	
	@Test
	public void TC_01_Upload_One_File_Per_Time_AutoIT() throws IOException {	
	
		// Step 1
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.cssSelector("span.btn-success")).click();
		sleepInSecond(3);
		
		// Load file = AutoIT
		if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] {firefoxSinglePath, hungFilePath});
			sleepInSecond(3);
		}
		else if (driver.toString().contains("chrome") || driver.toString().contains("edge"))
		{
		Runtime.getRuntime().exec(new String[] {chromeSinglePath, hungFilePath});
		sleepInSecond(3);
		}
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hung + "']")).isDisplayed());
		
		// Th???c hi???n Upload
		List<WebElement> startButtonElements = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement startButton:startButtonElements) {
			startButton.click();
			sleepInSecond(2);			
		}
		
		// Verify Image ??c Upload
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hung + "']")).isDisplayed());
	}
	@Test
	public void TC_02_Upload_Multi_File_Per_Time_AutoIT() throws IOException {	
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.cssSelector("span.btn-success")).click();
		sleepInSecond(3);
		
		// Load file = AutoIT
		if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] {firefoxMultiplePath, hungFilePath, lamFilePath, nguyenFilePath});
			sleepInSecond(3);
		}
		else if (driver.toString().contains("chrome") || driver.toString().contains("edge"))
		{
		Runtime.getRuntime().exec(new String[] {chromeMultiplePath, hungFilePath, lamFilePath, nguyenFilePath});
		sleepInSecond(3);
		}


		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hung + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + lam + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + nguyen + "']")).isDisplayed());
		
		// Th???c hi???n Upload
		List<WebElement> startButtonElements = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement startButton:startButtonElements) {
			startButton.click();
			sleepInSecond(2);			
		}
				
		// Verify Image ??c Upload
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name']/a[text() ='" + hung + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name']/a[text() ='" + lam + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name']/a[text() ='" + nguyen + "']")).isDisplayed());

	}		
				

	
	@Test
	public void TC_03_Upload_One_File_Per_Time_Java_Robot() throws AWTException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");	
		
		driver.findElement(By.cssSelector("span.btn-success")).click();
		sleepInSecond(3);
		
		loadFileByRobot(hungFilePath);
		
		// Th???c hi???n Upload
		List<WebElement> startButtonElements = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement startButton:startButtonElements) {
			startButton.click();
			sleepInSecond(2);			
		}
				
		// Verify Image ??c Upload
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hung + "']")).isDisplayed());
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
	


   
	
	@AfterClass
	public void afterClass() {
		driver.quit();
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
	public String getCurrentDateTime()
	{
		return new Date().toString();
	}
}


