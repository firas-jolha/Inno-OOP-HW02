package main.utils;

import java.util.AbstractMap;
import java.util.Map;

/**
 * Represents the address or location of the shop
 */
public class Location {
    private String address;
    private Map.Entry<Double, Double> coordinates = new AbstractMap.SimpleEntry<Double, Double>(0.0,0.0);

    public void setAddress(String address) {
        this.address = address;
    }

    private void setCoordinates(Map.Entry<Double, Double> coordinates) {
        this.coordinates = coordinates;
    }

    public void setCoordinates(Double x, Double y){
        setCoordinates(new AbstractMap.SimpleEntry<>(x, y));
    }

    public Map.Entry<Double, Double> getCoordinates() {
        return coordinates;
    }

    public String getAddress() {
        return address;
    }
}
