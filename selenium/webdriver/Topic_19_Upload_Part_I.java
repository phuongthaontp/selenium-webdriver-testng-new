package webdriver;

import java.io.File;
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

public class Topic_19_Upload_Part_I {
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
		//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();

		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();
		
		System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		driver = new EdgeDriver();
		
		// Wait cho các trạng thái của element: visible/ presence/ invisible/ staleness
		// Visible: Phải thấy được trên UI/ Presence: Phải có ở trong HTML
		expliciWait = new WebDriverWait(driver, 15);


		// Wait cho việc tìm element (findElement)
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	
	@Test
	public void TC_01_Upload_Sendkey_One_File_Per_Time() {	
	
		// Step 1
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		By uploadFile = By.cssSelector("input[type='file']");
		
		// Load file lên, chưa thực hiện Upload / Browse file	
		for (String fileName:fileNames) {
			driver.findElement(uploadFile).sendKeys(uploadFileFolderPath + fileName);		
		}
		
		// Verify Image đc load lên
		for (String fileName:fileNames) {
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() ='" + fileName + "']")).isDisplayed());
		}
		
		// Thực hiện Upload
		List<WebElement> startButtonElements = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement startButton:startButtonElements) {
			startButton.click();
			sleepInSecond(2);			
		}
		
		// Verify Image đc Upload
		for (String fileName:fileNames) {
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name']/a[text() ='" + fileName + "']")).isDisplayed());
		}
		}

	@Test
	public void TC_02_Upload_Sendkey_Multi_File_Per_Time() {	
		// Step 1
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		By uploadFile = By.cssSelector("input[type='file']");
		
		// Load file lên, chưa thực hiện Upload / Browse file
		driver.findElement(uploadFile).sendKeys(hungFilePath + "\n" + lamFilePath + "\n" + nguyenFilePath + "\n" + catFilePath);		
		
		// Verify image load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hung + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + lam + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + nguyen + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + cat + "']")).isDisplayed());
		
		// Thực hiện Upload
		List<WebElement> startButtonElements = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement startButton:startButtonElements) {
			startButton.click();
			sleepInSecond(2);			
		}
		
		// Verify Image đc Upload
		for (String fileName:fileNames) {
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name']/a[text() ='" + fileName + "']")).isDisplayed());
		}
		}		
		
	
	
	@Test
	public void TC_03_Frame_HDFC() {
		
	
		
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


