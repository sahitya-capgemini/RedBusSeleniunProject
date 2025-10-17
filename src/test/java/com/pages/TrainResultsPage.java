package com.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TrainResultsPage extends BasePage {
	PassengerInfoPage passengerInfoPage;

	public TrainResultsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "/html/body/div/div[2]/div/div[1]/div/div[2]/div[2]")
	WebElement availability;
	@FindBy(xpath = "//*[@id=\"search-main-content\"]/div/div[1]/div/div[2]/div[4]")
	WebElement ticketQuota;

	By sortByDepartureButton = By.xpath("//button[text()='Departure']");
	By secondAC = By.xpath("//div[@role='checkbox' and contains(@aria-label, 'AC 2 Tier (2A)')]");
	By thirdAC = By.xpath("//div[@role='checkbox' and contains(@aria-label, 'AC 3 Tier (3A)')]");
	By availabilityStatusSecondAC = By.xpath(
			"//div[@role='button' and contains(@aria-label, 'Class: 2A') and contains(@aria-label, 'Availability:Available')]");
	By departureSixAMto12PM = By.xpath("//div[@role='checkbox' and .//div[text()='Morning']]//label");
	By ticketClass = By.xpath("//div[@role='button' and .//div[text()='Ticket class']]");
	By clearFiltersButton = By.cssSelector("button[aria-label='Clear all filters']");
	By quotaFilter = By.xpath("//div[@role='button' and .//div[text()='Quota']]");
	By ladiesQuotaRadio = By.xpath("//div[contains(@aria-label, 'Ladies quota (LD)')]");
	By firstAvailableSeat = By
			.xpath("//div[contains(@class, 'srpCard') and contains(@aria-label, 'Availability:Available')]");

	public void clickOnAvailability() {
		availability.click();
	}

	public void clickOnTicketClass() throws InterruptedException {
		Thread.sleep(1000);
		driver.navigate().refresh();
		Thread.sleep(1000);
		waitAndGetVisibilityOfElement(ticketClass).click();
	}

	public void clickOnTicketQuota() {
		ticketQuota.click();
	}

	public void selectClass() {
		waitAndGetVisibilityOfElement(secondAC).click();
	}

	public void selectThirdAC() {
		waitAndGetVisibilityOfElement(thirdAC).click();
	}

	public void selectDepartureTime() {
		waitAndGetVisibilityOfElement(departureSixAMto12PM).click();
	}

	public String checkAvailabilityStatus() {

		try {
			return waitAndGetVisibilityOfElement(availabilityStatusSecondAC).getAttribute("aria-label");
		} catch (NoSuchElementException e) {
			// Element not found, return null
			return null;
		} catch (Exception e) {
			// Element not found, return null
			return null;
		}

	}

	public void clearAllFilters() {

		waitAndGetVisibilityOfElement(clearFiltersButton).click();
		driver.navigate().refresh();
	}

	public void clickOnQuotaFilter() {
		waitAndGetVisibilityOfElement(quotaFilter).click();
		;
	}

	public void selectLadiesQuota() {
		waitAndGetVisibilityOfElement(ladiesQuotaRadio).click();

	}

	public void sortByDepartureTime() {
		waitAndGetVisibilityOfElement(sortByDepartureButton).click();
	}

	public String getStateDepartureSort() throws Exception {

		WebElement sortIcon = driver.findElement(By.xpath("//button[contains(@aria-label, 'Departure')]/img"));
		String altText = sortIcon.getAttribute("alt");
		return altText;

	}

	public PassengerInfoPage checkAndClickOnAvailabilitystatus() throws Exception {

		waitAndGetVisibilityOfElement(firstAvailableSeat).click();
		passengerInfoPage = new PassengerInfoPage(driver);
		return passengerInfoPage;
	}

}
