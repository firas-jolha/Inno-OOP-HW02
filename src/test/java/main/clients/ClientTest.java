package main.clients;

import main.utils.Gender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    public Client client = new Client("Ga",5, Gender.MALE);

    @Test
    void setName() {
        client.setName("Firas");
        assert client.getName().equals("Firas");
    }

    @Test
    void setAge() {
    }

    @Test
    void testToString() {
    }
}