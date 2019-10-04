package com.sapient.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.payment.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	Account findByAccountNo(int accountNo);
}
