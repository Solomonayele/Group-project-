package edu.metrostate;

public class Service {
    // Attributes
    private String name;
    private double price;
    private String description;
    private int lengthOfTime;

    // Constructor
    public Service(String name, double price, String description, int lengthOfTime) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.lengthOfTime = lengthOfTime;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLengthOfTime() {
        return lengthOfTime;
    }
    public void setLengthOfTime(int lengthOfTime) {
        this.lengthOfTime = lengthOfTime;
    }
}
