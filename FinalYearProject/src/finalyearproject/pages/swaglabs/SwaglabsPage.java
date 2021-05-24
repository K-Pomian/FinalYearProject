package finalyearproject.pages.swaglabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import finalyearproject.patterns.WebPage;

public abstract class SwaglabsPage extends WebPage {
	
	private HamburgerMenuList hamburgerMenuList;

	public SwaglabsPage(WebDriver webDriver) {
		super(webDriver);
	}
	
	public WebElement hamburgerMenu = this.findElementByXpath("//button[@id='react-burger-menu-btn']");
	
	public WebElement shoppingCart = this.findElementByXpath("//div[@id='shopping_cart_container']/a");
	
	private void initHamburgerMenuList() {
		this.hamburgerMenuList = new HamburgerMenuList(this.webDriver);
	}
	
	public HamburgerMenuList getHamburgerMenuList() {
		initHamburgerMenuList();
		return this.hamburgerMenuList;
	}
	
	public class HamburgerMenuList extends WebPage {
		
		private HamburgerMenuList(WebDriver webDriver) {
			super(webDriver);
		}
		
		public WebElement allItems = this.findElementByXpath("//a[@id='inventory_sidebar_link']");
		
		public WebElement about = this.findElementByXpath("//a[@id='about_sidebar_link']");
		
		public WebElement logout = this.findElementByXpath("//a[@id='logout_sidebar_link']");
		
		public WebElement reset = this.findElementByXpath("//a[@id='reset_sidebar_link']");
		
	}

}
