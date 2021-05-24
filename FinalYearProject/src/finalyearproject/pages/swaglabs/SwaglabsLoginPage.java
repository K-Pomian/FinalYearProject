package finalyearproject.pages.swaglabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import finalyearproject.patterns.WebPage;

public class SwaglabsLoginPage extends WebPage {

	public SwaglabsLoginPage(WebDriver webDriver) {
		super(webDriver);
	}
	
	public WebElement username = this.findElementByXpath("//input[@id='user-name']");
	
	public WebElement password = this.findElementByXpath("//input[@id='password']");
	
	public WebElement loginButton = this.findElementByXpath("//input[@id='login-button']");

}
