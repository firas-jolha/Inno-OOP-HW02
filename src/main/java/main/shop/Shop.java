package main.shop;

import main.clients.Consumer;
import main.clients.Customer;
import main.utils.ProductCategory;
import main.inventories.Inventory;
import main.stocks.Stock;
import main.utils.Location;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.valid4j.Assertive;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Shop {
    private String name;
    private Location location;
    public final ArrayList<Customer> customers = new ArrayList<>();
    public final ArrayList<Consumer> consumers = new ArrayList<>();
    public final ArrayList<Order> orders = new ArrayList<>();
    public final ArrayList<Stock> stocks = new ArrayList<>();
    {
        for (ProductCategory category:ProductCategory.values()){
            stocks.add(new Stock(category));
        }
    }

    public Integer getNumInventories(){
        int accu = 0;
        for (Stock stock: stocks){
            accu += stock.getInventories().size();
        }
        return  accu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void addCustomer(Customer customer) {
        Assertive.require(customer, IsNull.notNullValue());
        customers.add(customer);
    }

    public void addConsumer(Consumer consumer) {
        Assertive.require(consumer, IsNull.notNullValue());
        consumers.add(consumer);
    }

    public void addInventory(Inventory inventory, Integer amount) {
        Assertive.require(inventory, IsNull.notNullValue());
        Assertive.require(amount > 0);
        ProductCategory category = inventory.getCategory();
        IntStream.range(0, stocks.size()).
                filter(i -> stocks.get(i).getCategory() == category).
                findFirst().
                ifPresent(i -> stocks.get(i).addInventory(inventory, amount));
    }

    public void removeInventory(Inventory inventory, Integer amount) {
        Assertive.require(inventory, IsNull.notNullValue());
        Assertive.require(amount > 0);
        ProductCategory category = inventory.getCategory();
        IntStream.range(0, stocks.size())
                .filter(i -> stocks.get(i).getCategory() == category)
                .findFirst()
                .ifPresent(i -> stocks.get(i).removeInventory(inventory, amount));
    }

    public boolean isAvailable(Inventory inventory, Integer amount) {
        Assertive.require(inventory, IsNull.notNullValue());
        Assertive.require(amount > 0);
        for (Stock stock : stocks) {
            if ((stock.isAvailable(inventory, amount)) != null) {
                return true;
            }
        }
        return false;
    }

    public boolean isInventoryExpired(Inventory inventory){
        Assertive.require(inventory, IsNull.notNullValue());
        return stocks.stream().
                filter(stock -> (stock.isAvailable(inventory, 1)) != null)
                .findFirst()
                .map(stock -> stock.isInventoryExpired(inventory))
                .orElse(true);
    }

    public void createOrder(Inventory inventory,  Integer amount, Consumer consumer, Customer customer) {
        Assertive.require(consumer, IsNull.notNullValue());
        Assertive.require(customer, IsNull.notNullValue());
        if(consumers.contains(consumer)){
            if (isAvailable(inventory, amount) && !isInventoryExpired(inventory) ) {
                Order order = consumer.makeOrder(inventory, customer, amount);
                if (order != null) {
                    Integer size = orders.size();
                    orders.add(order);
                    Assertive.ensure(orders.size() == size + 1);
                } else {
                    System.out.println(
                            new StringBuilder()
                                    .append("Cannot make order")
                                    .append(order)
                                    .append(" for that consumer ")
                                    .append(consumer)
                                    .toString());
                }
            }else{
                System.out.println("We don't have available inventories!");
            }
        }else{
            System.out.println("Customer is not existed!");
        }
    }

    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", location=" + location +
                ", customers=" + customers +
                ", consumers=" + consumers +
                ", orders=" + orders +
                ", stocks=" + stocks +
                '}';
    }
}
