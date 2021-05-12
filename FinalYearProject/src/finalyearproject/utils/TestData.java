package finalyearproject.utils;

import java.util.Map;

import finalyearproject.utils.enums.TestName;

public class TestData {
	
	private Map<TestName, Map<String, String>> testData;
	
	public void setTestData(TestName testName, Map<String, String> data) {
		testData.put(testName, data);
	}
}
