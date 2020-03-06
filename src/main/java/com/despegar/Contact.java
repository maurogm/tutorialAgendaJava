package com.despegar;

import java.util.Objects;

public class Contact {
    private String name;
    private String phone;
    private Address address;
    // Obs: May have multiple addresses or phone numbers. Should upgrade variables to lists.

    Contact (String name, String phone, Address address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public void showContact() {
        System.out.println(name + ". Phone: " + phone + ". Address: " + address.toString());
    }

    //Retrieve methods:
    public Address getAddress() {
        return address;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }

    //Edit methods:
    public void setAddress(Address newAddress) {
        address = newAddress;
    }
    public void setName(String newName) {
        name = newName;
    }
    public void editPhone(String newPhone) {
        phone = newPhone;
    }

}
