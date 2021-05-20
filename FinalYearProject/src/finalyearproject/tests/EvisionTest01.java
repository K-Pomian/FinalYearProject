package finalyearproject.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import finalyearproject.pages.evision.EvisionCoursePage;
import finalyearproject.pages.evision.EvisionLoginPage;
import finalyearproject.pages.evision.EvisionMarksPage;
import finalyearproject.patterns.Test;
import finalyearproject.utils.OutputSaver;

import org.openqa.selenium.WebElement;

public class EvisionTest01 extends Test {

	public EvisionTest01(String url, Map<String, String> inputData) throws MalformedURLException {
		super(url, inputData);
	}

	@Override
	protected void runTest(Map<String, String> inputData) {
		EvisionLoginPage evisionLoginPage = new EvisionLoginPage(this.getWebDriver());
		sendValueToField(evisionLoginPage.username, inputData.get("Username"));
		sendValueToField(evisionLoginPage.password, inputData.get("Password"));
		clickOnElement(evisionLoginPage.logIn);

		EvisionCoursePage evisionCoursePage = new EvisionCoursePage(this.getWebDriver());
		
		OutputSaver outputSaver = null;
		try {
			outputSaver = new OutputSaver(EvisionTest01.class.getSimpleName());
			WebElement imageElement = evisionCoursePage.findChildElementByXpath(evisionCoursePage.courseDetails, ".//img");
			outputSaver.saveImageFromScreenshot(this.takeScreenshot(imageElement), imageElement);

			Map<String, String> outputData = new LinkedHashMap<String, String>();
			evisionCoursePage.findChildrenElementsByXpath(evisionCoursePage.courseDetails, ".//th").forEach(element -> {
				List<WebElement> tds = new ArrayList<WebElement>();
				tds.addAll(evisionCoursePage.findChildrenElementsByXpath(element, "./following-sibling::td"));

				String key = element.getText();
				String value = "";

				for (WebElement td : tds) {
					value += td.getText() + "\t";
				}

				outputData.put(key, value);
			});

			outputSaver.saveOutputData(outputData, false);
		
		clickOnElement(evisionCoursePage.marks);

		EvisionMarksPage evisionMarksPage = new EvisionMarksPage(this.getWebDriver());
		List<WebElement> tbodies = new ArrayList<WebElement>();
		tbodies.addAll(
				evisionMarksPage.findChildrenElementsByXpath(evisionMarksPage.marksTable, "./tbody").subList(1, 5));

		
			Map<Map<String, String>, Map<String, String>> outputData2 = new LinkedHashMap<Map<String, String>, Map<String, String>>();
			tbodies.forEach(tbody -> {
				scrollToElement(tbody);

				List<WebElement> trsWithoutId = new ArrayList<WebElement>();
				trsWithoutId.addAll(evisionMarksPage.findChildrenElementsByXpath(tbody, "./tr[not(@id)]"));
				trsWithoutId = trsWithoutId.subList(0, trsWithoutId.size() - 1);
				
				scrollDown(trsWithoutId.get(0).getSize().height);
				
				for (int i = 0; i < trsWithoutId.size(); i++) {
					Map<String, String> moduleOutputData = new LinkedHashMap<String, String>();
					List<WebElement> moduleTds = new ArrayList<WebElement>();
					moduleTds.addAll(
							evisionMarksPage.findChildrenElementsByXpath(trsWithoutId.get(i), "./td").subList(0, 3));

					String moduleKey = moduleTds.get(0).getText();
					String moduleValue = moduleTds.get(1).getText() + "\t" + moduleTds.get(2).getText();
					moduleOutputData.put(moduleKey, moduleValue);

					clickOnElement(evisionMarksPage.findChildElementByXpath(moduleTds.get(1), ".//a"));

					List<WebElement> assessmentTrs = new ArrayList<WebElement>();
					assessmentTrs.addAll(evisionMarksPage.findChildrenElementsByXpath(trsWithoutId.get(i),
							"./following-sibling::tr[@id and count(preceding-sibling::tr[not(@id)])=" + (i + 1) + "]"));
					
					int pixels = 0;
					for (WebElement element : assessmentTrs) {
						pixels += element.getSize().height;
					}
					scrollDown(pixels);

					Map<String, String> assessmentOutputData = new LinkedHashMap<String, String>();
					assessmentTrs.forEach(assessmentTr -> {
						List<WebElement> assessmentTds = new ArrayList<WebElement>();
						assessmentTds.addAll(
								evisionMarksPage.findChildrenElementsByXpath(assessmentTr, "./td").subList(1, 3));
						assessmentTds.add(evisionMarksPage.findChildrenElementsByXpath(assessmentTr, "./td").get(4));

						String assessmentKey = assessmentTds.get(0).getText();
						String assessmentValue = assessmentTds.get(1).getText() + "\t" + assessmentTds.get(2).getText();

						assessmentOutputData.put(assessmentKey, assessmentValue);
					});

					outputData2.put(moduleOutputData, assessmentOutputData);
				}

			});

			for (Map.Entry<Map<String, String>, Map<String, String>> outputDataEntry : outputData2.entrySet()) {
				Map<String, String> moduleData = outputDataEntry.getKey();
				Map<String, String> assessmentData = outputDataEntry.getValue();

				Iterator<Map.Entry<String, String>> moduleDataIterator = moduleData.entrySet().iterator();
				Iterator<Map.Entry<String, String>> assessmentDataIterator = assessmentData.entrySet().iterator();

				while (moduleDataIterator.hasNext()) {
					Map.Entry<String, String> moduleDataEntry = moduleDataIterator.next();
					outputSaver.saveOutputData(moduleDataEntry, true);
				}
				
				while (assessmentDataIterator.hasNext()) {
					Map.Entry<String, String> assessmentDataEntry = assessmentDataIterator.next();
					outputSaver.writeTabKey(true);
					outputSaver.saveOutputData(assessmentDataEntry, true);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outputSaver != null) {
				try {
					outputSaver.closeTextWriter();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
