package finalyearproject.pages.swaglabs;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SwaglabsInventoryPage extends SwaglabsPage {

	public SwaglabsInventoryPage(WebDriver webDriver) {
		super(webDriver);
	}
	
	public WebElement sauceLabsBackpack = this.findElementByXpath("//div[contains(text(),'Backpack')]/../../../..");
	
	public WebElement sauceLabsBikeLight = this.findElementByXpath("//div[contains(text(),'Light')]/../../../..");
	
	public WebElement sauceLabsBoltTShirt = this.findElementByXpath("//div[contains(text(),'Bolt')]/../../../..");
	
	public WebElement sauceLabsFleeceJacket = this.findElementByXpath("//div[contains(text(),'Fleece')]/../../../..");
	
	public WebElement sauceLabsOnesie = this.findElementByXpath("//div[contains(text(),'Onesie')]/../../../..");
	
	public WebElement TestAllTheThingsTShirtRed = this.findElementByXpath("//div[contains(text(),'Test')]/../../../..");
	
	public List<WebElement> items = this.findElementsByXpath("//div[@class='inventory_item']");
	
	public WebElement sortOptionsButton = this.findElementByXpath("((//div[@id='header_container']/div)[2]/div)[2]/span/span");
}
