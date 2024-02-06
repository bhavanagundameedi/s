package resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	
	


	 public   WebDriver driver;
	   public  Properties prop;
	    public WebDriver initializeDriver() throws IOException {

	         prop = new Properties();
	        
	        // Use File.separator for cross-platform compatibility
	        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + File.separator + "src"
	                + File.separator + "main" + File.separator + "java" + File.separator + "resource" + File.separator + "data.properties");

	        prop.load(fis);

	        String browser = prop.getProperty("browser");
	        if (browser.equalsIgnoreCase("chrome")) {
	            driver = new ChromeDriver();
	        } else if (browser.equalsIgnoreCase("firefox")) {
	            driver = new FirefoxDriver();
	        } else if (browser.equalsIgnoreCase("edge")) {
	            driver = new EdgeDriver();
	        }

	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	        return driver;
	    }
	    public String takeScreenShot(String testName,WebDriver driver) throws IOException {
			File SrcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String destPath= System.getProperty("user.dir")+"\\screenshots\\"+testName+".png";
			File destFile= new File(destPath);
			FileUtils.copyFile(SrcFile,destFile);
			return destPath;

	       
	    }
	}
