package com.jdc.balance.model.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.jdc.balance.model.entity.BalanceItem;

public interface BalanceItemRepo extends JpaRepositoryImplementation<BalanceItem, Long>{

}
