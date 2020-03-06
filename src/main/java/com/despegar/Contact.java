package com.despegar;

public class Contact {
    private String name;
    private String phone;
    private Address address;
    // Obs: May have multiple addresses or phone numbers. Should upgrade variables to lists.

    Contact (String n, String phn, Address ad) {
        name = n;
        phone = phn;
        address = ad;
    }

    public void showContact() {
        System.out.println(name + ". Phone: " + phone + ". Address: " + address.getAddressString());
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
    public void editAddress(Address newAddress) {
        address = newAddress;
    }
    public void editName(String newName) {
        name = newName;
    }
    public void editPhone(String newPhone) {
        phone = newPhone;
    }

}
