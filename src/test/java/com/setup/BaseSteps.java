package com.setup;

import com.hooks.Hooks;
import com.pages.HomePage;
import com.pages.RailwaysPage;
import com.parameters.Context;

import io.cucumber.java.en.Given;

public class BaseSteps {

	@Given("the user is on the RedBus Railways page")
	public void the_user_is_on_the_red_bus_railways_page() {
		// Create HomePage
		HomePage homePage = new HomePage(Hooks.driver);
		// Go to the railways by clicking on it and create railways page
		RailwaysPage railwaysPage = homePage.clickOnRailwaysPage();
		Context.setRailwaysPage(railwaysPage);
	}
}