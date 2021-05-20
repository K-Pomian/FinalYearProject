package finalyearproject.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import finalyearproject.utils.enums.TestType;

public class TestData {
	
	private Map<TestType, Map<String, String>> data;
	private Map<String, TestType> activeTestsWithCorrespondingTypes;
	private List<TestType> activeTestTypes;
	private List<String> activeTests;
	
	protected TestData() {
		this.data = new HashMap<TestType, Map<String,String>>();
		this.activeTestTypes = new ArrayList<TestType>();
		this.activeTests = new ArrayList<String>();
	}
	
	protected void setTestData(TestType testType, Map<String, String> data) {
		this.data.put(testType, data);
	}
	
	protected void setActiveTestTypes(Set<TestType> activeTestTypes) {
		this.activeTestTypes.addAll(activeTestTypes);
	}
	
	protected void setActiveTests(List<String> activeTests) {
		this.activeTests = activeTests;
	}
	
	protected void setActiveTestsWithCorrespondingTypes(Map<String, TestType> activeTestsWithCorrespondingTypes) {
		this.activeTestsWithCorrespondingTypes = activeTestsWithCorrespondingTypes;
	}
	
	public TestType getTestTypeForTest(String test) {
		return activeTestsWithCorrespondingTypes.get(test);
	}
	
	public Map<String, String> getInputs(TestType testType) {
		return data.get(testType);
	}
	
	public List<TestType> getActiveTestTypes() {
		return this.activeTestTypes;
	}
	
	public List<String> getActiveTests() {
		return this.activeTests;
	}
	
	
}
