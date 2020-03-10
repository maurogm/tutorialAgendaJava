package com.despegar;

public class Address {
    private final String country, city, streetName, apartment;
    private final int streetNumber;
    private static String DEFAULT_COUNTRY = "Argentina";
    private static String DEFAULT_CITY = "CABA";

    public Address(String st, int n, String ap, String ci, String co) {
        country = co;
        city = ci;
        streetName = st;
        apartment = ap;
        streetNumber = n;
    }
    public Address(String a, int n, String ap) {
        streetName = a;
        streetNumber = n;
        apartment = ap;
        city = DEFAULT_CITY;
        country = DEFAULT_COUNTRY;
    }


    @Override
    public String toString() {
        String aptForConcat = (apartment.equals("")) ? ", " : " " + apartment + ", ";
        return streetName + " " + streetNumber + aptForConcat + city + ", " + country + ".";
    }

    public void showAddress() {
        System.out.println(this.toString());
    }

    //Getters:
    public String getCountry() {
        return country;
    }
    public String getCity() {
        return city;
    }
    public String getStreetName() {
        return streetName;
    }
    public String getApartment() {
        return apartment;
    }
    public int getStreetNumber() {
        return streetNumber;
    }
}
