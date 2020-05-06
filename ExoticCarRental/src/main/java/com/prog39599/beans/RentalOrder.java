package com.prog39599.beans;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@RequiredArgsConstructor
@Entity
public class RentalOrder {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Date startDate;

	private Date endDate;
	
	
	@OneToOne

	//@JoinTable(name="RENTAL_CAR", joinColumns = @JoinColumn(name="RENTAL_ID"), inverseJoinColumns=@JoinColumn(name="CAR_ID"))	
	@NonNull
	private Car car;
	
	@OneToOne
	@NonNull
	private Customer customer;
	
	
}
