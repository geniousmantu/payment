package com.sapient.payment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.math.BigDecimal;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaymentApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class PaymentServiceImplIntegrationTest {

	@Autowired
	PaymentServiceImpl paymentServiceImp;

	@Test
	public void getLast10PaymentsTest() {
		List<Payment> payments = paymentServiceImp.getLastTenPayments();
		assertThat(payments).extracting("fromAccountNo", "toAccountNo", "amount")
				.contains(tuple(21, 12, new BigDecimal(5)));

	}

	@Test
	public void getPaymentsBetweenTest() throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Payment> payments = paymentServiceImp.getPaymentsBetween(
				new java.sql.Date(simpleDateFormat.parse("2013-09-04").getTime()),
				new java.sql.Date(simpleDateFormat.parse("2018-09-04").getTime()));
		assertThat(payments).extracting("fromAccountNo", "toAccountNo", "amount")
				.contains(tuple(21, 12, new BigDecimal(3)));

	}

	@Test
	public void transferAmount() {
		String responseMsg = paymentServiceImp.transferAmount(21, 12, new BigDecimal(4));
		assertThat(responseMsg).isEqualTo("Transfer SUCCESS");

	}

	@Test
	public void transferAmountWithNoExistingFromAccNo() {
		String responseMsg = paymentServiceImp.transferAmount(219, 12, new BigDecimal(4));
		assertThat(responseMsg).isEqualTo("Transfer Failure : Account no not found");

	}

	@Test
	public void transferAmountWithNoExistingToAccNo() {
		String responseMsg = paymentServiceImp.transferAmount(21, 128, new BigDecimal(4));
		assertThat(responseMsg).isEqualTo("Transfer Failure : Account no not found");

	}

	@Test
	public void transferAmountWithFromAccNoWithAmountZero() {
		String responseMsg = paymentServiceImp.transferAmount(21, 12, null);
		assertThat(responseMsg).isEqualTo("Transfer Failure");

	}

	@Test
	public void transferAmountWithToAccNoAmountZero() {
		String responseMsg = paymentServiceImp.transferAmount(21, 128, null);
		assertThat(responseMsg).isEqualTo("Transfer Failure : Account no not found");

	}

	@Test
	public void transferAmountWithAllNull() {
		String responseMsg = paymentServiceImp.transferAmount(21, 128, null);
		assertThat(responseMsg).isEqualTo("Transfer Failure : Account no not found");

	}
	
	@Test
	public void transferAmountWithNotEnoghBalance() {
		String responseMsg = paymentServiceImp.transferAmount(21, 12, new BigDecimal(100));
		assertThat(responseMsg).isEqualTo("Transfer Failure : Not enogh balance in From Account");

	}
}
