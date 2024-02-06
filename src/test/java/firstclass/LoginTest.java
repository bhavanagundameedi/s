package firstclass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.LandingPage;
import pageobjects.LoginPage;
import pageobjects.MyAccPage;
import resource.Base;
import resource.ExcelReader;

public class LoginTest extends Base {
	
		public WebDriver driver;
		@BeforeMethod
		public void openApplication() throws IOException {
			driver = initializeDriver();
			driver.get(prop.getProperty("url"));
		}

		@Test(dataProvider = "getLoginData")
		public void login(String uname, String pwd, String expected) {

			LandingPage lp= new LandingPage (driver);
			lp.getMyAccDropDown().click();
			lp.getLoginBtn().click();
			
			LoginPage log= new LoginPage(driver);
			log.getEmail().sendKeys(prop.getProperty("email"));
			log.getPassword().sendKeys(prop.getProperty("pwd"));
			log.getLoginButton().click();
			
			MyAccPage map = new MyAccPage(driver);
			String actual = null;
			try {
				if(map .getEditaccountInfo().isDisplayed()) {
					actual= "Success";
				
				}
			} catch (Exception e) {
				actual = "Failure";
			}

			Assert.assertEquals(actual, expected);
		}

		@AfterMethod
		public void tearDown() {
			driver.close();
		}

		@DataProvider
		public Object[][] getLoginData() throws IOException {
			ExcelReader excel= new ExcelReader();
			Object[][] exceldata=excel.excelRead();
			return exceldata;
		}

	}
