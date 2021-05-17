package finalyearproject.launcher;

import java.io.IOException;
import java.net.MalformedURLException;

import finalyearproject.tests.uobtests.UOBTest01;
import finalyearproject.utils.SpreadsheetReader;
import finalyearproject.utils.TestData;
import finalyearproject.utils.enums.TestType;

public class Launcher {

	private static TestData testData;

	public static void main(String[] args) {
		setup();

		try {
			UOBTest01 test = new UOBTest01(TestType.UOB.getUrl(), testData.getInputs(TestType.UOB));
			test.run();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private static void setup() {
		try {
			SpreadsheetReader reader = new SpreadsheetReader();
			testData = reader.getTestData();
			testData.getActiveTests().forEach(test -> System.out.println(test.toString()));
			testData.getInputs(TestType.UOB).forEach((key, value) -> System.out.println(key + ": " + value));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
