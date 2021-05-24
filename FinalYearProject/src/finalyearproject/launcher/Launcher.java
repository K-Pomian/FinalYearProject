package finalyearproject.launcher;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import finalyearproject.patterns.Test;
import finalyearproject.utils.SpreadsheetReader;
import finalyearproject.utils.TestData;

public class Launcher {

	private static TestData testData;

	public static void main(String[] args) {
		setup();

		testData.getActiveTests().forEach(test -> {
			try {
				@SuppressWarnings("unchecked")
				Class<Test> clazz = (Class<Test>) Class.forName("finalyearproject.tests." + test);
				Constructor<Test> constructor = clazz.getConstructor(String.class, Map.class);
				Test testInstance = constructor.newInstance(testData.getTestTypeForTest(test).getUrl(),
						testData.getInputs(testData.getTestTypeForTest(test)));
				testInstance.run();
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		});
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
