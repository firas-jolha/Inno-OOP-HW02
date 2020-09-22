package main.shop;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(Suite.class)
@DisplayName("Shop Tester")
class ShopTest {

    // Check it for junit 5
    //https://www.petrikainulainen.net/programming/testing/junit-5-tutorial-writing-our-first-test-class/

    @Test
    public void test(){
        Shop shop = new Shop();
        int i=5;
        assertEquals(i, 5);
    }

    @Test
    public void test2(){
        int y = 233;
        assertEquals(y,233);
    }



}