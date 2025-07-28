package com.oracle.service;

import org.springframework.stereotype.Service;

import com.oracle.model.Customer;

@Service
public interface CustomerService {
	Customer registerCustomer(Customer customer);
	Customer loginCustomer(String email, String password);
}
