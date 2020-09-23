package main.shop;

import main.clients.Consumer;
import main.clients.Customer;
import main.inventories.ExpirableInventory;
import main.inventories.Inventory;
import main.utils.Gender;
import main.utils.Location;
import main.utils.ProductCategory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * A test class for Shop which tests the validity of shop methods
 */
@DisplayName("Shop Tester")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShopTest {

    /**
     * Instance of Shop to test the its methods
     */
    private static final Shop shop = new Shop();
    private static Inventory inventory;
    private static int inventoryAmount = 14;

    /**
     * An initiator for shop properties
     */
    @BeforeAll
    public static void init() {
        shop.setName("Firas");
        shop.setLocation(new Location("Kazan", 140.0, 160.0));
        inventory = new ExpirableInventory("product",LocalDate.now(),14.3, ProductCategory.FOOD, LocalDate.now().plusMonths(6));
    }

    /**
     * Testing the getName method
     */
    @Test
    void getName() {
        assertEquals(shop.getName(), "Firas");
    }

    /**
     * Testing the setName method
     */
    @Test
    void setName() {
        assertEquals(shop.getName(), "Firas");
    }

    /**
     * Testing getLocation method
     */
    @Test
    void getLocation() {
        assertEquals(shop.getLocation().getCoordinatesX(),140.0);
        assertEquals(shop.getLocation().getCoordinatesY(),160.0);
        assertEquals(shop.getLocation().getAddress(),"Kazan");
    }

    /**
     * Testing setLocation method
     */
    @Test
    void setLocation() {
        shop.setLocation(new Location("Kazan", 140.0, 160.0));
        assertEquals(shop.getLocation().getAddress(),"Kazan");
        assertEquals(shop.getLocation().getCoordinatesX(),140.0);
        assertEquals(shop.getLocation().getCoordinatesY(),160.0);
    }

    /**
     * Testing addCustomer method, includes adding 4 customers and checking the size of customers list after every adding.
     *
     */
    @RepeatedTest(4)
    @Order(1)
    void addCustomer() {
        Customer customer = new Customer("Firas", 28, Gender.MALE);
        Integer i = shop.customers.size();
        shop.addCustomer(customer);
        assertEquals(shop.customers.size(), i+1);
    }

    /**
     * Testing addConsumer method, includes adding 4 consumers and checking the size of consumers list after every adding.
     *
     */
    @RepeatedTest(4)
    @Order(2)
    void addConsumer() {
        Consumer consumer = new Consumer("Firas", 28, Gender.MALE);
        Integer i = shop.consumers.size();
        shop.addConsumer(consumer);
        assertEquals(shop.consumers.size(), i+1);
    }

    @RepeatedTest(5)
    @Order(3)
    void addInventory() {
        Number n = shop.stocks.get(1).getInventories().get(inventory);
        int size = n==null?0:n.intValue();
        shop.addInventory(inventory,inventoryAmount);
        assertEquals(shop.stocks.get(1).getInventories().get(inventory).intValue(), size+inventoryAmount);
    }


    @Test
    @Order(4)
    void isAvailable() {
        assertEquals(shop.isAvailable(inventory,inventoryAmount),true);
    }

    @RepeatedTest(3)
    @Order(5)
    void removeInventory() {
        Number n = shop.stocks.get(1).getInventories().get(inventory);
        int size = n==null?0:n.intValue();
        shop.removeInventory(inventory,inventoryAmount);
        assertEquals(shop.stocks.get(1).getInventories().get(inventory).intValue(), size-inventoryAmount);
    }


    @Test
    @Order(6)
    void createOrder() {
        int size = shop.orders.size();
        shop.createOrder(inventory, inventoryAmount, shop.consumers.get(0),shop.customers.get(0));
        assertEquals(shop.orders.size(), size+1);
    }

    @Test
    @Order(Integer.MAX_VALUE)
    void testToString() {
        System.out.println(shop.toString());
    }

}