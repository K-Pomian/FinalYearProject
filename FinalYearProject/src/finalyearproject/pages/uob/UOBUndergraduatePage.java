package finalyearproject.pages.uob;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import finalyearproject.patterns.WebPage;

public class UOBUndergraduatePage extends WebPage {

	public UOBUndergraduatePage(WebDriver webDriver) {
		super(webDriver);
	}
	
	public WebElement searchCourse = this.findElementByXpath("//input[@id='course_search_form_stripe_query']");
	
	public WebElement search = this.findElementByXpath("(//input[@value='Search'])[2]");

}
