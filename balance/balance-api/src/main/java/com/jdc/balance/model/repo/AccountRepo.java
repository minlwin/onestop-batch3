package com.jdc.balance.model.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.jdc.balance.model.entity.Account;

public interface AccountRepo extends JpaRepositoryImplementation<Account, Integer>{

}
