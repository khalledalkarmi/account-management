package validator;

import model.Account;
import model.Customer;

import java.util.List;
import java.util.Objects;

public class CreateAccountValidator {

    //TODO: read from csv file
    private static List<Customer> customers = List.of(new Customer("KHALEDKAR"), new Customer("YOUSEFSUL"), new Customer("TAYSEERSAB"));

    public static boolean validate(Account account) {
        if (Objects.isNull(account))
            throw new NullPointerException("Invalid Account, account is null");

        if (Objects.isNull(account.getId()))
            throw new NullPointerException("Invalid Account id, account id is null");
        for (Customer customer : customers) {
            if (customer.getName().equals(account.getId()))
                return true;
        }
        return false;
    }
}
