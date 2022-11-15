package testNG;

import org.testng.annotations.Test;

public class Priority {

	@Test(enabled = false, description = "JIRA_0919 - Create a new Employee and verify employee success")
	public void EndUser_01_Register_Employee() {
		
	}
	@Test(priority = 2)
	public void EndUser_02_View_Employee() {
		
	}
	@Test(priority = 3)
	public void EndUser_03_Edit_Employee() {
		
	}
	@Test(enabled = false)
	public void EndUser_04_Delete_Employee() {
		
	}
}

