package com.jdc.balance.model.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.jdc.balance.model.dto.Role;
import com.jdc.balance.model.entity.Account;

public interface AccountRepo extends JpaRepositoryImplementation<Account, Integer>{

	Optional<Account> findOneByLoginId(String loginId);

	Long countByRole(Role admin);

}
