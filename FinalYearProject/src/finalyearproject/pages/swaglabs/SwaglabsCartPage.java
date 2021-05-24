package finalyearproject.pages.swaglabs;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SwaglabsCartPage extends SwaglabsPage {

	public SwaglabsCartPage(WebDriver webDriver) {
		super(webDriver);
	}
	
	public List<WebElement> removeButtons = this.findElementsByXpath("//div[@class='cart_list']//button");
	
	public WebElement continueShopping = this.findElementByXpath("//button[@id='continue-shopping']");
	
	public WebElement checkout = this.findElementByXpath("//button[@id='checkout']");

}
