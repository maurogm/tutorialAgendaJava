package com.despegar;

import java.util.*;

public class AddressBook {
    private Set<String> contactNames = new TreeSet<>();
    private SortedMap<String,Contact> contactMap= new TreeMap<>();

    public void printAddressBook() {
        for(String contactName : contactNames){
            contactMap.get(contactName).showContact();
        }
    }

    public Optional<Contact> searchContact(String contactName) {
        boolean existingContact = contactNames.contains(contactName);
        if (existingContact) {
            Contact contact = contactMap.get(contactName);
            return Optional.of(contact);
        } else {
            return Optional.empty();
        }
    }

    public void addContact(Contact newContact) {
        boolean addedContact = contactNames.add(newContact.getName());
        if (addedContact) {
            contactMap.put(newContact.getName(), newContact);
        } else {
            System.out.println("Warning: Already existent contact's name. Use editContact() instead.");
        }
    }

    public void removeContact(Contact contact) {
        removeContact(contact.getName());
    }

    public void removeContact(String contactName) {
        boolean removedContact = contactNames.remove(contactName);
        if (removedContact) {
            contactMap.remove(contactName);
        } else {
            System.out.println("Warning: Tried to remove non-existent contact");
        }
    }

    public void editContact(Contact updatedContact) {
        removeContact(updatedContact.getName());
        addContact(updatedContact);
    }

}