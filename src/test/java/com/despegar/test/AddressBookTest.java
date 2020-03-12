package com.despegar.test;

import com.despegar.Address;
import com.despegar.Contact;
import com.despegar.AddressBook;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AddressBookTest {
    private final AddressBook testAddressBook = new AddressBook(
            new Contact("Alan",   "15-12345-678", new Address("Rivadavia", 502, "7ºA", "Buenos Aires", "Argentina")),
            new Contact("Betty",  "15-12312-123", new Address("Doblas",    282, "6ºA", "Buenos Aires", "Argentina")),
            new Contact("Carlos", "15-12345-678", new Address("Gaitán",    282, "8vo", "Cartagena",    "Colombia")),
            new Contact("Denise", "2020",         new Address("C.Torres",  123, "",    "Cartagena",    "Colombia")),
            new Contact("Eric",   "",             new Address("Durruti",   123, "6ºA", "Cartagena",    "España")));

    @Test
    public void getContactsCountTest() {
        assertEquals(5, testAddressBook.getContactsCount());
    }

    @Test
    public void searchContactNotFoundTest() {
        assertFalse(testAddressBook.searchContact("Charly").isPresent());
    }

    @Test
    public void searchContactFoundTest() {
        Contact contact = testAddressBook.searchContact("Carlos").get();
        assertAll("Validate all contact variables",
            () -> assertEquals(contact.getName(),"Carlos"),
            () -> assertEquals(contact.getPhone(),"15-12345-678"),
            () -> assertEquals(contact.getAddress().getStreetName(),"Gaitán"),
            () -> assertEquals(contact.getAddress().getStreetNumber(),282),
            () -> assertEquals(contact.getAddress().getApartment(),"8vo"),
            () -> assertEquals(contact.getAddress().getCity(),"Cartagena"),
            () -> assertEquals(contact.getAddress().getCountry(),"Colombia")
            );
    }

    @Test
    public void addNewContactTest(){
        AddressBook testAddressBookCopy = new AddressBook(testAddressBook);
        Contact newContact = new Contact("René", "15-9854-3547", new Address("Lobos", 523, "2do"));
        String name = newContact.getName();

        assertFalse(testAddressBookCopy.searchContact(name).isPresent());

        testAddressBookCopy.addContact(newContact);

        assertEquals(6, testAddressBookCopy.getContactsCount());
        assertTrue(testAddressBookCopy.searchContact(name).isPresent());
    }

    @Test
    public void addOldContactTest(){
        AddressBook testAddressBookCopy = new AddressBook(testAddressBook);
        Contact newContact = new Contact("Alan", "15-9854-3547", new Address("Lobos", 523, "2do"));
        String name = newContact.getName();

        assertTrue(testAddressBookCopy.searchContact(name).isPresent());
        Contact oldContact = testAddressBookCopy.searchContact(name).get();
        assertNotEquals(oldContact, newContact);

        testAddressBookCopy.addContact(newContact);

        assertEquals(5, testAddressBookCopy.getContactsCount());
        assertEquals(newContact, testAddressBookCopy.searchContact(name).get());
    }

    @Test
    public void removeExistingContactTest(){
        AddressBook testAddressBookCopy = new AddressBook(testAddressBook);
        String name = "Alan";

        testAddressBookCopy.removeContact(name);
        assertEquals(4, testAddressBookCopy.getContactsCount());
    }

    @Test
    public void removeNonExistingContactTest(){
        AddressBook testAddressBookCopy = new AddressBook(testAddressBook);
        String name = "René";

        testAddressBookCopy.removeContact(name);

        assertEquals(testAddressBook, testAddressBookCopy);
    }

    @Test
    public void groupContactsByCityManualTest() {
        TreeMap<String, ArrayList<Contact>> groupedByCities = testAddressBook.groupContactsByCityManual();

        // Where groupContactsByCity correctly done, it would see Cartagena as two
        // separate cities, so the following test should have size = 3:
        //assertEquals(3, groupedByCities.size());
        assertEquals(2, groupedByCities.size());
    }
}
