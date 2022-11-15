package testNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class NewTest {
  @Test(groups = "admin")
  public void TC_01() {
	  System.out.println("TC01");
  }
  @Test(groups = {"user", "id"})
  public void TC_02() {
	  System.out.println("TC02");
  }
  
  @Test(groups = "user")
  public void TC_03() {
	  System.out.println("TC03");  
		  
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("BeforeMethod"); 
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("AfterMethod");  
  }


  @BeforeClass
  public void beforeClass() {
	  System.out.println("BeforeClass");  
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("AfterClass");  
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("BeforeTest"); 
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("AfterTest"); 
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("BeforeSuite"); 
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("AfterSuite"); 
  }

}
