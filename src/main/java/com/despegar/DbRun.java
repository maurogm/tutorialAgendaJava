package com.despegar;

import java.sql.*;

public class DbRun {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:mydb.db";

        Contact contact = new Contact("Alan",   "15-12345-678", new Address("Rivadavia", 502, "7ºA", "Buenos Aires", "Argentina"));

        String name = contact.getName();

        try(Connection conn = DriverManager.getConnection(url)) {

            AddressBookDb db = new AddressBookDb(conn);

            System.out.println("Is " + name + " present before add method?: " + db.searchContactByName(name).isPresent());
            db.addContact(contact);
            System.out.print("After addition: " + db.searchContactByName(name).isPresent());
            System.out.println("; and total contacts = " + db.getContactsCount());

            db.removeContact(name);
            System.out.print("After removal: " + db.searchContactByName(name).isPresent());
            System.out.println("; and total contacts = " + db.getContactsCount());

            System.out.println("The database consists of:");

            db.printAddressBook();

//            String sql = "SELECT * FROM CONTACT WHERE NAME = ?";
//
//            PreparedStatement statement = conn.prepareStatement(sql);
//
//            statement.setString(1, name);
//
//            ResultSet resultSet = statement.executeQuery();





        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
