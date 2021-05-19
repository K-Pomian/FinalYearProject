package finalyearproject.pages.evision;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import finalyearproject.patterns.WebPage;

public abstract class EvisionStudentPage extends WebPage {

	public EvisionStudentPage(WebDriver webDriver) {
		super(webDriver);
	}

	public WebElement course = this.findElementByXpath("//nav//ul[@role='menu']//a[text()='Course']");
	
	public WebElement contact = this.findElementByXpath("//nav//ul[@role='menu']//a[text()='Contact']");
	
	public WebElement enrolments = this.findElementByXpath("//nav//ul[@role='menu']//a[text()='Enrolments']");
	
	public WebElement marks = this.findElementByXpath("//nav//ul[@role='menu']//a[contains(text(), 'Marks')]");
	
	public WebElement notes = this.findElementByXpath("//nav//ul[@role='menu']//a[text()='Notes']");
	
	public WebElement finance = this.findElementByXpath("//nav//ul[@role='menu']//a[text()='Finance']");
	
	public WebElement learnerSupport = this.findElementByXpath("//nav//ul[@role='menu']//a[contains(text(), 'Learner')]");
}
