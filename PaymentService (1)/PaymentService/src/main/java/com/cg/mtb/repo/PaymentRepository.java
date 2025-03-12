package com.cg.mtb.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.mtb.entity.PaymentEntity;


public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer>{
	 List<PaymentEntity> findByUserId(int userId);
}
