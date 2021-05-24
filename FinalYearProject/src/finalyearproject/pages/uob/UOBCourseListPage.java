package finalyearproject.pages.uob;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import finalyearproject.patterns.WebPage;

public class UOBCourseListPage extends WebPage {

	public UOBCourseListPage(WebDriver webDriver) {
		super(webDriver);
	}
	
	public WebElement notification = findElementByXpath("(//span[contains(text(), 'Your search')])[1]");
	
	public List<WebElement> results = findElementsByXpath("//div[contains(@class, 'ei')]");
	
	public WebElement computerScienceWithPlacement = findElementByXpath("//a[@href=\"/courses/ug/computer-science-bsc/\"]");
}
