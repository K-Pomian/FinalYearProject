package finalyearproject.pages.evision;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import finalyearproject.patterns.WebPage;

public class EvisionLoginPage extends WebPage {

	public EvisionLoginPage(WebDriver webDriver) {
		super(webDriver);
	}
	
	public WebElement username = this.findElementByXpath("//input[@id='MUA_CODE.DUMMY.MENSYS']");
	
	public WebElement password = this.findElementByXpath("//input[@id='PASSWORD.DUMMY.MENSYS']");
	
	public WebElement logIn = this.findElementByXpath("//input[@value='Log in']");

}
