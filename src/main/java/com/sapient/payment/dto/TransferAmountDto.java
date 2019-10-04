package com.sapient.payment.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class TransferAmountDto {

	@NotNull
	int fromAcc;
	@NotNull
	int toAcc;
	@NotNull
	BigDecimal amount;

	public int getFromAcc() {
		return fromAcc;
	}

	public void setFromAcc(int fromAcc) {
		this.fromAcc = fromAcc;
	}

	public int getToAcc() {
		return toAcc;
	}

	public void setToAcc(int toAcc) {
		this.toAcc = toAcc;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
