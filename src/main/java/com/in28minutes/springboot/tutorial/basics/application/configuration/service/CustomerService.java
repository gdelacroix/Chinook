package com.in28minutes.springboot.tutorial.basics.application.configuration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Customer;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Employee;
import com.in28minutes.springboot.tutorial.basics.application.configuration.repository.CustomerRepository;
import com.in28minutes.springboot.tutorial.basics.application.configuration.repository.EmployeeRepository;;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void addCustomer(String firstName, String lastName, String company, String address, String city,
            String state, String country, String postalCode, String phone, String fax,
            String email, Employee supportRep) {

        customerRepository.save(new Customer(firstName, lastName, company, address, city, state, country,
                postalCode, phone, fax, email, supportRep));
    }

    @Override
    public void deleteCustomer(long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
        }
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
}
