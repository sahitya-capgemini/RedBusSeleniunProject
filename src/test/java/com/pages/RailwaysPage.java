package com.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RailwaysPage extends BasePage {
	TrainResultsPage trainResultsPage;
	String source, destination;

	@FindBy(xpath = "//div[contains(@class, 'inputAndSwapWrapper')]")
	WebElement from;
	@FindBy(css = "div[role='combobox'][data-field='dst']")
	WebElement to;

	By clickDate = By.xpath("//span[contains(text(), '2025')]");
	By monthCurr = By.xpath("//p[@aria-live='polite' and @aria-atomic='true']");
	By exactDate = By.xpath(
			"//ul[contains(@aria-label, 'calendar')]//li[contains(@class, 'dateItem') and contains(@class, 'date')]//span");
	By nextMonth = By.xpath("//i[@role='button' and contains(@aria-label, 'Next month')]");
	By fromLabel = By.xpath("//div[@role='combobox' and @aria-label='From']//div[contains(@class, 'label')]/div[2]");
	By toLabel = By.xpath("//div[@role='combobox' and @aria-label='To']//div[contains(@class, 'label')]//div[2]");

	public RailwaysPage(WebDriver driver) {
		super(driver);
	}

	public void clickOnFrom() {
		from.click();
	}

	public void searchFrom(String from) {
		waitAndGetVisibilityOfElement(By.cssSelector("input#srcDest")).sendKeys(from);

		this.source = from;
	}

	public void selectFrom(String from) {
		if (!from.isEmpty()) {
			String stationName = "All Stations";
			String xpath = String.format("//div[contains(normalize-space(text()), '%s')]", stationName);
			By stationBy = By.xpath(xpath);
			waitAndGetVisibilityOfElement(stationBy).click();
		} else {
			clickOnTo();
		}
	}

	public void clickOnTo() {
		to.click();
	}

	public void searchTo(String to) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='srcDest']")));
		WebElement toInput = driver.findElement(By.xpath("//input[@id='srcDest']"));
		toInput.sendKeys(to);
		this.destination = to;
	}

	public void selectTo(String to) {
		String stationNameTo = "All Stations";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		if (!to.isEmpty()) {
			String xpath = String.format("//div[contains((text()), '%s') and contains((text()), '%s')]", stationNameTo,
					to);
			By stationTo = By.xpath(xpath);
			wait.until(ExpectedConditions.elementToBeClickable(stationTo));
			WebElement option = driver.findElement(stationTo);
			option.click();
			//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

			System.out.println(waitAndGetVisibilityOfElement(stationTo).getText());
		}
		//		waitAndGetVisibilityOfElement(stationBy).click();
		else {

			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.ESCAPE).perform();
			waitAndGetVisibilityOfElement(clickDate);
		}
	}

	public void chooseDate(String month, String date) {
		int retryCount = 0;
		int maxRetries = 3;

		while (retryCount < maxRetries) {
			try {
				// Try to get the current month element
				WebElement mon = waitAndGetVisibilityOfElement(monthCurr);
				if (mon.getText().contains(month)) {
					break;
				}

				// Click next month
				waitAndGetVisibilityOfElement(nextMonth).click();

				// Optional delay to allow DOM update
				Thread.sleep(500);
			} catch (Exception e) {
				System.out.println("Month element not found, retrying...");

				// Click fallback element to refresh or trigger calendar
				try {
					WebElement fallback = driver.findElement(By.xpath("//span[contains(text(), '2025')]")); // Corrected
																											// XPath
					fallback.click();
					Thread.sleep(500);
				} catch (Exception fallbackEx) {
					System.out.println("Fallback click failed.");
				}
			}
			retryCount++;
		}

		// Proceed to select the date
		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(exactDate));

		List<WebElement> dates = driver.findElements(exactDate);
		for (WebElement x : dates) {
			try {
				if (x.getText().equals(date)) {
					x.click();
					break;
				}
			} catch (StaleElementReferenceException e) {
				System.out.println("Stale date element, retrying...");
				dates = driver.findElements(exactDate);
			}
		}
	}

	public TrainResultsPage clickOnSearch() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement searchBtn = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Search Trains')]")));
		searchBtn.click();
		this.trainResultsPage = new TrainResultsPage(driver);
		return trainResultsPage;

	}

}
