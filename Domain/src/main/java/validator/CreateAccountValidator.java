package validator;

import exception.CustomerNotFoundException;
import lombok.AllArgsConstructor;
import model.Account;
import model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class CreateAccountValidator {

    //TODO Add dependency on CustomerProvider and dont call it in customer
    //TODO Use Streams for filtering/finding the customer by ID

    private final CustomerProvider customerProvider;

    //Return Set<Violation> instead of boolean and handle it in the CreateAccountUseCase

    public List<Violation> validate(Account account) {
        List<Violation> violations = new ArrayList<>();
        List<Customer> customers = customerProvider.getAllCustomer();

        if (Objects.isNull(account))
            violations.add(new Violation(new NullPointerException("Invalid Account, account is null")));
        if (Objects.isNull(account.getCustomerId()))
            violations.add(new Violation(new NullPointerException("Invalid Account id, account id is null")));

        boolean anyMatch = customers.stream().anyMatch(customer -> customer.getName().equals(account.getCustomerId()));

        if (!anyMatch)
            violations.add(new Violation(new CustomerNotFoundException("Customer not found")));

        return violations;

    }
}
