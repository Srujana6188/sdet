package cucumberTest;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
features = "Features",
glue = {"stepDefinitions"},
//tags = "@jobAlchemy",
//tags = "@OrangeHRM",
tags = "@SuiteCRM",
monochrome = true,
//plugin = {"html:reports2/jobAlchemy_Test_Report1.html"}
//plugin = {"html:reports2/OrangeHRM_Test_Report1.html"}
plugin = {"html:reports2/SuiteCRM_Test_Report1.html"}

)

public class ActivitiesRunner {
//empty
}