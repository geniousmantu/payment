package com.sapient.payment.feature;

import java.util.List;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.sapient.payment.entity.Payment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Ignore
public class SpringCucumberIntegrationTests {
	private final String SERVER_URL = "http://localhost";
	private final String THINGS_ENDPOINT = "/api/v1";
	private RestTemplate restTemplate;
	@LocalServerPort
	protected int port;

	public SpringCucumberIntegrationTests() {
		this.restTemplate = new RestTemplate();
	}

	private String thingsEndpoint() {
		return SERVER_URL + ":" + port + THINGS_ENDPOINT;
	}

	int put(String something) {
		return restTemplate.postForEntity(thingsEndpoint(), something, Void.class).getStatusCodeValue();
	}

	List<Payment> getContents() {
		return (List<Payment>) restTemplate.getForEntity(thingsEndpoint(), Payment.class).getBody();
	}

	void clean() {
		restTemplate.delete(thingsEndpoint());
	}
}