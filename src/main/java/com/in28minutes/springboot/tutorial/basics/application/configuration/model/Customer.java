package com.in28minutes.springboot.tutorial.basics.application.configuration.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "\"Customer\"")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"CustomerId\"")
    private Long customerId;

    @Column(name = "\"FirstName\"", nullable = false)
    private String firstName;

    @Column(name = "\"LastName\"", nullable = false)
    private String lastName;

    @Column(name = "\"Company\"")
    private String company;

    @Column(name = "\"Address\"")
    private String address;

    @Column(name = "\"City\"")
    private String city;

    @Column(name = "\"State\"")
    private String state;

    @Column(name = "\"Country\"")
    private String country;

    @Column(name = "\"PostalCode\"")
    private String postalCode;

    @Column(name = "\"Phone\"")
    private String phone;

    @Column(name = "\"Fax\"")
    private String fax;

    @Column(name = "\"Email\"", nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "\"SupportRepId\"", referencedColumnName = "\"EmployeeId\"")
    private Employee supportRep;

    public Customer() {
        super();
    }

    public Customer(String firstName, String lastName, String company, String address, String city,
            String state, String country, String postalCode, String phone, String fax,
            String email, Employee supportRep) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.supportRep = supportRep;
    }

    // Getters et Setters

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee getSupportRep() {
        return supportRep;
    }

    public void setSupportRep(Employee supportRep) {
        this.supportRep = supportRep;
    }

    @Override
    public String toString() {
        return "Customer [CustomerId=" + customerId + ", FirstName=" + firstName + ", LastName=" + lastName +
                ", Company=" + company + ", Address=" + address + ", City=" + city + ", State=" + state +
                ", Country=" + country + ", PostalCode=" + postalCode + ", Phone=" + phone + ", Fax=" + fax +
                ", Email=" + email + ", SupportRep=" + (supportRep != null ? supportRep.getEmployeeId() : "NULL") + "]";
    }
}
