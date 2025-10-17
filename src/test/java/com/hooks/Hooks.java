package com.hooks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.parameters.ConfigReader;
import com.parameters.ScreenshotUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {

	public static WebDriver driver;

	@Before
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		//		WebDriverManager.edgedriver().setup();
		//		driver = new EdgeDriver();
		driver.manage().window().maximize();
		String baseUrl = ConfigReader.getProperty("base.url");
		if (baseUrl != null) {
			driver.get(baseUrl);
		} else {
			throw new RuntimeException("Base URL is not defined in config.properties");
		}
	}

	@After
	public void tearDown() throws InterruptedException {
		// capture screenshot
		ScreenshotUtil.captureScreenshot(driver, "TestResult");
		Thread.sleep(2000); // close the browser
		if (driver != null) {
			driver.quit();
		}
	}
}