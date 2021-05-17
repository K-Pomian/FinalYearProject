package finalyearproject.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class TextFileWriter {
	
	private BufferedWriter writer;
	
	public TextFileWriter(String outputPath) throws IOException {
		File file = new File("data/output/" + outputPath + ".txt");
		FileWriter fileWriter = new FileWriter(file);
		this.writer = new BufferedWriter(fileWriter);
	}
	
	public void saveOutputData(Map<String, String> outputData) throws IOException {
		outputData.forEach((key, value) -> {
			try {
				writer.write(key +  ": " + value + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		close();
	}
	
	public void close() throws IOException {
		this.writer.close();
	}
}
