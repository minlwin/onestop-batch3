package com.jdc.balance.model.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.jdc.balance.model.entity.Ledger;

public interface LedgerRepo extends JpaRepositoryImplementation<Ledger, Integer>{

}
