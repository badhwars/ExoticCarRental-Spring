package com.prog39599.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prog39599.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findByUsername(String username);
	
	

}
