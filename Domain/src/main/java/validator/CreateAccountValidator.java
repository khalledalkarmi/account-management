package validator;

import model.Account;
import model.Customer;

import java.util.List;
import java.util.Objects;

public class CreateAccountValidator {

    //TODO Add dependency on CustomerProvider and dont call it in customer
    //TODO Use Streams for filtering/finding the customer by ID

    private final List<Customer> customers;

    public CreateAccountValidator(CustomerProvider customerProvider) {
        this.customers = customerProvider.getAllCustomer();
    }

    //Return Set<Violation> instead of boolean and handle it in the CreateAccountUseCase

    public boolean validate(Account account) {
        if (Objects.isNull(account))
            throw new NullPointerException("Invalid Account, account is null");

        if (Objects.isNull(account.getCustomerId()))
            throw new NullPointerException("Invalid Account id, account id is null");
        for (Customer customer : customers) {
            if (customer.getName().equals(account.getCustomerId()))
                return true;
        }
        return false;
    }
}
