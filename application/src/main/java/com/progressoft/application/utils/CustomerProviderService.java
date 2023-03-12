package com.progressoft.application.utils;

import model.Customer;
import org.springframework.stereotype.Service;
import validator.CustomerProvider;

import java.util.List;

@Service
public class CustomerProviderService implements CustomerProvider {

    @Override
    public List<Customer> getAllCustomer() {
        return List.of(new Customer("KHALEDKAR"), new Customer("YOUSEFSUL"), new Customer("TAYSEERSAB"));
    }
}
