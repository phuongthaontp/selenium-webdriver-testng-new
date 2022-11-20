package listener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.reporters.Files;

import testNG.ListenerDemo;

public class ExtentReportListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		TakesScreenshot t = (TakesScreenshot) ListenerDemo.driver;
		
		File srcFile = t.getScreenshotAs(OutputType.FILE);
		
		try {
		File destFile = new File("./screenshot/"+result.getName()+".jpg");
		com.google.common.io.Files.copy(srcFile, destFile);
		Reporter.log("<a href='"+destFile.getAbsolutePath()+"'> <img src='"+destFile.getAbsolutePath()+"'height='100' width='100' /> </a>");
	}
	catch (IOException e) {
		e.printStackTrace();
	}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
	

}
