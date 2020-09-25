package main;


import main.clients.Consumer;
import main.clients.Customer;
import main.inventories.ExpirableInventory;
import main.inventories.Inventory;
import main.inventories.SizableInventory;
import main.shop.Shop;
import main.utils.Gender;
import main.utils.Location;
import main.utils.ProductCategory;
import main.utils.ProductSize;

import java.time.LocalDate;

/**
 * Main class (:)
 *
 * @author firasjolha
 * @since sep. 2020
 */
public class Main {

    /**
     * The entry point to the Program
     *
     * @param args the program arguments
     */
    public static void main(String[] args) {
        //Creating a shop with name and location
        Shop shop = new Shop("Восточный Базар", new Location("Kazan, Tatarstan, 420111", 55.82052, 49.09175));

        //Adding customers
        shop.addCustomer(new Customer("Firas", 28, Gender.MALE));
        shop.addCustomer(new Customer("Alhasan", 28, Gender.MALE));
        shop.addCustomer(new Customer("Habib", 19, Gender.MALE));
        shop.addCustomer(new Customer("Safaa", 34, Gender.FEMALE));
        shop.addCustomer(new Customer("Leyla", 18, Gender.FEMALE));

        //Adding consumers
        shop.addConsumer(new Consumer("Sawsan", 27, Gender.FEMALE));
        shop.addConsumer(new Consumer("Majed", 50, Gender.MALE));

        //Creating inventories
        Inventory[] inventories = new Inventory[5];
        inventories[0] = new ExpirableInventory("Milk", LocalDate.of(2020, 9, 23), 32.0, ProductCategory.FOOD, LocalDate.of(2020, 9, 30));
        // inventories[1] is expired
        inventories[1] = new ExpirableInventory("Мёд", LocalDate.of(2020, 9, 15), 200.0, ProductCategory.FOOD, LocalDate.of(2020, 9, 15));
        inventories[2] = new SizableInventory("Coat", LocalDate.of(2019, 6, 1), 5500.6, ProductCategory.CLOTHES, ProductSize.L);
        inventories[3] = new SizableInventory("Hat", LocalDate.of(2019, 4, 26), 730.8, ProductCategory.CLOTHES, ProductSize.XXL);
        inventories[4] = new ExpirableInventory("Aspirin", LocalDate.of(2018, 4, 2), 200.14, ProductCategory.PHARMACY, LocalDate.of(2021, 6, 11));

        //Adding 10 items of each inventory
        for (Inventory inventory : inventories) {
            shop.addInventory(inventory, 10);
        }
        //Print info about shop after creating of its components
        System.out.println(shop);

        System.out.println("=========================");
        System.out.println("=======>>Removing 6 items of inventory 0 (Milk product)");
        // removing 6 out of 10 items of inventory_0 will remain 4 items of inventory_0
        shop.removeInventory(inventories[0], 6);
        System.out.println(shop); // prints shop info as a string

        System.out.println("=========================");
        //Removing 24 out of 4 items of inventory_0
        //Will remove the inventory from the stock
        System.out.println("=======>>Removing 24 items of inventory 0 (Milk product) will remove the inventory from stock");
        shop.removeInventory(inventories[0], 24);
        System.out.println(shop); // prints shop info as a string

        System.out.println("=========================");
        System.out.println("=======>>Creating order of 5 items of inventory 2 (Coat product)...");
        System.out.println("-------------------------");
        //Create order of 5 items of inventory_2 by consumer_0 to customer_0
        shop.createOrder(inventories[2], 5, shop.getConsumer(0), shop.getCustomer(0));
        System.out.println("-------------------------");
        System.out.println(shop);

        System.out.println("=========================");
        System.out.println("=======>>Creating order of 15 items of inventory 4 (Aspirin product)...");
        System.out.println("-------------------------");
        //Won't create order of 5 items of inventory_4 by consumer_0 to customer_0
        //There is only 10 items of Aspirin
        shop.createOrder(inventories[4], 15, shop.getConsumer(0), shop.getCustomer(0));
        System.out.println("-------------------------");
        System.out.println(shop);

        System.out.println("=========================");
        System.out.println("=======>>Creating order of 5 items of inventory 1 (Мёд product)...");
        System.out.println("-------------------------");
        //Won't create order of 5 items of inventory_1
        //because the inventory_1 is expired
        shop.createOrder(inventories[1], 5, shop.getConsumer(0), shop.getCustomer(0));
        System.out.println("-------------------------");
        System.out.println(shop);
    }
}
