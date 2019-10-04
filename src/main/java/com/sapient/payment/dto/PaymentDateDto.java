package com.sapient.payment.dto;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PaymentDateDto {
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull
	Date fromDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull
	Date toDate;
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	
}
