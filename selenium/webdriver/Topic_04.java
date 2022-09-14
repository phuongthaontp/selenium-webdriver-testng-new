package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://pastecode.io/s/zztef7o7");
	}

	@Test
	public void TC_01_VerifyUrl() {
		String techPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(techPageUrl, "https://pastecode.io/s/zztef7o7");
		/* WebElement myAccountfooter = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
		myAccountfooter.click();
		String techPageUrllogin = driver.getCurrentUrl();
		Assert.assertEquals(techPageUrllogin, "http://www.live.techpanda.org/index.php/customer/account/login");
		WebElement createAnAcountbtn = driver.findElement(By.xpath("//span[contains(text(),'Create An Account')"));
		createAnAcountbtn.click();
		String techPageUrlregister = driver.getCurrentUrl();
		Assert.assertEquals(techPageUrlregister, "http://www.live.techpanda.org/index.php/customer/account/create");
		driver.quit();
*/

}
}