package finalyearproject.tests.uobtests;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import finalyearproject.exceptions.ResultMismatchException;
import finalyearproject.pages.test01.UOBComputerSciencePlacement;
import finalyearproject.pages.test01.UOBCourseListPage;
import finalyearproject.pages.test01.UOBMainPage;
import finalyearproject.pages.test01.UOBStudyPage;
import finalyearproject.pages.test01.UOBUndergraduatePage;
import finalyearproject.patterns.Test;

public class UOBTest01 extends Test {

	@Override
	protected void runTest() {
		UOBMainPage uobMainPage = new UOBMainPage(getWebDriver(), getWebDriverWait());
		clickOnElement(uobMainPage.study);

		UOBStudyPage uobStudyPage = new UOBStudyPage(getWebDriver(), getWebDriverWait());
		clickOnElement(uobStudyPage.undergraduate);

		UOBUndergraduatePage uobUndergraduatePage = new UOBUndergraduatePage(getWebDriver(), getWebDriverWait());
		scrollToElement(uobUndergraduatePage.searchCourse);
		sendValueToField(uobUndergraduatePage.searchCourse, "Computer Science");
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

	}

}
