package com.in28minutes.springboot.tutorial.basics.application.configuration.service;

import java.util.List;
import java.util.Optional;

import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Customer;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Employee;

public interface ICustomerService {

    List<Customer> getCustomers();

    Optional<Customer> getCustomerById(long id);

    void updateCustomer(Customer customer);

    void addCustomer(String firstName, String lastName, String company, String address, String city,
            String state, String country, String postalCode, String phone, String fax,
            String email, Employee supportRep);

    void deleteCustomer(long id);

    void saveCustomer(Customer customer);

    List<Employee> getEmployees(); // Ajout pour récupérer la liste des employés
}
