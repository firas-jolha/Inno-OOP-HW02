package main.clients;

import main.utils.Gender;
import static org.valid4j.Assertive.*;

public class Client {

    private String name;

    private Integer age;

    private final Gender gender;

    public Client(String name, Integer age, Gender gender) {
        require(name!=null && !name.equals("") && age>0 && age<190,"Error Message");
        setName(name);
        setAge(age);
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
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }

}
