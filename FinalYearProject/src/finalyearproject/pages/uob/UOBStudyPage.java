package finalyearproject.pages.uob;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import finalyearproject.patterns.WebPage;

public class UOBStudyPage extends WebPage {

	public UOBStudyPage(WebDriver webDriver) {
		super(webDriver);
	}
	
	public WebElement undergraduate = this.findElementByXpath("//a[contains(text(), 'Undergraduate')]");

}
