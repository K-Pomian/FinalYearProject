package finalyearproject.pages.uob;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import finalyearproject.patterns.WebPage;

public class UOBMainPage extends WebPage {

	public UOBMainPage(WebDriver webDriver) {
		super(webDriver);
	}
	
	public WebElement study = this.findElementByXpath("//a[contains(text(), 'Study')]");

}
