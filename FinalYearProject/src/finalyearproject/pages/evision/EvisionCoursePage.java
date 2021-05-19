package finalyearproject.pages.evision;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EvisionCoursePage extends EvisionStudentPage {

	public EvisionCoursePage(WebDriver webDriver) {
		super(webDriver);
	}
	
	public WebElement courseDetails = this.findElementByXpath("//tbody");

}
