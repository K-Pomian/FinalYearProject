package finalyearproject.pages.test01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import finalyearproject.patterns.WebPage;

public class UOBUndergraduatePage extends WebPage {

	public UOBUndergraduatePage(WebDriver webDriver, WebDriverWait waiter) {
		super(webDriver, waiter);
	}
	
	public WebElement searchCourse = this.findElementByXpath("//input[@id='course_search_form_stripe_query']");
	
	public WebElement search = this.findElementByXpath("(//input[@value='Search'])[2]");

}
