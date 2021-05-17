package finalyearproject.tests.uobtests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import finalyearproject.exceptions.ResultMismatchException;
import finalyearproject.pages.uob.UOBComputerSciencePlacement;
import finalyearproject.pages.uob.UOBCourseListPage;
import finalyearproject.pages.uob.UOBMainPage;
import finalyearproject.pages.uob.UOBStudyPage;
import finalyearproject.pages.uob.UOBUndergraduatePage;
import finalyearproject.patterns.Test;
import finalyearproject.utils.TextFileWriter;

public class UOBTest01 extends Test {

	public UOBTest01(String url, Map<String, String> inputData) throws MalformedURLException {
		super(url, inputData);
	}

	@Override
	protected void runTest(Map<String, String> inputData) {
		UOBMainPage uobMainPage = new UOBMainPage(getWebDriver(), getWebDriverWait());
		clickOnElement(uobMainPage.study);

		UOBStudyPage uobStudyPage = new UOBStudyPage(getWebDriver(), getWebDriverWait());
		clickOnElement(uobStudyPage.undergraduate);

		UOBUndergraduatePage uobUndergraduatePage = new UOBUndergraduatePage(getWebDriver(), getWebDriverWait());
		scrollToElement(uobUndergraduatePage.searchCourse);
		sendValueToField(uobUndergraduatePage.searchCourse, inputData.get("CourseName"));
		clickOnElement(uobUndergraduatePage.search);

		UOBCourseListPage uobCourseListPage = new UOBCourseListPage(getWebDriver(), getWebDriverWait());
		String notificationText = uobCourseListPage.notification.getText();
		int expectedNumberOfResults = Integer.parseInt(notificationText.replaceAll("[^0-9]", ""));
		int actualNumberOfResults = uobCourseListPage.results.size();

		if (expectedNumberOfResults != actualNumberOfResults) {
			throw new ResultMismatchException("Expected number of results doesn't match acutal number of results");
		}

		scrollToElement(uobCourseListPage.computerScienceWithPlacement);
		clickOnElement(uobCourseListPage.computerScienceWithPlacement);

		UOBComputerSciencePlacement uobComputerSciencePlacement = new UOBComputerSciencePlacement(getWebDriver(),
				getWebDriverWait());
		
		int lastIndex = uobComputerSciencePlacement.entryRequirements.size() - 1;
		scrollToElement(uobComputerSciencePlacement.entryRequirements.get(lastIndex));
		
		Map<String, String> requirements = new LinkedHashMap<>();
		uobComputerSciencePlacement.entryRequirements.forEach(element -> {
			String requirementXpath = "./div[1]/p/strong";
			String requirement = uobComputerSciencePlacement.findChildElementByXpath(element, requirementXpath)
					.getText();

			String valueXpath = "./div[2]/p";
			List<WebElement> valueElements = uobComputerSciencePlacement.findChildrenElementsByXpath(element,
					valueXpath);
			
			String value = "";
			for (WebElement valueElement : valueElements) {
				value += valueElement.getText() + " ";
			}

			requirements.put(requirement, value);
		});
		
		try {
			TextFileWriter writer = new TextFileWriter(this.getClass().getSimpleName());
			writer.saveOutputData(requirements);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
