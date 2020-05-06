package com.prog39599.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prog39599.beans.RentalOrder;

public interface RentalOrderRepository extends JpaRepository<RentalOrder, Long> {

}
