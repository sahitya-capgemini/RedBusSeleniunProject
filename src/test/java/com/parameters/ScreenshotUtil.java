package com.parameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

	public static void captureScreenshot(WebDriver driver, String namePrefix) {
		if (driver instanceof TakesScreenshot) {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			File destination = new File("Screenshots/" + namePrefix + "_" + timestamp + ".png");

			// Ensure directory exists
			destination.getParentFile().mkdirs();

			try {
				Files.copy(screenshot.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Screenshot saved to: " + destination.getAbsolutePath());
			} catch (IOException e) {
				System.err.println("Failed to save screenshot: " + e.getMessage());
			}
		}
	}

}