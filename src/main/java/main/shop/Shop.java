package main.shop;

import main.clients.Consumer;
import main.clients.Customer;
import main.stocks.Inventory;
import main.stocks.Stock;
import main.utils.Location;
import main.utils.ProductCategory;
import org.hamcrest.core.IsNull;
import org.valid4j.Assertive;

import java.util.ArrayList;

public class Shop {
    private String name;
    private Location location;
    private final ArrayList<Customer> customers = new ArrayList<>();
    private final ArrayList<Consumer> consumers = new ArrayList<>();
    private final ArrayList<Order> orders = new ArrayList<>();
    private final ArrayList<Stock> stocks = new ArrayList<>();
    {
        for (ProductCategory category: ProductCategory.values()){
            stocks.add(Stock.createStock(category));
        }
    }

    public Integer getCustomersSize(){
        return customers.size();
    }

    public Integer getConsumersSize(){
        return consumers.size();
    }

    public Integer getOrdersSize(){ return orders.size();}

    public Integer getStocksSize(){ return stocks.size();}

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

    public void addCustomer(Customer customer){
        Assertive.require(customer, IsNull.notNullValue());
        customers.add(customer);
    }

    public void addConsumer(Consumer consumer){
        Assertive.require(consumer, IsNull.notNullValue());
        consumers.add(consumer);
    }

    public void addInventory(Inventory inventory, Integer amount){
        Assertive.require(inventory, IsNull.notNullValue());
        Assertive.require(amount>0);
        ProductCategory category = inventory.getCategory();
        for (int i = 0; i < stocks.size(); i++) {
            if (stocks.get(i).getCategory()==category)
                stocks.get(i).addInventory(inventory,amount);
        }
    }

    public void removeInventory(Inventory inventory, Integer amount){
        Assertive.require(inventory, IsNull.notNullValue());
        Assertive.require(amount>0);
        ProductCategory category = inventory.getCategory();
        for (int i = 0; i < stocks.size(); i++) {
            if (stocks.get(i).getCategory()==category)
                stocks.get(i).removeInventory(inventory,amount);
        }
    }

    public boolean isAvailable(Inventory inventory, Integer amount){
        Assertive.require(inventory, IsNull.notNullValue());
        Assertive.require(amount>0);
        for (Stock stock: stocks){
            if((stock.isAvailable(inventory, amount))!=null){
                return true;
            }
        }
        return false;
    }

    public void createOrder(Order order){
        Assertive.require(order, IsNull.notNullValue());
        orders.add(order);
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
