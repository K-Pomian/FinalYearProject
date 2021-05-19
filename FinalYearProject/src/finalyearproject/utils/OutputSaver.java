package finalyearproject.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

public class OutputSaver {

	private TextFileWriter textFileWriter;
	private ImageDownloader imageDownloader;

	public OutputSaver(String outputFileName) throws IOException {
		this.textFileWriter = new TextFileWriter(outputFileName);
		this.imageDownloader = new ImageDownloader(outputFileName);
	}

	public void saveOutputData(Map<String, String> outputData, boolean append) throws IOException {
		this.textFileWriter.saveOutputData(outputData, append);
	}
	
	public void saveOutputData(Map.Entry<String, String> outputData, boolean append) throws IOException {
		this.textFileWriter.saveOutputData(outputData, append);
	}
	
	public void writeTabKey(boolean append) throws IOException {
		this.textFileWriter.writeTabKey(append);
	}

	public void saveImage(String url) throws MalformedURLException, IOException {
		this.imageDownloader.saveImage(url);
	}
	
	public void saveImageFromScreenshot(File file, WebElement element) throws IOException {
		this.imageDownloader.saveImageFromScreenshot(file, element);
	}
	
	public void closeTextWriter() throws IOException {
		this.textFileWriter.close();
	}

	private class TextFileWriter {
		private BufferedWriter writer;
		private File file;

		public TextFileWriter(String outputFileName) throws IOException {
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

	private class ImageDownloader {

		private File file;
		int imageCounter = 1;

		public ImageDownloader(String outputFileName) {
			file = new File("data/output/" + outputFileName + "/" + imageCounter + ".png");
		}

		public void saveImage(String url) throws IOException {
			URL u = new URL(url);
			InputStream inputStream = new BufferedInputStream(u.openStream());
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];

			int n = 0;
			while (-1 != (n = inputStream.read(buffer))) {
				outputStream.write(buffer, 0, n);
			}

			outputStream.close();
			inputStream.close();

			byte[] response = outputStream.toByteArray();

			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(response);
			fileOutputStream.close();
			
			imageCounter++;
		}
		
		public void saveImageFromScreenshot(File image, WebElement element) throws IOException {
			Rectangle rectangle = new Rectangle(element.getSize().width, element.getSize().height, element.getSize().height, element.getSize().width);
			Point location = element.getLocation();
			
			BufferedImage bufferedImage = ImageIO.read(image);
			BufferedImage destination = bufferedImage.getSubimage(location.x, location.y, rectangle.width, rectangle.height);
			
			ImageIO.write(destination, "png", image);
			
			File file = this.file;
			FileUtils.copyFile(image, file);
			
			imageCounter++;
		}
		
	}
}
