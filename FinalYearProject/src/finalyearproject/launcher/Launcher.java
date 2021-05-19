package finalyearproject.launcher;

import java.io.IOException;
import java.net.MalformedURLException;

import finalyearproject.tests.evisiontests.EvisionTest01;
import finalyearproject.tests.uobtests.UOBTest01;
import finalyearproject.utils.SpreadsheetReader;
import finalyearproject.utils.TestData;
import finalyearproject.utils.enums.TestType;

public class Launcher {

	private static TestData testData;

	public static void main(String[] args) {
		setup();

		try {
			EvisionTest01 test = new EvisionTest01(TestType.Evision.getUrl(), testData.getInputs(TestType.Evision));
			test.run();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private static void setup() {
		try {
			SpreadsheetReader reader = new SpreadsheetReader();
			testData = reader.getTestData();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
