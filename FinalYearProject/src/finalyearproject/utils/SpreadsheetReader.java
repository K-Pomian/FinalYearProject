package finalyearproject.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import finalyearproject.exceptions.SizeMismatchException;
import finalyearproject.exceptions.TestTypeMismatchException;
import finalyearproject.utils.enums.TestType;

public class SpreadsheetReader {
	
	private Workbook workbook;
	private TestData testData;
	private final File inputFile = new File("data/Zeszyt1.xlsx");
	
	public SpreadsheetReader() throws IOException {
		FileInputStream stream = new FileInputStream(inputFile);
		this.workbook = new XSSFWorkbook(stream);
		this.testData = new TestData();
	}
	
	public TestData getTestData() throws IOException {
		Set<TestType> activeTests = fetchActiveTestTypes();
		List<Map<String, String>> allInputsForActiveTests = fetchAllInputsForActiveTests(activeTests);
		
		Iterator<TestType> activeTestsIterator = activeTests.iterator();
		Iterator<Map<String, String>> allInputsForActiveTestsIterator = allInputsForActiveTests.iterator();
		
		if (activeTests.size() != allInputsForActiveTests.size()) {
			throw new SizeMismatchException("Size of 'activeTests' is different from size of 'allInputsForActiveTests'");
		}
		
		while (activeTestsIterator.hasNext()) {
			testData.setTestData(activeTestsIterator.next(), allInputsForActiveTestsIterator.next());
		}
		
		testData.setActiveTests(activeTests);
		
		close();
		
		return this.testData;
	}
	
	private List<Map<String, String>> fetchAllInputsForActiveTests(Set<TestType> activeTestTypes) {
		List<Map<String, String>> allInputs = new ArrayList<Map<String, String>>();
		
		activeTestTypes.forEach(testType -> {
			Sheet sheet = workbook.getSheet(testType.toString());
			
			Row keys = sheet.getRow(0);
			Row values = sheet.getRow(1);
			
			Map<String, String> records = new HashMap<>();
			keys.forEach(cell -> {
				int index = cell.getColumnIndex();
				records.put(cell.getStringCellValue(), values.getCell(index).getStringCellValue());
			});
			
			allInputs.add(records);
		});	
		
		return allInputs;
	}
	
	private Set<TestType> fetchActiveTestTypes() {
		Sheet sheet = workbook.getSheet("ActiveTests");
		
		Set<TestType> activeTests = new LinkedHashSet<TestType>();
		Iterator<Row> rowIterator = sheet.rowIterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {
			Row currentRow = rowIterator.next();
			
			Cell testTypeCell = currentRow.getCell(1);
			String testType = testTypeCell.getStringCellValue();
			
			Cell statusCell = currentRow.getCell(2);
			boolean status = statusCell.getStringCellValue().equals("Active");
			
			if (Arrays.stream(TestType.values()).anyMatch(type -> type.toString().equals(testType))) {
				if (status) {
					activeTests.add(TestType.valueOf(testType));
				}
			} else {
				throw new TestTypeMismatchException("Test type " + testType + " is invalid");
			}
		}
		
		return activeTests;
	}
	
	private void close() throws IOException {
		workbook.close();
	}
}
