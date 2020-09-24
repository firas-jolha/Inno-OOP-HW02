package main.shop;

import main.clients.Consumer;
import main.clients.Customer;
import main.inventories.ExpirableInventory;
import main.inventories.Inventory;
import main.utils.Gender;
import main.utils.Location;
import main.utils.ProductCategory;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A test class for Shop which tests the validity of shop methods
 */
@DisplayName("Shop Tester")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShopTest {

    /**
     * Instance of location
     */
    private static final Location location = new Location("Kazan, Tatarstan, 420111", 55.82052, 49.09175);

    /**
     * Instance of Shop to test the its methods
     */
    private static final Shop shop = new Shop("Восточный Базар", location);

    /**
     * A reference to inventory
     */
    private static Inventory inventory;

    /**
     * The specified amount of inventory
     */
    private static final int inventoryAmount = 14;

    /**
     * An initiator for shop properties
     * This method will be called before the running test cases of other methods in this test class
     */
    @BeforeAll
    public static void init() {
        inventory = new ExpirableInventory("product", LocalDate.now(), 14.3, ProductCategory.FOOD, LocalDate.now().plusMonths(6));
    }

    /**
     * Testing the getName method
     */
    @Test
    @Order(-4)
    // order of test, this is the first test method because -4 is the smallest order specified in this class
    void getName() {
        assertEquals(shop.getName(), "Восточный Базар");
    }

    /**
     * Testing the setName method
     */
    @Test
    @Order(-3)
    // second test method
    void setName() {
        assertEquals(shop.getName(), "Восточный Базар");
    }

    /**
     * Testing getLocation method
     */
    @Test
    @Order(-2)
    void getLocation() {
        assertEquals(shop.getLocation(), location);
    }

    /**
     * Testing setLocation method
     */
    @Test
    @Order(-1)
    void setLocation() {
        shop.setLocation(new Location("Kazan", 140.0, 160.0));
        assertEquals(shop.getLocation().getAddress(), "Kazan");
        assertEquals(shop.getLocation().getCoordinatesX(), 140.0);
        assertEquals(shop.getLocation().getCoordinatesY(), 160.0);
    }

    /**
     * Testing addCustomer method, includes adding 4 customers and checking the size of customers list after every adding.
     */
    @RepeatedTest(4)
    @Order(1)
    void addCustomer() {
        Customer customer = new Customer("Firas", 28, Gender.MALE);
        int i = shop.customers.size();
        shop.addCustomer(customer);
        assertEquals(shop.customers.size(), i + 1);
    }

    /**
     * Testing addConsumer method, includes adding 4 consumers and checking the size of consumers list after every adding.
     */
    @RepeatedTest(4)
    @Order(2)
    void addConsumer() {
        Consumer consumer = new Consumer("Firas", 28, Gender.MALE);
        int i = shop.consumers.size();
        shop.addConsumer(consumer);
        assertEquals(shop.consumers.size(), i + 1);
    }

    /**
     * Testing addInventory method, includes adding 5 inventories and checking the list after each adding
     */
    @RepeatedTest(5)
    @Order(3)
    void addInventory() {
        Number n = shop.stocks.get(1).getInventories().get(inventory);
        int size = n == null ? 0 : n.intValue();
        shop.addInventory(inventory, inventoryAmount);
        assertEquals(shop.stocks.get(1).getInventories().get(inventory).intValue(), size + inventoryAmount);
    }

    /**
     * Testing of isAvailable method, Checks if the inventory is existed in stocks
     */
    @Test
    @Order(4)
    void isAvailable() {
        assertTrue(shop.isAvailable(inventory, inventoryAmount));
    }

    /**
     * Testing of removeInventory method, will remove 3 inventories from stocks
     */
    @RepeatedTest(3)
    @Order(5)
    void removeInventory() {
        Number n = shop.stocks.get(1).getInventories().get(inventory);
        int size = n == null ? 0 : n.intValue();// when no inventories n will be null or zero number of inventories
        shop.removeInventory(inventory, inventoryAmount);
        assertEquals(shop.stocks.get(1).getInventories().get(inventory).intValue(), size - inventoryAmount);
    }

    /**
     * Testing of createOrder method, Checks if order is created and added to list of orders
     */
    @Test
    @Order(6)
    void createOrder() {
        int size = shop.orders.size();
        shop.createOrder(inventory, inventoryAmount, shop.consumers.get(0), shop.customers.get(0));
        assertEquals(shop.orders.size(), size + 1);
    }

}