package finalyearproject.patterns;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import finalyearproject.utils.TextFileWriter;

public abstract class Test {
	
	private WebDriver webDriver;
	private WebDriverWait waiter;
	private final URL start;
	private final int timeout = 10;
	private final Map<String, String> inputData;
	private Actions actions;
	
	public Test(String url, Map<String, String> inputData) throws MalformedURLException {
		this.start = new URL(url);
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
		this.setWebDriverWait(new WebDriverWait(this.webDriver, timeout));
		
		actions = new Actions(this.webDriver);
		
		webDriver.get(url);
	}
	
	protected void navigateTo(String url) {
		webDriver.navigate().to(url);
	}
	
	protected void clickOnElement(WebElement element) {
		actions.click(element).build().perform();
	}
	
	protected void sendValueToField(WebElement element, String value) {
		actions.sendKeys(element, value).build().perform();
	}
	
	protected void scrollToElement(WebElement element) {
		actions.moveToElement(element).build().perform();
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
	
	private void setWebDriverWait(WebDriverWait waiter) {
		this.waiter = waiter;
	}
	
	public URL getStart() {
		return this.start;
	}
	
}
