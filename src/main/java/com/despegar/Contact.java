package com.despegar;

import java.util.Objects;

public class Contact {
    private final String name;
    private final String phone;
    private final Address address;
    // Obs: May have multiple addresses or phone numbers. Should upgrade variables to lists.

    Contact (String name, String phone, Address address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    Contact (Contact contact) {
        this.name = contact.name;
        this.phone = contact.phone;
        this.address = contact.address;
    }

    public void showContact() {
        System.out.println(name + ". Phone: " + phone + ". Address: " + address.toString());
    }

    //Getters:
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public Address getAddress() {
        return address;
    }
}
