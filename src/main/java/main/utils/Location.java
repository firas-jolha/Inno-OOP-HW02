package main.utils;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.valid4j.Assertive;

import java.util.AbstractMap;
import java.util.Map;

/**
 * Represents the location of the shop
 */
public class Location {
    /**
     * The address of location
     */
    private String address;

    /**
     * coordinates of location
     */
    private Map.Entry<Double, Double> coordinates;

    /**
     * Constructor for Location
     * @param address the address
     * @param coordinatesX the X coordinates of location
     * @param coordinatesY the Y coordinates of location
     */
    public Location(String address, Double coordinatesX, Double coordinatesY) {
        this.address = address;
        this.coordinates = new AbstractMap.SimpleEntry<>(coordinatesX, coordinatesY);
    }

    /**
     * Setter method for Address
     * @param address the address of location
     */
    public void setAddress(String address) {
        Assertive.require(address, IsNull.notNullValue());
        Assertive.require(address, IsNot.not(""));
        this.address = address;
    }

    /**
     * Setter method for coordinates but for private usage
     * @param coordinates the coordinates of location
     */
    private void setCoordinates(Map.Entry<Double, Double> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Another setter method for coordinates for public usage
     * @param x the X coordinates of location
     * @param y the Y coordinates of location
     */
    public void setCoordinates(Double x, Double y){
        setCoordinates(new AbstractMap.SimpleEntry<>(x, y));
    }

    /**
     * Getter method for coordinates of location
     * @return the Pair of coordinates
     */
    public Map.Entry<Double, Double> getCoordinates() {
        return coordinates;
    }

    /**
     * Getter method for X coordinates of location
     * @return the X coordinates of location
     */
    public Double getCoordinatesX(){ return coordinates.getKey();}

    /**
     * Getter method for Y coordinates of location
     * @return the Y coordinates of location
     */
    public Double getCoordinatesY(){ return coordinates.getValue();}

    /**
     * Getter method for address of location
     * @return the address of location
     */
    public String getAddress() {
        return address;
    }

    /**
     * Represents the Location class as a string.
     * Returns string info about the location
     * @return
     */
    @Override
    public String toString() {
        return "{" +
                "address='" + getAddress() + '\'' +
                ", coordinates X = " + getCoordinatesX() +
                ", coordinates Y = " + getCoordinatesY() +
                '}';
    }
}
