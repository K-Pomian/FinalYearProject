package finalyearproject.launcher;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import finalyearproject.tests.uobtests.UOBTest01;
import finalyearproject.utils.SpreadsheetReader;

public class Launcher {
	public static void main(String[] args) {
		setup();
		
		UOBTest01 test = new UOBTest01();
		test.run("https://www.bradford.ac.uk/external/");
	}
	
	private static void setup() {
//		SpreadsheetReader csvReader = null;
//		try {
//			BufferedReader reader = new BufferedReader(new FileReader("data/Zeszyt1.csv"));
//			csvReader = new SpreadsheetReader(new CSVReader(reader));
//			
//			
//			Map<String, String> inputs = csvReader.getAllInputs();
//			
//			inputs.forEach((key, value) -> System.out.println(key + ": " + value));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (CsvException e) {
//			e.printStackTrace();
//		} finally {
//			if (csvReader != null) {
//				try {
//					csvReader.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
	}

}
