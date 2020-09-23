package main.clients;

import main.utils.Gender;
import org.hamcrest.core.IsNull;

import static org.valid4j.Assertive.*;

public class Client {

    private String name;

    private Integer age;

    private final Gender gender;

    public Client(String name, Integer age, Gender gender) {
        setName(name);
        setAge(age);
        require(gender, IsNull.notNullValue());
        this.gender = gender;
    }
    public void setName(String name) {
        require(name!=null && !name.equals(""));
        this.name = name;
    }

    public void setAge(Integer age) {
        require(age>0 && age<=190);
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", gender=" + getGender() +
                '}';
    }

}
