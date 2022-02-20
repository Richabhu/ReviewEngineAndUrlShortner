package com.spring.product.review.services;

import com.spring.product.review.models.Customer;
import com.spring.product.review.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer create(Customer customer)
    {
        Customer res =  customerRepository.save(customer);
        return res;
    }
}
