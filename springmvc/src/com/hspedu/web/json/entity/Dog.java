package com.hspedu.web.json.entity;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class Dog {
    private String name;
    private String address;

    public Dog(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Dog() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
