package com.despegar;

public class Address {
    private String country, city, streetName, apartment;
    private int streetNumber;
    private static String DEFAULT_COUNTRY = "Argentina";
    private static String DEFAULT_CITY = "CABA";

    // Constructors:
    Address(String a, int n) {
        streetName = a;
        streetNumber = n;
        city = DEFAULT_CITY;
        country  = DEFAULT_COUNTRY;
        apartment = "";
    }
    Address(String st, int n, String ap, String ci, String co) {
        country = co;
        city= ci;
        streetName = st;
        apartment = ap;
        streetNumber = n;
    }

    //Methods:
    public String getAddressString() {
        String aptForConcat = (apartment.equals("")) ? ", " : " " + apartment + ", ";
        return streetName + " " + streetNumber + aptForConcat + city + ", " + country + ".";
    }
    public void showAddress() {
        System.out.println(this.getAddressString());
    }

    public static void setDefaultCountry(String newCountry) {
        DEFAULT_COUNTRY = newCountry;
    }
    public static void setDefaultCity(String newCity) {
        DEFAULT_CITY = newCity;
    }
}
