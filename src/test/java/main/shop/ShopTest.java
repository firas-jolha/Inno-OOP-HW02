package main.shop;

import main.clients.Consumer;
import main.clients.Customer;
import main.stocks.Inventory;
import main.utils.Gender;
import main.utils.Location;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * A test class for Shop which tests the validity of shop methods
 */
@DisplayName("Shop Tester")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShopTest {

    // Check it for junit 5
    //https://www.petrikainulainen.net/programming/testing/junit-5-tutorial-writing-our-first-test-class/
    /**
     * Instance of Shop to test the its methods
     */
    private final Shop shop = new Shop();

    /**
     * An initiator for shop properties
     */
    @BeforeEach
    public void init() {
        shop.setName("Firas");
        shop.setLocation(new Location("Kazan", 140.0, 160.0));
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
    void addCustomer() {
        Customer customer = new Customer("Firas", 28, Gender.MALE);
        Integer i = shop.getCustomersSize();
        shop.addCustomer(customer);
        assertEquals(shop.getCustomersSize(), i+1);
    }

    /**
     * Testing addConsumer method, includes adding 4 consumers and checking the size of consumers list after every adding.
     *
     */
    @RepeatedTest(4)
    void addConsumer() {
        Consumer consumer = new Consumer("Firas", 28, Gender.MALE);
        Integer i = shop.getConsumersSize();
        shop.addConsumer(consumer);
        assertEquals(shop.getConsumersSize(), i+1);
    }

    @Test
    @Order(1)
    void addInventory() {

    }


    @Test
    @Order(2)
    void isAvailable() {
//        shop.isAvailable()
    }

    @Test
    @Order(3)
    void removeInventory() {
    }


    @Test
    @Order(4)
    void createOrder() {

        int size = shop.getOrdersSize();

//        Order order = new Order(shop, LocalDate.now(),);
//        shop.createOrder(order);
//        assertEquals(shop.getOrdersSize(), size + 1);
    }

    @Test
    @Order(5)
    void testToString() {
    }
}