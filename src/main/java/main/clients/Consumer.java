package main.clients;

import main.shop.Order;
import main.utils.Gender;

import java.util.UUID;

public class Consumer extends Client {

    public Consumer(String name, Integer age, Gender gender) {
        super(name, age, gender);
    }

    public Consumer() {
        this("Customer-" + UUID.randomUUID().toString(), 0, Gender.MALE);
    }

    public Order makeOrder(Order order){
        return new Order(order);
    }
}
