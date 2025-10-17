
package com.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features", // path to your feature files
		glue = { "com.stepdefinition", // scenario-specific steps
				"com.setup", // shared BaseSteps
				"com.hooks" // global Hooks
		}, plugin = { "pretty:target/cucumber-reports/pretty.txt", "json:target/cucumber-reports/cucumber.json",
				"junit:target/cucumber-reports/cucumber.xml", "usage:target/cucumber-reports/usage.json",
				"html:target/cucumber-reports/cucumber-html-report.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, monochrome = true)
public class TestRunnerJunit {

}