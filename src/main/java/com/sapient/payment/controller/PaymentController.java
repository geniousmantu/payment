package com.sapient.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.payment.dto.PaymentDateDto;
import com.sapient.payment.dto.TransferAmountDto;
import com.sapient.payment.entity.Payment;
import com.sapient.payment.service.PaymentService;

@RestController
@RequestMapping("api/v1")
public class PaymentController {
	
	@Autowired
	PaymentService paymentservice;

	@RequestMapping(value="top10Payments", method=RequestMethod.GET)
	public List<Payment> top10Payments(){
		return paymentservice.getLastTenPayments();
	}
	
	@RequestMapping(value = "transferMoney", method=RequestMethod.POST)
	public String transferMoney(@RequestBody TransferAmountDto transferAmountDto){
		return paymentservice.transferAmount(transferAmountDto.getFromAcc(), transferAmountDto.getToAcc(), transferAmountDto.getAmount());
	}
	
	@RequestMapping(value = "getPaymentsByDate", method=RequestMethod.GET)
	public List<Payment> getPaymentsByDate(PaymentDateDto paymentDateDto){
		return paymentservice.getPaymentsBetween(paymentDateDto.getFromDate(), paymentDateDto.getToDate());
	}
	
}
