package com.sapient.payment.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.payment.entity.Account;
import com.sapient.payment.entity.Payment;
import com.sapient.payment.repository.AccountRepository;
import com.sapient.payment.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private AccountRepository accountRepository;

	ModelMapper modelMapper = new ModelMapper();

	@Override
	@Transactional
	public String transferAmount(int fromAccount, int toAccount, BigDecimal amount) {
		String transferMsg = null;
		try {
			Account fromAcount = accountRepository.findByAccountNo(fromAccount);
			Account toAcount = accountRepository.findByAccountNo(toAccount);
			if ((fromAcount != null && fromAcount.getBalance() != null)
					&& (toAcount != null && toAcount.getBalance() != null)) {
				if(fromAcount.getBalance().compareTo(amount) >= 0) {
					fromAcount.setBalance(fromAcount.getBalance().subtract(amount));
					accountRepository.save(fromAcount);
					toAcount.setBalance(toAcount.getBalance().add(amount));
					accountRepository.save(toAcount);
	
					Payment payment = new Payment();
					payment.setAmount(amount);
					payment.setFromAccountNo(fromAccount);
					payment.setToAccountNo(toAccount);
					payment.setLastUpdate(new java.util.Date());
	
					paymentRepository.saveAndFlush(payment);
					transferMsg = "Transfer SUCCESS";
					log.info("{} from {} to {}", transferMsg, fromAccount, toAccount);
				}
				else {
					transferMsg = "Transfer Failure : Not enogh balance in From Account";
					log.error("{} from {} to {}", transferMsg, fromAccount, toAccount);
				}
			}
			else {
				transferMsg = "Transfer Failure : Account no not found";
				log.error("{} from {} to {}", transferMsg, fromAccount, toAccount);
			}

		} catch (Exception e) {
			transferMsg = "Transfer Failure";
			log.error("{} from {} to {}", transferMsg, fromAccount, toAccount,e.getMessage());
		}
		return transferMsg;

	}

	@Override
	public List<Payment> getLastTenPayments() {
		log.info("PaymentService  >> getLastTenPayments");
		return paymentRepository.findTop10ByOrderByLastUpdateAsc();
	}

	@Override
	public List<Payment> getPaymentsBetween(Date fromDate, Date toDate) {
		return paymentRepository.findByLastUpdateBetween(fromDate, toDate);
	}

}
