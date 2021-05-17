package finalyearproject.pages.uob;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import finalyearproject.patterns.WebPage;

public class UOBMainPage extends WebPage {

	public UOBMainPage(WebDriver webDriver, WebDriverWait waiter) {
		super(webDriver, waiter);
	}
	
	public WebElement study = this.findElementByXpath("//a[contains(text(), 'Study')]");

}
