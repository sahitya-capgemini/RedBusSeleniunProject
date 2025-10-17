package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Visibility
	public void waitForVisibilityOfElement(WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForVisibilityOfElementBy(By locator) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForVisibilityOfAllElementsBy(By locator) {
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	// Invisibility
	public void waitForInVisibilityOfElement(WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOf(element));
	}

	public void waitForInVisibilityOfElementBy(By locator) {
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	// Presence
	public void waitForPresenceOfElement(By locator) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitForPresenceOfAllElements(By locator) {
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	// Clickable
	public void waitForElementToBeClickable(WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementToBeClickableBy(By locator) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(locator));
	}

	// Text
	public void waitForTextToBePresentInElement(WebElement element, String text) {
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	// Attribute
	public void waitForAttributeToContain(WebElement element, String attribute, String value) {
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.attributeContains(element, attribute, value));
	}

	// Frame
	public void waitForFrameAndSwitchToIt(By locator) {
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public WebElement waitAndGetPresenceOfElement(By locator) {
		return new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitAndGetVisibilityOfElement(By locator) {
		return new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitAndGetElementToBeClickable(By locator) {
		return new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(locator));
	}

}
