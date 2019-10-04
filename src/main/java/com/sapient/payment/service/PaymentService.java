package com.sapient.payment.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.sapient.payment.entity.Payment;

public interface PaymentService {
	String transferAmount(int fromAccount, int toAccount, BigDecimal amount);
	List<Payment> getLastTenPayments();
	List<Payment> getPaymentsBetween(Date fromDate, Date toDate);
}
