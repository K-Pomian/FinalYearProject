package finalyearproject.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import finalyearproject.pages.swaglabs.SwaglabsCartPage;
import finalyearproject.pages.swaglabs.SwaglabsCheckoutCompletePage;
import finalyearproject.pages.swaglabs.SwaglabsCheckoutInformationPage;
import finalyearproject.pages.swaglabs.SwaglabsCheckoutOverviewPage;
import finalyearproject.pages.swaglabs.SwaglabsInventoryPage;
import finalyearproject.pages.swaglabs.SwaglabsLoginPage;
import finalyearproject.patterns.Test;
import finalyearproject.utils.OutputSaver;

public class SwaglabsTest01 extends Test {

	public SwaglabsTest01(String url, Map<String, String> inputData) throws MalformedURLException {
		super(url, inputData);
	}

	@Override
	protected void runTest(Map<String, String> inputData) {
		OutputSaver outputSaver = null;
		try {
			outputSaver = new OutputSaver(this.getClass().getSimpleName());

			SwaglabsLoginPage swaglabsLoginPage = new SwaglabsLoginPage(this.getWebDriver());

			this.sendValueToField(swaglabsLoginPage.username, inputData.get("Username1"));
			this.sendValueToField(swaglabsLoginPage.password, inputData.get("Password"));
			this.clickOnElement(swaglabsLoginPage.loginButton);

			SwaglabsInventoryPage swaglabsInventoryPage = new SwaglabsInventoryPage(this.getWebDriver());

			for (WebElement item : swaglabsInventoryPage.items) {
				WebElement itemNameElement = swaglabsInventoryPage.findChildElementByXpath(item, ".//a/div");
				String itemName = itemNameElement.getText();

				outputSaver.getImageDownloader().setFileName(itemName);
				if (swaglabsInventoryPage.items.indexOf(item) == 3) {
					this.scrollDown(item.getSize().height);
				}

				WebElement itemImageElement = swaglabsInventoryPage.findChildElementByXpath(item, ".//img");
				swaglabsInventoryPage.waitForElementToLoad(itemImageElement);
				outputSaver.getImageDownloader().saveImageFromScreenshot(this.takeScreenshot(itemImageElement),
						itemImageElement);
			}

			this.clickOnElement(swaglabsInventoryPage.sortOptionsButton);

			Select sortOptions = new Select(swaglabsInventoryPage
					.findChildElementByXpath(swaglabsInventoryPage.sortOptionsButton, "./../select"));
			;

			for (int i = 0; i < sortOptions.getOptions().size(); i++) {
				Map<String, String> itemNamesAndPrices = new LinkedHashMap<String, String>();
				sortOptions.selectByIndex(i);

				swaglabsInventoryPage = new SwaglabsInventoryPage(this.getWebDriver());
				sortOptions = new Select(swaglabsInventoryPage
						.findChildElementByXpath(swaglabsInventoryPage.sortOptionsButton, "./../select"));

				List<WebElement> items = new ArrayList<WebElement>();
				items.addAll(
						swaglabsInventoryPage.findElementsByXpath("(//div[@id='inventory_container'])[2]/div/div"));

				for (WebElement item : items) {
					WebElement itemNameElement = swaglabsInventoryPage.findChildElementByXpath(item, ".//a/div");
					String itemName = itemNameElement.getText();

					WebElement itemPriceElement = swaglabsInventoryPage.findChildElementByXpath(item,
							".//div[@class='pricebar']/div");
					String itemPrice = itemPriceElement.getText();

					itemNamesAndPrices.put(itemName, itemPrice);
				}

				outputSaver.getTextFileWriter().saveOutputData(itemNamesAndPrices, i != 0);

				if (i != sortOptions.getOptions().size() - 1) {
					this.clickOnElement(swaglabsInventoryPage.sortOptionsButton);
				}

			}

			WebElement sauceLabsBackpackAdd = swaglabsInventoryPage
					.findChildElementByXpath(swaglabsInventoryPage.sauceLabsBackpack, ".//button");
			clickOnElement(sauceLabsBackpackAdd);
			swaglabsInventoryPage = new SwaglabsInventoryPage(this.getWebDriver());

			WebElement sauceLabsLightAdd = swaglabsInventoryPage
					.findChildElementByXpath(swaglabsInventoryPage.sauceLabsBikeLight, ".//button");
			clickOnElement(sauceLabsLightAdd);
			swaglabsInventoryPage = new SwaglabsInventoryPage(this.getWebDriver());
			clickOnElement(swaglabsInventoryPage.findChildElementByXpath(swaglabsInventoryPage.sauceLabsBikeLight, ".//button"));
			swaglabsInventoryPage = new SwaglabsInventoryPage(this.getWebDriver());

			WebElement sauceLabsBoltAdd = swaglabsInventoryPage
					.findChildElementByXpath(swaglabsInventoryPage.sauceLabsBoltTShirt, ".//button");
			clickOnElement(sauceLabsBoltAdd);
			swaglabsInventoryPage = new SwaglabsInventoryPage(this.getWebDriver());

			WebElement sauceLabsFleeceAdd = swaglabsInventoryPage
					.findChildElementByXpath(swaglabsInventoryPage.sauceLabsFleeceJacket, ".//button");
			clickOnElement(sauceLabsFleeceAdd);
			swaglabsInventoryPage = new SwaglabsInventoryPage(this.getWebDriver());

			clickOnElement(swaglabsInventoryPage.shoppingCart);

			SwaglabsCartPage swaglabsCartPage = new SwaglabsCartPage(this.getWebDriver());

			WebElement lastRemoveButton = swaglabsCartPage.removeButtons.get(swaglabsCartPage.removeButtons.size() - 1);
			clickOnElement(lastRemoveButton);

			clickOnElement(swaglabsCartPage.checkout);

			SwaglabsCheckoutInformationPage swaglabsCheckoutInformationPage = new SwaglabsCheckoutInformationPage(
					this.getWebDriver());

			sendValueToField(swaglabsCheckoutInformationPage.firstNameField, inputData.get("FirstName"));
			sendValueToField(swaglabsCheckoutInformationPage.lastNameField, inputData.get("LastName"));
			sendValueToField(swaglabsCheckoutInformationPage.postalCodeField, inputData.get("PostalCode"));
			clickOnElement(swaglabsCheckoutInformationPage.continueButton);

			SwaglabsCheckoutOverviewPage swaglabsCheckoutOverviewPage = new SwaglabsCheckoutOverviewPage(
					getWebDriver());

			Map<String, String> checkoutOutputData = new LinkedHashMap<String, String>();
			for (WebElement item : swaglabsCheckoutOverviewPage.items) {
				WebElement itemNameElement = swaglabsCheckoutOverviewPage.findChildElementByXpath(item, ".//a/div");
				String itemName = itemNameElement.getText();

				WebElement itemPriceElement = swaglabsCheckoutOverviewPage.findChildElementByXpath(item,
						".//div[@class='inventory_item_price']");
				String itemPrice = itemPriceElement.getText();

				checkoutOutputData.put(itemName, itemPrice);
			}

			String paymentInformation = swaglabsCheckoutOverviewPage.paymentInformationLabel.getText();
			String paymentInformationValue = swaglabsCheckoutOverviewPage.paymentInformation.getText();
			checkoutOutputData.put(paymentInformation, paymentInformationValue);

			String shippingInformation = swaglabsCheckoutOverviewPage.shippingInformationLabel.getText();
			String shippingInformationValue = swaglabsCheckoutOverviewPage.shippingInformation.getText();
			checkoutOutputData.put(shippingInformation, shippingInformationValue);

			String[] itemTotal = swaglabsCheckoutOverviewPage.itemTotal.getText().split(": ");
			checkoutOutputData.put(itemTotal[0], itemTotal[1]);

			String[] tax = swaglabsCheckoutOverviewPage.tax.getText().split(": ");
			checkoutOutputData.put(tax[0], tax[1]);

			String[] total = swaglabsCheckoutOverviewPage.total.getText().split(": ");
			checkoutOutputData.put(total[0], total[1]);

			outputSaver.getTextFileWriter().saveOutputData(checkoutOutputData, true);

			clickOnElement(swaglabsCheckoutOverviewPage.finish);

			SwaglabsCheckoutCompletePage swaglabsCheckoutCompletePage = new SwaglabsCheckoutCompletePage(
					getWebDriver());
			
			Map.Entry<String, String> notification = new AbstractMap.SimpleEntry<String, String>(
					swaglabsCheckoutCompletePage.notificationHeadnig.getText(),
					swaglabsCheckoutCompletePage.notification.getText());
			outputSaver.getTextFileWriter().saveOutputData(notification, true);
			
			outputSaver.getImageDownloader().setFileName("PonyExpress");
			outputSaver.getImageDownloader().saveImageFromScreenshot(
					this.takeScreenshot(swaglabsCheckoutCompletePage.ponyExpressImage),
					swaglabsCheckoutCompletePage.ponyExpressImage);
			
			clickOnElement(swaglabsCheckoutCompletePage.backHomeButton);
			
			swaglabsInventoryPage = new SwaglabsInventoryPage(getWebDriver());
			
			clickOnElement(swaglabsInventoryPage.hamburgerMenu);
			clickOnElement(swaglabsInventoryPage.getHamburgerMenuList().logout);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outputSaver != null) {
				try {
					outputSaver.getTextFileWriter().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
