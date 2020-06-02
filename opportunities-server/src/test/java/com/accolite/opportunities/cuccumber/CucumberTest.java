package com.accolite.opportunities.cuccumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.accolite.opportunities.Application;

/**
 * To run cucumber test
 */
@RunWith(Cucumber.class)
@SpringBootTest(classes = { Application.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberOptions(features = { "classpath:features", "src/test/resources/create-user.feature" }, 
					plugin = { 
							//"usage"
							"pretty"
							//, "json:target/cucumber-report.json"
							, "html:target/cucumber-reports" 
							}, 
					monochrome = true)
public class CucumberTest {

}
