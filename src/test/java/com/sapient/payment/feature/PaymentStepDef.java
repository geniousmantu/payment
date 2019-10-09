package com.sapient.payment.feature;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

import com.sapient.payment.dto.PaymentDateDto;
import com.sapient.payment.entity.Payment;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PaymentStepDef{
	
	private final String SERVER_URL = "http://localhost:8081";
	private RestTemplate restTemplate;
	private List<Payment> payments;
	private String PAYMENT_URL;
	
	ResponseEntity<List<Payment>> response;

	public PaymentStepDef() {
		this.restTemplate = new RestTemplate();
		this.payments = new ArrayList<>();
	}
	
	
	@Given("As a bank end user i want to see the top ten Transaction")
	public void as_a_bank_end_user_i_want_to_see_the_top_ten_Transaction() {
		String PAYMENT_ENDPOINT = "/api/v1/top10Payments";
		PAYMENT_URL = SERVER_URL.concat(PAYMENT_ENDPOINT);
	}

	@When("I requested to see top ten transaction")
	public void i_requested_to_see_top_ten_transaction() {
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("admin", "admin"));
		ParameterizedTypeReference<List<Payment>> myBean = new ParameterizedTypeReference<List<Payment>>() {};
		response = restTemplate.exchange(PAYMENT_URL, HttpMethod.GET, null, myBean);
		assertThat(response.getBody()).isNotEmpty();
	}

	@Then("I should be able to see the list of top ten transaction")
	public void i_should_be_able_to_see_the_list_of_top_ten_transaction() {
		payments = response.getBody();
		assertThat(payments.size()).isGreaterThanOrEqualTo(10);
	}

}
