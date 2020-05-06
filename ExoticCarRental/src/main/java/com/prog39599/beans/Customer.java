package com.prog39599.beans;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Null;

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
@RequiredArgsConstructor
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
     @NonNull
	private String username;
	@NonNull
	private String encryptedPassword;
	@NonNull
	private String fName;
	@NonNull
	private String lName;
	@NonNull
	private String address;
	@NonNull
	private Long phoneNumber;
	@NonNull
	private Byte enabled;
	
//	@OneToMany
//	private List<RentalOrder> rentalOrderList;
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Roles> roles = new ArrayList<Roles>();
	
	
	
	
	
}
