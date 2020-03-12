package com.despegar;

import java.util.*;

public class AddressBook {
    private SortedMap<String,Contact> contactMap = new TreeMap<>();

    public AddressBook(Contact ... contactArray) {
        this.contactMap = new TreeMap<>();
        for (Contact contact : contactArray) {
            this.contactMap.put(contact.getName(), contact);
        }
    }
    public AddressBook(AddressBook oldAddressBook) {
        this.contactMap = new TreeMap<>(oldAddressBook.contactMap);
    }

    public void printAddressBook() {
        for(String contactName : contactMap.keySet()){
            contactMap.get(contactName).showContact();
        }
    }

    public int getContactsCount() {
        return contactMap.size();
    }

    public Optional<Contact> searchContact(String contactName) {
        return Optional.ofNullable(contactMap.get(contactName));
        /*
        boolean existingContact = contactNames.contains(contactName);
        if (existingContact) {
            Contact contact = contactMap.get(contactName);
            return Optional.of(contact);
        } else {
            return Optional.empty();
        }*/
    }

    public void addContact(Contact newContact) {
        contactMap.put(newContact.getName(), newContact);
    }

    public void removeContact(Contact contact) {
        contactMap.remove(contact.getName());
    }

    public void removeContact(String contactName) {
        contactMap.remove(contactName);
    }

    //public SortedMap<String, List<Contact>> groupContactsByCityManual() {
    public TreeMap<String, ArrayList<Contact>> groupContactsByCityManual() {
        TreeMap<String, ArrayList<Contact>> contactsByCity = new TreeMap<>();
        for(String contactName : contactMap.keySet()){
            Contact contact = contactMap.get(contactName);
            String contactCity = contact.getAddress().getCity();
            if (!contactsByCity.containsKey(contactCity)) {
                contactsByCity.put(contactCity, new ArrayList<>());
            }
            contactsByCity.get(contactCity).add(contact);
        }
        return contactsByCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressBook that = (AddressBook) o;

        return contactMap != null ? contactMap.equals(that.contactMap) : that.contactMap == null;
    }

    @Override
    public int hashCode() {
        return contactMap != null ? contactMap.hashCode() : 0;
    }
}