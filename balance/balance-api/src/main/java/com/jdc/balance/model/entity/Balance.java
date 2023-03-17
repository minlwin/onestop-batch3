package com.jdc.balance.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.Data;

@Data
@Entity
@Table(name = "BALANCE")
public class Balance implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "BALANCE_SEQ")
	@TableGenerator(name = "BALANCE_SEQ")
	private long id;
	
	@ManyToOne(optional = false)
	private Ledger ledger;
	
	@Column(name = "use_date", nullable = false)
	private LocalDate useDate;
	
	private String remark;
	
	@CreatedBy
	@ManyToOne(optional = false)
	private Account owner;
	
	@OneToMany(mappedBy = "balance")
	private List<BalanceItem> items;
}
