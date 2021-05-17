package finalyearproject.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import finalyearproject.utils.enums.TestType;

public class TestData {
	
	private Map<TestType, Map<String, String>> data;
	private List<TestType> activeTests;
	
	protected TestData() {
		this.data = new HashMap<TestType, Map<String,String>>();
		this.activeTests = new ArrayList<TestType>();
	}
	
	protected void setTestData(TestType testType, Map<String, String> data) {
		this.data.put(testType, data);
	}
	
	protected void setActiveTests(Set<TestType> activeTests) {
		this.activeTests.addAll(activeTests);
	}
	
	public Map<String, String> getInputs(TestType testName) {
		return data.get(testName);
	}
	
	public List<TestType> getActiveTests() {
		return this.activeTests;
	}
	
	
}
