package finalyearproject.pages.test01;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import finalyearproject.patterns.WebPage;

public class UOBComputerSciencePlacement extends WebPage {

	public UOBComputerSciencePlacement(WebDriver webDriver, WebDriverWait waiter) {
		super(webDriver, waiter);
	}
	
	public List<WebElement> entryRequirements = findElementsByXpath("//div[@id='entryReqInner']/div[@class='row']").subList(1, 8);

}
