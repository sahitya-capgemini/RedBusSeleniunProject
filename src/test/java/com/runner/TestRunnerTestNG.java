
package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "classpath:Features", glue = { "com.stepdefinition", // scenario-specific																								// steps
		"com.setup", // shared BaseSteps
		"com.hooks", // global Hooks
}, plugin = { "pretty:target/cucumber-reports/pretty.txt", "json:target/cucumber-reports/cucumber.json",
		"junit:target/cucumber-reports/cucumber.xml", "usage:target/cucumber-reports/usage.json",
		"html:target/cucumber-reports/cucumber-html-report.html",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, monochrome = true)

public class TestRunnerTestNG extends AbstractTestNGCucumberTests {
	//	@BeforeSuite
	//	public void setupExtent() {
	//		System.setProperty("basefolder.name", "Report/Redbus-TestNG");
	//		System.setProperty("extent.reporter.spark.out", "TestNGReport.html");
	//
	//	}
}