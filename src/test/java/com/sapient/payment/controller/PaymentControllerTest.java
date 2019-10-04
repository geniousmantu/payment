package com.sapient.payment.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.payment.dto.PaymentDateDto;
import com.sapient.payment.dto.TransferAmountDto;
import com.sapient.payment.entity.Payment;
import com.sapient.payment.service.PaymentService;

@RunWith(SpringRunner.class)
@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

	@Autowired
	private MockMvc mvc;
	@MockBean
	private PaymentService paymentService;

	@Test
	public void getLastTenPaymentsUnitTest() throws Exception {
		given(this.paymentService.getLastTenPayments()).willReturn(new ArrayList<Payment>());
		this.mvc.perform(get("/api/v1/top10Payments").with(user("admin").roles("USER", "ADMIN"))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void transferMoneyTest() throws Exception {
		given(this.paymentService.transferAmount(12, 21, new BigDecimal(5))).willReturn(new String());
		TransferAmountDto transferAmountDto = new TransferAmountDto();
		transferAmountDto.setFromAcc(12);
		transferAmountDto.setToAcc(21);
		transferAmountDto.setAmount(new BigDecimal(5));
		this.mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/transferMoney")
				.with(user("admin").roles("USER", "ADMIN"))
				.content(new ObjectMapper().writeValueAsString(transferAmountDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getPaymentsByDateTest() throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		PaymentDateDto paymentDateDto = new PaymentDateDto();
		paymentDateDto.setFromDate(new java.sql.Date(simpleDateFormat.parse("2013-09-04").getTime()));
		paymentDateDto.setToDate(new java.sql.Date(simpleDateFormat.parse("2018-09-04").getTime()));
		given(this.paymentService.getPaymentsBetween(new java.sql.Date(simpleDateFormat.parse("2013-09-04").getTime()),
				new java.sql.Date(simpleDateFormat.parse("2018-09-04").getTime())))
						.willReturn(new ArrayList<Payment>());
		this.mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/getPaymentsByDate")
				.with(user("admin").roles("USER", "ADMIN"))
				.content(new ObjectMapper().writeValueAsString(paymentDateDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}
