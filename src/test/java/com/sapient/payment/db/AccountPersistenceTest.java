package com.sapient.payment.db;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.math.BigDecimal;
import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.sapient.payment.PaymentApplication;
import com.sapient.payment.entity.Account;
import com.sapient.payment.repository.AccountRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=PaymentApplication.class, webEnvironment =WebEnvironment.RANDOM_PORT)
public class AccountPersistenceTest {

	@Autowired
	AccountRepository accountRepository;
	
	@Test
	public void findByAccountNoTest() throws ParseException {
		Account account = accountRepository.findByAccountNo(12);
		assertThat(account).extracting("accountNo","name").contains(12, new String("account name 1"));
	}
}
