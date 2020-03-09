package com.despegar;

public class TestProject {
    public static void main(String[] args) {
        Address addWork = new Address("Juana Manso", 999);
        Address addMoth = new Address("Doblas", 282, "6ºA", "Buenos Aires", "Arg");

        System.out.println("A couple of addresses are created:");
        addWork.showAddress();
        addMoth.showAddress();

        Contact contact1 = new Contact("Nacho", "15-31415-926?", addWork);
        Contact contact2 = new Contact("Mauro", "4983-6245", addMoth);

        System.out.println("\nShow new contacts:");
        contact1.showContact();
        contact2.showContact();

        System.out.println("\nCreate an address book and add the contacts:");
        AddressBook myAddressBook = new AddressBook();
        myAddressBook.addContact(contact1);
        myAddressBook.addContact(contact2);
        myAddressBook.printAddressBook();

        System.out.println("\nSearch an existing contact:");
        myAddressBook.searchContact("Nacho").ifPresent( contact -> contact.showContact() );

        System.out.println("\nSearch a non-existing contact:");
        myAddressBook.searchContact("René").ifPresent(Contact::showContact);

        System.out.println("\nCreate a duplicated contact:");
        Contact contact2Bis = new Contact("Mauro", "15-3232-8165", addMoth);
        contact2Bis.showContact();
        System.out.println("\nAnd try to add the duplicated contact to the address book:");
        myAddressBook.addContact(contact2Bis);
        System.out.println("Address book remains unchanged:");
        myAddressBook.printAddressBook();

        System.out.println("\nEdit an existing contact:");
        myAddressBook.editContact(contact2Bis);
        myAddressBook.printAddressBook();
    }
}
