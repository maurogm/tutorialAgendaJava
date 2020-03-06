package com.despegar;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private List<Contact> contactList = new ArrayList();

    void printAddressBook() {
        for (Contact contact : contactList) {
            contact.showContact();
        }
    }

    Contact searchContact(String name) {
        for(Contact contact : contactList) {
            if (contact.getName().equals(name)) { return contact; }
        }
        return null;
    }
    void addContact(Contact newContact) {
        if (searchContact(newContact.getName()) == null) {
            contactList.add(newContact);
        } else {
            System.out.println("Warning: Already existent contact's name. Use editContact() instead.");
        }
    }
    void removeContact(Contact contact) {
        boolean removeAction = contactList.remove(contact);
        if (!removeAction) {
            System.out.println("Warning: Tried to remove non-existent contact");
        }
    }
    void removeContact(String name) {
        Contact contactObject = searchContact(name);
        if (contactObject == null) {
            System.out.println("Warning: Tried to remove non-existent contact");
        } else {
            removeContact(contactObject);
        }
    }
    void editContact(Contact updatedContact) {
        removeContact(updatedContact.getName());
        addContact(updatedContact);
    }
}
