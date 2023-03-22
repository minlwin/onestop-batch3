package com.jdc.balance.model.entity;

import java.io.Serializable;

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
@Table(name = "BALANCE_ITEM")
public class BalanceItem implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "BALANCE_ITEM_SEQ")
	@TableGenerator(name = "BALANCE_ITEM_SEQ", allocationSize = 1, initialValue = 0)
	private long id;
	
	@ManyToOne(optional = false)
	private Balance balance;
	
	@Column(nullable = false)
	private String reason;
	@Column(nullable = false, name = "unit_price")
	private int unitPrice;
	@Column(nullable = false)
	private int quentity;
	
	public void setBalance(Balance b) {
		b.getItems().add(this);
		this.balance = b;
	}
}
