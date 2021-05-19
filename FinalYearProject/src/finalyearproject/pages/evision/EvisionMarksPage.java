package finalyearproject.pages.evision;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import finalyearproject.patterns.WebPage;

public class EvisionMarksPage extends EvisionStudentPage {

	public EvisionMarksPage(WebDriver webDriver) {
		super(webDriver);
	}
	
	public WebElement marksTable = this.findElementByXpath("//table");

}
