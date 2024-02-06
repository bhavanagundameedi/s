package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

public	WebDriver  driver;
public LandingPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements( driver,this);
}

@FindBy(xpath="//a[@title='My Account']")
WebElement myAccDropDown;
@FindBy(xpath="//a[contains(text(),'Login')]")
WebElement LoginBtn;


public WebElement getMyAccDropDown() {
	return myAccDropDown;
}
public WebElement getLoginBtn() {
	return LoginBtn;
}

}
