package finalyearproject.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import finalyearproject.exceptions.SizeMismatchException;
import finalyearproject.exceptions.StatusMismatchException;
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
		Map<String, TestType> activeTestsWithCorrespondingTypes = fetchActiveTests();
		Set<TestType> activeTypesTests = Sets.newLinkedHashSet(activeTestsWithCorrespondingTypes.values());
		List<String> activeTests = Lists.newArrayList(activeTestsWithCorrespondingTypes.keySet());
		List<Map<String, String>> allInputsForActiveTests = fetchAllInputsForActiveTests(activeTypesTests);
		
		Iterator<TestType> activeTestsIterator = activeTypesTests.iterator();
		Iterator<Map<String, String>> allInputsForActiveTestsIterator = allInputsForActiveTests.iterator();
		
		if (activeTypesTests.size() != allInputsForActiveTests.size()) {
			throw new SizeMismatchException("Size of 'activeTests' is different from size of 'allInputsForActiveTests'");
		}
		
		while (activeTestsIterator.hasNext()) {
			testData.setTestData(activeTestsIterator.next(), allInputsForActiveTestsIterator.next());
		}
		
		testData.setActiveTestsWithCorrespondingTypes(activeTestsWithCorrespondingTypes);
		testData.setActiveTestTypes(activeTypesTests);
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
	
	private Map<String, TestType> fetchActiveTests() {
		Sheet sheet = workbook.getSheet("ActiveTests");
		
		Map<String, TestType> activeTests = new LinkedHashMap<String, TestType>();
		Iterator<Row> rowIterator = sheet.rowIterator();
		rowIterator.next();
		while(rowIterator.hasNext()) {
			Row currentRow = rowIterator.next();
			
			Cell testNameCell = currentRow.getCell(0);
			String testName = testNameCell.getStringCellValue();
			
			Cell testTypeCell = currentRow.getCell(1);
			String testType = testTypeCell.getStringCellValue();
			
			Cell statusCell = currentRow.getCell(2);
			String status = statusCell.getStringCellValue();
			
			switch(status) {
			case "Active": {
				if (Arrays.stream(TestType.values()).anyMatch(type -> type.toString().equals(testType))) {
					activeTests.put(testName, TestType.valueOf(testType));
				} else {
					throw new TestTypeMismatchException("Test type " + testType + " is invalid");
				}
			} case "Inactive": {
				break;
			} default : {
				throw new StatusMismatchException("Wrong status of test " + testName);
			}	
			}
		}
		
		return activeTests;
	}
	
	private void close() throws IOException {
		workbook.close();
	}
}
