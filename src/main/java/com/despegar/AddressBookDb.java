package com.despegar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class AddressBookDb {
    private final Connection conn;

    public AddressBookDb(Connection conn){
        this.conn = conn;
    }

    public void printAddressBook() throws SQLException {
        String sqlQuery = "SELECT * FROM CONTACT";

        PreparedStatement statement = conn.prepareStatement(sqlQuery);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Contact contact = resultSet2Contact(resultSet);
            contact.showContact();
        }
    }


    public int getContactsCount() throws SQLException {
        String sqlQuery = "SELECT COUNT(1) FROM CONTACT";
        PreparedStatement statement = conn.prepareStatement(sqlQuery);

        ResultSet resultSet = statement.executeQuery();
        return resultSet.getInt(1);
    }

    public void addContact(Contact contact) throws SQLException {
        String sqlQuery = "INSERT INTO CONTACT (NAME, PHONE, COUNTRY, CITY, STREET_NAME, STREET_NUMBER, APARTMENT)" +
                "\n" + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sqlQuery);

        Address address = contact.getAddress();
        statement.setString(1, contact.getName());
        statement.setString(2, contact.getPhone());
        statement.setString(3, address.getCountry());
        statement.setString(4, address.getCity());
        statement.setString(5, address.getStreetName());
        statement.setInt   (6, address.getStreetNumber());
        statement.setString(7, address.getApartment());

        statement.executeUpdate();
    }

    public void removeContact(String contactName) throws SQLException {
        String sqlQuery = "DELETE FROM CONTACT WHERE NAME = ?";

        PreparedStatement statement = conn.prepareStatement(sqlQuery);
        statement.setString(1, contactName);

        statement.executeUpdate();
    }

    private Contact resultSet2Contact(ResultSet rs) throws SQLException {
        String name = rs.getString("NAME");
        String phone = rs.getString("PHONE");
        String country = rs.getString("COUNTRY");
        String city = rs.getString("CITY");
        String streetName = rs.getString("STREET_NAME");
        int streetNumber = rs.getInt("STREET_NUMBER");
        String apartment = rs.getString("APARTMENT");

        Address address = new Address(streetName, streetNumber, apartment, city, country);
        return new Contact(name, phone, address);
    }

    public Optional<Contact> searchContactByName(String contactName) throws SQLException {
        String sqlQuery = "SELECT * FROM CONTACT WHERE NAME = ?";

        PreparedStatement statement = conn.prepareStatement(sqlQuery);
        statement.setString(1, contactName);

        ResultSet resultSet = statement.executeQuery();

        Contact contact = null;
        while (resultSet.next()) {
                contact = resultSet2Contact(resultSet);
        }

        return Optional.ofNullable(contact);
    }
}