package validator;

import model.Account;
import model.Customer;

import java.util.List;

public class CreateAccountValidator {

    //TODO: read from csv file
    private static List<Customer> customers = List.of(new Customer("KHALEDKAR"), new Customer("YOUSEFSUL"), new Customer("TAYSEERSAB"));

    public static boolean validate(Account account) {
        for (Customer customer : customers) {
            if (customer.getName().equals(account.getId()))
                return false;
        }
        return true;
    }
}
