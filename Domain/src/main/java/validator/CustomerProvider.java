package validator;

import model.Customer;

import java.util.List;

public interface CustomerProvider {

    List<Customer> getAllCustomer();
}
