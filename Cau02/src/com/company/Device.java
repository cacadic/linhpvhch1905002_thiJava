package com.company;

public class Device {
    private int ID, Price, Device_count;
    private String Name;

    public Device(int ID, int price, int device_count, String name) {
        this.ID = ID;
        this.Price = price;
        this.Device_count = device_count;
        this.Name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getDevice_count() {
        return Device_count;
    }

    public void setDevice_count(int device_count) {
        Device_count = device_count;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
