package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;



import resource.Base;
import resource.ExtentReporter;

public class Listeners extends Base implements ITestListener{
	  
			WebDriver driver=null;
			ExtentTest extenTest;
			ExtentReports extentReport=ExtentReporter.getExtentReport();
			ThreadLocal<ExtentTest> extentThread= new ThreadLocal<ExtentTest>();
			@Override
			public void onTestStart(ITestResult result) {
				
			 extenTest = extentReport.createTest(result.getName()+" execution started");
			 extentThread.set(extenTest);
			}

			@Override
			public void onTestSuccess(ITestResult result) {
				extentThread.get().log(Status.PASS, result.getName()+" got passed");
			}

			@Override
			public void onTestFailure(ITestResult result) {
				
				
				String testMethodName=result.getName();
				extentThread.get().fail(result.getThrowable());
				
				try {
					driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
				} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					String screenshotPath=takeScreenShot(testMethodName, driver);
					extentThread.get().addScreenCaptureFromPath(screenshotPath, testMethodName);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

			@Override
			public void onFinish(ITestContext context) {
				extentReport.flush();
			}

		}

	