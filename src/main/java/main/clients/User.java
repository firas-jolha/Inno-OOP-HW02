package main.clients;

import main.utils.Gender;
import org.hamcrest.core.IsNull;

import static org.valid4j.Assertive.require;

/**
 * Represents the user of system including consumer and customer
 */
public class User {
    /**
     * name of user
     */
    private String name;

    /**
     * age of user
     */
    private Integer age;

    /**
     * gender of user
     */
    private final Gender gender;

    /**
     * Creates an instance of user
     *
     * @param name   name of user
     * @param age    age of user
     * @param gender gender of user
     */
    public User(String name, Integer age, Gender gender) {
        setName(name);
        setAge(age);
        require(gender, IsNull.notNullValue());
        this.gender = gender;
    }

    /**
     * Setter for name attribute
     *
     * @param name new name of user
     */
    public void setName(String name) {
        require(name != null && !name.equals(""));
        this.name = name;
    }

    /**
     * Setter for age attribute
     *
     * @param age new age of user
     */
    public void setAge(Integer age) {
        require(age > 0 && age <= 190);
        this.age = age;
    }

    /**
     * Getter for name attribute
     *
     * @return name of user
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for age attribute
     *
     * @return age of user
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Getter for gender attribute
     *
     * @return gender of user
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * String representation of user
     *
     * @return string
     */
    @Override
    public String toString() {
        return "{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", gender=" + getGender() +
                '}';
    }

}
