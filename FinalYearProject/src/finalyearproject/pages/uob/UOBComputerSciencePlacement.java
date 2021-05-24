package finalyearproject.pages.uob;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import finalyearproject.patterns.WebPage;

public class UOBComputerSciencePlacement extends WebPage {

	public UOBComputerSciencePlacement(WebDriver webDriver) {
		super(webDriver);
	}
	
	public List<WebElement> entryRequirements = findElementsByXpath("//div[@id='entryReqInner']/div[@class='row']").subList(1, 8);

}
