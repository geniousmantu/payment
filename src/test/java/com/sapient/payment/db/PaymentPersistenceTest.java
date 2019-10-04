package com.sapient.payment.db;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.sapient.payment.PaymentApplication;
import com.sapient.payment.entity.Payment;
import com.sapient.payment.repository.PaymentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=PaymentApplication.class, webEnvironment =WebEnvironment.RANDOM_PORT)
public class PaymentPersistenceTest {

	
	@Autowired
	PaymentRepository paymentRepository;

	@Test
	public void findByLastUpdateBetweenTest() throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date sqlDatefrom = new java.sql.Date(df.parse("2013-01-01").getTime());
		java.sql.Date sqlDateTo = new java.sql.Date(df.parse("2018-01-01").getTime());
		
		List<Payment> payments = paymentRepository.findByLastUpdateBetween(sqlDatefrom, sqlDateTo);
	}
}
