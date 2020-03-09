package com.despegar;

import java.util.*;

public class AddressBook {
    private List<Contact> contactList = new ArrayList<>();

    public void printAddressBook() {
        for (Contact contact : contactList) {
            contact.showContact();
        }
    }

    public Optional<Contact> searchContact(String name) {
        for(Contact contact : contactList) {
            if (contact.getName().equals(name)) {
                return Optional.of(contact);
            }
        }
        return Optional.empty();
    }

    public void addContact(Contact newContact) {
        if (!searchContact(newContact.getName()).isPresent()) {
            contactList.add(newContact);
        } else {
            System.out.println("Warning: Already existent contact's name. Use editContact() instead.");
        }
    }

    public void removeContact(Contact contact) {
        boolean removeAction = contactList.remove(contact);
        if (!removeAction) {
            System.out.println("Warning: Tried to remove non-existent contact");
        }
    }

    public void removeContact(String name) {
        Optional<Contact> contactObject = searchContact(name);
        if (!contactObject.isPresent() ) {
            System.out.println("Warning: Tried to remove non-existent contact");
        } else {
            removeContact(contactObject.get());
        }
    }
    public void editContact(Contact updatedContact) {
        removeContact(updatedContact.getName());
        addContact(updatedContact);
    }

}