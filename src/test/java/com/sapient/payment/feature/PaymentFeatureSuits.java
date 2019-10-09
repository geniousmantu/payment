package com.sapient.payment.feature;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber",
		"junit:target/junit-report.xml" }, features = "src/test/resources/feature/payment.feature")
public class PaymentFeatureSuits {

}
