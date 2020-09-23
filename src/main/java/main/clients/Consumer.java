package main.clients;

import main.inventories.Inventory;
import main.shop.Order;
import main.utils.Gender;

import java.time.LocalDate;
import java.util.UUID;

public class Consumer extends Client {

    public Consumer(String name, Integer age, Gender gender) {
        super(name, age, gender);
    }

    public Consumer() {
        this("Customer-" + UUID.randomUUID().toString(), 30, Gender.MALE);
    }


    public Order makeOrder(Inventory inventory, Customer customer, Integer amount){
        return new Order(inventory,amount, this, customer);
    }

}
