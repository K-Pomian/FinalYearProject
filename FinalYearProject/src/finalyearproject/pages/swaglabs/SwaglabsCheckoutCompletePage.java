package finalyearproject.pages.swaglabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SwaglabsCheckoutCompletePage extends SwaglabsPage {

	public SwaglabsCheckoutCompletePage(WebDriver webDriver) {
		super(webDriver);
	}
	
	public WebElement notificationHeadnig = this.findElementByXpath("//h2");
	
	public WebElement notification = this.findElementByXpath("//div[@class='complete-text']");
	
	public WebElement ponyExpressImage = this.findElementByXpath("//img[@alt='Pony Express']");
	
	public WebElement backHomeButton = this.findElementByXpath("//button[@id='back-to-products']");

}
