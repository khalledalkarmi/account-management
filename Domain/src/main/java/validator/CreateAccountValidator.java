package validator;

import lombok.AllArgsConstructor;
import model.Account;
import model.Customer;

import java.util.List;
import java.util.Objects;
@AllArgsConstructor
public class CreateAccountValidator {

    //TODO Add dependency on CustomerProvider and dont call it in customer
    //TODO Use Streams for filtering/finding the customer by ID

    private final CustomerProvider customerProvider;

    //Return Set<Violation> instead of boolean and handle it in the CreateAccountUseCase

    public boolean validate(Account account) {
        List<Customer> customers = customerProvider.getAllCustomer();
        if (Objects.isNull(account))
            throw new NullPointerException("Invalid Account, account is null");

        if (Objects.isNull(account.getCustomerId()))
            throw new NullPointerException("Invalid Account id, account id is null");

        return customers.stream().anyMatch(customer -> customer.getName().equals(account.getCustomerId()));
    }
}
