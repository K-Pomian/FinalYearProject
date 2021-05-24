package finalyearproject.pages.swaglabs;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SwaglabsCheckoutOverviewPage extends SwaglabsPage {

	public SwaglabsCheckoutOverviewPage(WebDriver webDriver) {
		super(webDriver);
	}
	
	public List<WebElement> items = this.findElementsByXpath("//div[@class='cart_item']");
	
	public WebElement paymentInformationLabel = this.findElementByXpath("(//div[@class='summary_info']/div[contains(@class,'summary')])[1]");
	
	public WebElement paymentInformation = this.findElementByXpath("(//div[@class='summary_info']/div[contains(@class,'summary')])[2]");
	
	public WebElement shippingInformationLabel = this.findElementByXpath("(//div[@class='summary_info']/div[contains(@class,'summary')])[3]");
	
	public WebElement shippingInformation = this.findElementByXpath("(//div[@class='summary_info']/div[contains(@class,'summary')])[4]");
	
	public WebElement itemTotal = this.findElementByXpath("(//div[@class='summary_info']/div[contains(@class,'summary')])[5]");
	
	public WebElement tax = this.findElementByXpath("(//div[@class='summary_info']/div[contains(@class,'summary')])[6]");
	
	public WebElement total = this.findElementByXpath("(//div[@class='summary_info']/div[contains(@class,'summary')])[7]");
	
	public WebElement cancel = this.findElementByXpath("//button[@id='cancel']");
	
	public WebElement finish = this.findElementByXpath("//button[@id='finish']");

}
