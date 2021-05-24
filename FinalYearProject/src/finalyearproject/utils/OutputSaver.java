package finalyearproject.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;

public class OutputSaver {

	private TextFileWriter textFileWriter;
	private ImageDownloader imageDownloader;

	public OutputSaver(String outputFileName) throws IOException {
		this.textFileWriter = new TextFileWriter(outputFileName);
		this.imageDownloader = new ImageDownloader(outputFileName);
	}
	
	public TextFileWriter getTextFileWriter() {
		return this.textFileWriter;
	}
	
	public ImageDownloader getImageDownloader() {
		return this.imageDownloader;
	}

	public class TextFileWriter {
		private BufferedWriter writer;
		private File file;

		private TextFileWriter(String outputFileName) throws IOException {
			file = new File("data/output/" + outputFileName + "/" + outputFileName + ".txt");
			FileWriter fileWriter = new FileWriter(file);
			this.writer = new BufferedWriter(fileWriter);
		}

		public void saveOutputData(Map<String, String> outputData, boolean append) throws IOException {

			if (append) {
				outputData.forEach((key, value) -> {
					try {
						writer.append(key + ": " + value + "\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
				writer.append("\n\n");
			} else {
				outputData.forEach((key, value) -> {
					try {
						writer.write(key + ": " + value + "\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
				writer.append("\n\n");
			}
		}

		public void saveOutputData(Map.Entry<String, String> outputData, boolean append) throws IOException {

			if (append) {
				writer.append(outputData.getKey() + ": " + outputData.getValue() + "\n");
			} else {
				writer.write(outputData.getKey() + ": " + outputData.getValue() + "\n");
			}
		}
		
		public void writeTabKey(boolean append) throws IOException {
			
			if (append) {
				writer.append("\t");
			} else {
				writer.write("\t");
			}
		}

		public void close() throws IOException {
			this.writer.close();
		}
	}

	public class ImageDownloader {

		private File file;
		private final String outputFolderName;
		int imageCounter = 1;

		private ImageDownloader(String outputFolderName) {
			this.outputFolderName = outputFolderName;
			file = new File("data/output/" + outputFolderName + "/" + imageCounter + ".png");
		}
		
		public void saveImageFromScreenshot(File image, WebElement element) throws IOException {
			File file = this.file;
			FileUtils.copyFile(image, file);
			
			imageCounter++;
		}
		
		public void setFileName(String name) {
			this.file = new File("data/output/" + outputFolderName + "/" + name + ".png");
		}
		
	}
}
