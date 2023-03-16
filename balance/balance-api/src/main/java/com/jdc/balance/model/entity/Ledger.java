package com.jdc.balance.model.entity;

import java.io.Serializable;

import com.jdc.balance.model.dto.LedgerType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.Data;

@Data
@Entity
@Table(name = "LEDGER")
public class Ledger implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "LEDGER_SEQ")
	@TableGenerator(name = "LEDGER_SEQ")
	private int id;
	
	@Column(nullable = false)
	private LedgerType type;
	@Column(nullable = false)
	private String name;
	
	@ManyToOne(optional = false)
	private Account owner;
}
