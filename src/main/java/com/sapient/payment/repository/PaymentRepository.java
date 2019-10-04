package com.sapient.payment.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapient.payment.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
	List<Payment> findByLastUpdateBetween(Date startDate, Date endDate);
	List<Payment> findTop10ByOrderByLastUpdateAsc();
}
