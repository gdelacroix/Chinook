package com.in28minutes.springboot.tutorial.basics.application.configuration.repository;

import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
