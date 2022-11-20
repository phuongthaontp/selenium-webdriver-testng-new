package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;


public class Depend {
  @Test
  public void TC_01_Create() {
	  System.out.println("TC01");
	  Assert.assertTrue(true);
  }
  @Test(dependsOnMethods = "TC_01_Create")
  public void TC_02_Read() {
	  System.out.println("TC02");
	  Assert.assertTrue(false);
  }
  
  @Test(dependsOnMethods = "TC_02_Read")
  public void TC_03_Update() {
	  System.out.println("TC03");  
  }
  @Test(dependsOnMethods = "TC_03_Update")
  public void TC_04_Delete() {
	  System.out.println("TC04");  
		  
  }

}
