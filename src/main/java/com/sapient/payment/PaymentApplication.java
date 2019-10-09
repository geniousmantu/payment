package com.sapient.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class PaymentApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.out.println("*********** PaymentApplication from Tomcat******");
		SpringApplication.run(PaymentApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PaymentApplication.class);
    }
}
