package finalyearproject.pages.uob;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import finalyearproject.patterns.WebPage;

public class UOBStudyPage extends WebPage {

	public UOBStudyPage(WebDriver webDriver, WebDriverWait waiter) {
		super(webDriver, waiter);
	}
	
	public WebElement undergraduate = this.findElementByXpath("//a[contains(text(), 'Undergraduate')]");

}
