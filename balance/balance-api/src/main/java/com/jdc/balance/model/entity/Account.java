package com.jdc.balance.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.jdc.balance.model.dto.AccountStatus;
import com.jdc.balance.model.dto.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.Data;

@Data
@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "ACCOUNT_SEQ")
	@TableGenerator(name = "ACCOUNT_SEQ")
	private int id;
	
	@Column(nullable = false)
	private String name;
	@Column(name = "login_id", nullable = false, unique = true)
	private String loginId;
	@Column(nullable = false)
	private Role role;
	@Column(nullable = false)
	private AccountStatus status;
	@Column(nullable = false, name = "regist_date")
	private LocalDate registDate;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String phone;

}
