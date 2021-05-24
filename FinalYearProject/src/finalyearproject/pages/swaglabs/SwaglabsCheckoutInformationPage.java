package finalyearproject.pages.swaglabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SwaglabsCheckoutInformationPage extends SwaglabsPage {

	public SwaglabsCheckoutInformationPage(WebDriver webDriver) {
		super(webDriver);
	}
	
	public WebElement firstNameField = this.findElementByXpath("//input[@id='first-name']");
	
	public WebElement lastNameField = this.findElementByXpath("//input[@id='last-name']");
	
	public WebElement postalCodeField = this.findElementByXpath("//input[@id='postal-code']");
	
	public WebElement cancelButton = this.findElementByXpath("//button[@id='cancel']");
	
	public WebElement continueButton = this.findElementByXpath("//input[@id='continue']");

}
