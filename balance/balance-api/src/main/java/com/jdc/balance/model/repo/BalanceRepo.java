package com.jdc.balance.model.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.jdc.balance.model.entity.Balance;

public interface BalanceRepo extends JpaRepositoryImplementation<Balance, Long>{

}
