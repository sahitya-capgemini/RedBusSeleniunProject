package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	RailwaysPage railwaysPage;

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "img[title='Online Train Tickets Booking']")
	WebElement railwaysButton;

	public RailwaysPage clickOnRailwaysPage() {
		railwaysButton.click();
		railwaysPage = new RailwaysPage(this.driver);
		return this.railwaysPage;
	}

}
