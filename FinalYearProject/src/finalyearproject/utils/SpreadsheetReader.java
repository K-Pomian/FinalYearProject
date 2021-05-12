package finalyearproject.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import finalyearproject.utils.enums.TestName;

public class SpreadsheetReader {
	
	private Workbook workbook;
	private TestData testData;
	
	public SpreadsheetReader() throws IOException {
		FileInputStream stream = new FileInputStream(new File("data/Zeszyt1.csv"));
		this.workbook = new XSSFWorkbook(stream);
		this.testData = new TestData();
	}
	
	public Map<String, String> getAllInputs(List<TestName> testNames) {
//		List<String[]> rows = reader.readAll();
//		
//		String[] keys = rows.get(0)[0].split(";");
//		String[] values = rows.get(1)[0].split(";");
//		
//		Map<String, String> inputs = new HashMap<>();
//		for (int i = 0; i < keys.length; i++) {
//			inputs.put(keys[i], values[i]);
//		}
//		
		testNames.forEach(testName -> {
			Sheet sheet = workbook.getSheet(testName.toString());
			Map<String, String> records = new HashMap<>();
		});
		
		return new HashMap<String, String>();
	}
	
	public void close() throws IOException {
		
	}
}
