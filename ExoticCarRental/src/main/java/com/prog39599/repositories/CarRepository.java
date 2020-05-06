package com.prog39599.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prog39599.beans.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

	public List<Car> findByMakeLike(String make);

	

}
