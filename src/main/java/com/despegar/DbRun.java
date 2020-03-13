package com.despegar;

import java.sql.*;
import java.util.List;
import java.util.TreeMap;

public class DbRun {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:mydb.db";

        Contact contact = new Contact("Alan",   "15-12345-678", new Address("Rivadavia", 502, "7ºA", "Buenos Aires", "Argentina"));
        String contactName = contact.getName();

        AddressBook testAddressBook = new AddressBook(
                new Contact("Betty",  "15-12312-123", new Address("Doblas",    282, "6ºA", "Buenos Aires", "Argentina")),
                new Contact("Carlos", "15-12345-678", new Address("Gaitán",    282, "8vo", "Cartagena",    "Colombia")),
                new Contact("Denise", "2020",         new Address("C.Torres",  123, "",    "Cartagena",    "Colombia")),
                new Contact("Eric",   "",             new Address("Durruti",   123, "6ºA", "Cartagena",    "España")));

        try(Connection conn = DriverManager.getConnection(url)) {

            // Construct:
            AddressBookDb db = new AddressBookDb(conn);

            // Add:
            System.out.println("Is " + contactName + " present before add method?: " + db.searchContactByName(contactName).isPresent());
            db.addContact(contact);
            System.out.print("After addition: " + db.searchContactByName(contactName).isPresent());
            System.out.println("; and total contacts = " + db.getContactsCount());

            // Remove:
            db.removeContact(contactName);
            System.out.print("After removal: " + db.searchContactByName(contactName).isPresent());
            System.out.println("; and total contacts = " + db.getContactsCount());

            // Add entire AddressBook:
            db.addAddressBook(testAddressBook);
            System.out.println("The database consists of:");

            // Print object:
            db.printAddressBook();

            // Group by cities:
            System.out.println("Grouping by cities:");
            System.out.println(db.groupContactsByCity());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
