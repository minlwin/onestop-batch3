package com.jdc.balance.model.repo;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.jdc.balance.model.dto.LedgerType;
import com.jdc.balance.model.entity.BalanceItem;

public interface BalanceItemRepo extends JpaRepositoryImplementation<BalanceItem, Long>{
	
	@Query("""
		select sum(b.unitPrice * b.quentity) from BalanceItem b 
		where b.balance.owner.id = ?1 and b.balance.ledger.type = ?2 and 
		b.balance.useDate < ?3""")
	Optional<Long> searchBalaceTotal(int accountId, LedgerType type, LocalDate dateFrom);
}
