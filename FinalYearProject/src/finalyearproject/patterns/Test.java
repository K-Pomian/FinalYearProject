package finalyearproject.patterns;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.imageio.ImageIO;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import finalyearproject.exceptions.DimensionMismatchException;

public abstract class Test {
	
	private WebDriver webDriver;
	private final String start;
	private final Map<String, String> inputData;
	private Actions actions;
	
	public Test(String url, Map<String, String> inputData) throws MalformedURLException {
		this.start = url;
		this.inputData = inputData;
	}
	
	abstract protected void runTest(Map<String, String> inputData);
	
	public void run() {
		try {
			initialize(start.toString());
			runTest(inputData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			finishTest();
		}
	}
	
	private void initialize(String url) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\pomia\\git\\FinalYearProject\\FinalYearProject\\assets\\chromedriver.exe");
		this.setWebDriver(new ChromeDriver());
		webDriver.manage().window().maximize();
		
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
	
	protected File takeScreenshot(WebElement element) {
		File screenshot = element.getScreenshotAs(OutputType.FILE);
		return screenshot;
	}
	
	protected boolean compareImages(File expectedImage, File actualImage) throws IOException {
		BufferedImage bufferedExpectedImage = ImageIO.read(expectedImage);
		BufferedImage bufferedActualImage = ImageIO.read(actualImage);
		
		int expectedWidth = bufferedExpectedImage.getWidth();
		int expectedHeight = bufferedExpectedImage.getHeight();
		
		int actualWidth = bufferedActualImage.getWidth();
		int actualHeight = bufferedActualImage.getHeight();
		
		if (expectedWidth != actualWidth || expectedHeight != actualHeight) {
			throw new DimensionMismatchException("Images have different dimensions");
		}
		
		for (int i = 0; i < expectedWidth; i++) {
			for (int j = 0; j < expectedHeight; j++) {
				if (bufferedExpectedImage.getRGB(i, j) != bufferedActualImage.getRGB(i, j)) {
					return false;
				}
			}
		}
		
		return true;
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
	
	public String getStart() {
		return this.start;
	}
	
}
