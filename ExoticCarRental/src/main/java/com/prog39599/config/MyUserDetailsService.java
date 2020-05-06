package com.prog39599.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prog39599.beans.Roles;
import com.prog39599.repositories.CustomerRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		com.prog39599.beans.Customer customer = customerRepository.findByUsername(username);
		if (customer == null) {
			System.out.println("Customer not found:" + username);
			throw new UsernameNotFoundException("Customer " + username + " was not found in the database");
		}
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		for (Roles role : customer.getRoles()) {
			grantList.add(new SimpleGrantedAuthority(role.getRolename()));
		}
		UserDetails userDetails = (UserDetails) new User(customer.getUsername(), customer.getEncryptedPassword(), grantList);
		return userDetails;
	} 

}
