package com.jdc.location.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Division implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Please enter name.")
	@Column(nullable = false, unique = true)
	private String name;
	
	@NotBlank(message = "Please enter burmese name.")
	@Column(nullable = false)
	private String burmese;
	
	@NotNull(message = "Please select division type.")
	@ManyToOne(optional = false)
	private DivisionType type;
	
	@NotBlank(message = "Please enter capital name.")
	@Column(nullable = false)
	private String capital;
}
