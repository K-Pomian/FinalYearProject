package finalyearproject.patterns;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class WebPage {
	
	protected WebDriver webDriver;
	private WebDriverWait waiter;
	private final int timeout = 10;
	
	public WebPage(WebDriver webDriver) {
		this.webDriver = webDriver;
		this.waiter = new WebDriverWait(webDriver, timeout);
		
		JavascriptExecutor js = (JavascriptExecutor) this.webDriver;
		js.executeScript("return document.readyState");
	}
	
	public WebElement findElementByXpath(String xpath) {
		waitForElementToLoad(xpath);
		WebElement element = webDriver.findElement(By.xpath(xpath));
		return element;
	}
	
	public List<WebElement> findElementsByXpath(String xpath){
		waitForElementsToLoad(xpath);
		List<WebElement> elements = webDriver.findElements(By.xpath(xpath));
		return elements;
	}
	
	public WebElement findChildElementByXpath(WebElement element, String xpath) {
		waitForChildElementToLoad(element, xpath);
		WebElement child = element.findElement(By.xpath(xpath));
		return child;
	}
	
	public List<WebElement> findChildrenElementsByXpath(WebElement element, String xpath) {
		waitForChildrenElementsToLoad(element, xpath);
		List<WebElement> children = element.findElements(By.xpath(xpath));
		return children;
	}
	
	public WebDriverWait getWaiter() {
		return this.waiter;
	}
	
	private void waitForElementToLoad(String xpath) {
		waiter.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}
	
	private void waitForElementsToLoad(String xpath) {
		waiter.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
	}
	
	private void waitForChildElementToLoad(WebElement element, String xpath) {
		waiter.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(element, By.xpath(xpath)));
	}
	
	private void waitForChildrenElementsToLoad(WebElement element, String xpath) {
		waiter.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(element, By.xpath(xpath))).forEach(listElement -> {
			waiter.until(ExpectedConditions.elementToBeClickable(listElement));
		});;
	}
	
	public void waitForElementToLoad(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) this.webDriver;
		Boolean loaded = (Boolean) js.executeScript("return arguments[0].complete", element);
		if (!loaded.booleanValue()) {
			waitForElementToLoad(element);
		}
	}
	
}
