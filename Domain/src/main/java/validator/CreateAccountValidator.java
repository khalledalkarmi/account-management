package validator;

import model.Account;
import model.Customer;

import java.util.List;
import java.util.Objects;

public class CreateAccountValidator {


    private final List<Customer> customers;

    public CreateAccountValidator(CustomerProvider customerProvider) {
        this.customers = customerProvider.getAllCustomer();
    }

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
