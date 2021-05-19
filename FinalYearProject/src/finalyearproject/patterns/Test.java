package finalyearproject.patterns;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Test {
	
	private WebDriver webDriver;
	private WebDriverWait waiter;
	private final String start;
	private final Map<String, String> inputData;
	private Actions actions;
	
	public Test(String url, Map<String, String> inputData) throws MalformedURLException {
		this.start = url;
		this.inputData = inputData;
	}
	
	abstract protected void runTest(Map<String, String> inputData);
	
	public void run() {
		initialize(start.toString());
		runTest(inputData);
		finishTest();
	}
	
	private void initialize(String url) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\pomia\\git\\FinalYearProject\\FinalYearProject\\assets\\chromedriver.exe");
		this.setWebDriver(new ChromeDriver());
		
		actions = new Actions(this.webDriver);
		
		webDriver.get(url);
	}
	
	protected void navigateTo(String url) {
		webDriver.navigate().to(url);
	}
	
	protected void clickOnElement(WebElement element) {
		actions.click(element).build().perform();
	}
	
	protected void scrollDownAndClickOnElement(WebElement element, int pixels) {
		scrollDown(pixels);
		clickOnElement(element);
	}
	
	protected void scrollToElement(WebElement element) {
		actions.moveToElement(element).build().perform();
	}
	
	protected void scrollDown(int pixels) {
		((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,arguments[0])", pixels);
	}
	
	protected void sendValueToField(WebElement element, String value) {
		actions.sendKeys(element, value).build().perform();
	}
	
	public File takeScreenshot(WebElement element) {
		WrapsDriver wrapsDriver = (WrapsDriver) element;
		File screenshot = ((TakesScreenshot) wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);
		return screenshot;
	}
	
	public String getCurrentUrl() {
		return webDriver.getCurrentUrl();
	}
	
	private void finishTest() {
		webDriver.quit();
	}
	
	public WebDriver getWebDriver() {
		return this.webDriver;
	}
	
	private void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public WebDriverWait getWebDriverWait() {
		return this.waiter;
	}
	
	public String getStart() {
		return this.start;
	}
	
}
