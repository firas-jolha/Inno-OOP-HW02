package main.clients;

import main.utils.Gender;

/**
 * Represents customer in the system, which is a user
 */
public class Customer extends User {

    /**
     * Creates an instance of customer
     *
     * @param name   name of customer
     * @param age    age of customer
     * @param gender gender of customer
     */
    public Customer(String name, Integer age, Gender gender) {
        super(name, age, gender);
    }
}
