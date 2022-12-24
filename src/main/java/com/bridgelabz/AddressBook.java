package com.bridgelabz;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBook {

    static Scanner sc = new Scanner(System.in);
    static HashMap<String,ArrayList<ContactPerson>> addressBookList = new HashMap<>();// create an object of hashmap
    static ArrayList<ContactPerson> currentAddressBook;// declare variable
    static String currentAddressBookName;//declare variable

    static HashMap<String, ArrayList<ContactPerson>> cityContactList = new HashMap<>();
    static HashMap<String, ArrayList<ContactPerson>> stateContactList = new HashMap<>();

    static final String CSVFile = "C:\\Users\\Infinty system\\IdeaProjects\\Address_Book_CSV_JSON\\src\\main\\resources\\Contacts.CSV";

    public ContactPerson createContact() {
        ContactPerson person = new ContactPerson();//creating object of ContactPerson class
        System.out.print("Enter First Name: ");
        person.setFirstName(sc.next());//using object reference calling setFirstName method to set first name
        System.out.print("Enter Last Name: ");
        person.setLastName(sc.next());
        System.out.print("Enter Address: ");
        person.setAddress(sc.next());
        System.out.print("Enter City: ");
        person.setCity(sc.next());
        System.out.print("Enter State: ");
        person.setState(sc.next());
        System.out.print("Enter email: ");
        person.setEmail(sc.next());
        System.out.print("Enter ZipCode: ");
        person.setZipCode(sc.nextInt());
        System.out.print("Enter Phone Number: ");
        person.setPhoneNumber(sc.nextLong());
        System.out.println("created new contact");
        return person;
    }

    /*
    if duplicate contact found then display message as contact name already exists
     Add new contacts to address book if there is no duplicate contact
    */
    void addContact(ContactPerson person) {
        boolean isDuplicate = checkDuplicateContact(person);
        if (isDuplicate) {
            System.out.println("Contact name already exists");
        } else {
            currentAddressBook.add(person);
            //add to city contact list
            String city = person.getCity();
            ArrayList<ContactPerson> list;
            if (cityContactList.containsKey(city)) {
                list = cityContactList.get(city);
                list.add(person);

            } else {
                list = new ArrayList<>();
                list.add(person);
                cityContactList.put(city, list);
            }

            //add to State contact list
            String state= person.getState();
            if (stateContactList.containsKey(state)) {
                list = stateContactList.get(state);
                list.add(person);

            } else {
                list = new ArrayList<>();
                list.add(person);
                stateContactList.put(state, list);
            }
            System.out.println("contact added to AddressBook " + currentAddressBookName);
            System.out.println(person);
        }
    }

    //check duplicate contact using their name
    boolean checkDuplicateContact(ContactPerson newPerson) {
        return currentAddressBook.stream().anyMatch((person) -> person.getFirstName().equals(newPerson.getFirstName()));
    }

    /*
    Edit existing contact using person`s name
    if contact found then edit otherwise no contact found message will be display
     */
    public void editContact() {
        boolean isContactFound = false;
        System.out.println("Enter Name to edit Contact");
        String name = sc.next();
        for (ContactPerson contactPerson : currentAddressBook) { //iterate over the arraylist
            if (name.equalsIgnoreCase(contactPerson.getFirstName())) {
                isContactFound = true;
                System.out.print("Enter First Name :");
                contactPerson.setFirstName(sc.next());
                System.out.print("Enter Last Name :");
                contactPerson.setLastName(sc.next());
                System.out.print("Enter Address :");
                contactPerson.setAddress(sc.next());
                System.out.print("Enter City :");
                contactPerson.setCity(sc.next());
                System.out.print("Enter State :");
                contactPerson.setState(sc.next());
                System.out.print("Enter email :");
                contactPerson.setEmail(sc.next());
                System.out.print("Enter ZipCode :");
                contactPerson.setZipCode(sc.nextInt());
                System.out.print("Enter Phone Number :");
                contactPerson.setPhoneNumber(sc.nextLong());
                System.out.println(contactPerson);
                break;
            }
        }
        if (isContactFound) {
            System.out.println("Contact Updated Successfully..");
        } else {
            System.out.println("Oops...Contact not found");
        }
    }

    /*
     Delete contact using person`s name
     if contact found then delete that contact
      if no contact found then message will be display as oops....contact not found
      */
    public void deleteContact() {
        boolean isContactFound = false;
        System.out.println("enter name to delete contact");
        String name = sc.next();
        for (ContactPerson contactPerson : currentAddressBook){
            if (contactPerson.getFirstName().equalsIgnoreCase(name)) {
                System.out.println("contact found:");
                isContactFound = true;
                System.out.println(contactPerson);
                System.out.println("confirm to delete (y/n)");
                if (sc.next().equalsIgnoreCase("y")) {
                    currentAddressBook.remove(contactPerson);
                    System.out.println("contact deleted");
                }
                break;
            }
        }
        if (!isContactFound) {
            System.out.println("Opps... contact not found");
        }
    }

    /*
   add multiple address book
   each address book has unique name
    */
    void addNewAddressBook() {
        System.out.println("Enter name for AddressBook: ");
        String addressBookName = sc.next();
        ArrayList <ContactPerson> addressBook = new ArrayList();//creating object of arraylist
        //using object reference of hashmap calling put method and passing key as addressBookName and value as addressBook
        addressBookList.put(addressBookName,addressBook);
        System.out.println("new AddressBook created");
        currentAddressBook = addressBookList.get(addressBookName);//retrieve addressBookName using get method
        currentAddressBookName = addressBookName;//addressBookName store in current addressBookName
    }

    /*
    select address book
    if we want to add more contact in existing address book then select that address book
     */
    void selectAddressBook() {
        System.out.println(addressBookList.keySet());
        System.out.println("Enter name of address book:");
        String addressBookName = sc.next();

        for (String key : addressBookList.keySet()) {
            if (key.equalsIgnoreCase(addressBookName)) {
                currentAddressBook = addressBookList.get(key);
                currentAddressBookName = key;
            }
        }
        System.out.println("current AddressBook is: " + currentAddressBookName);
    }

    /*
    search contact by city and state
     */
    void searchContact() {
        System.out.println("1.Search by City \n2.Search by State");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                System.out.println("Enter city :");
                searchByCity(sc.next());
                break;
            case 2:
                System.out.println("Enter State :");
                searchByState(sc.next());
                break;
            default:
                searchContact();
                break;
        }
    }

    //search contact by city
    void searchByCity(String city) {
        System.out.println("Search Result: ");
        for (String addressBookName : addressBookList.keySet()) {//returns a set view of the keys contained in map
            //forEach iterate through every element of the stream
            addressBookList.get(addressBookName).stream().forEach((person) -> {
                if (person.getCity().equalsIgnoreCase(city))
                    System.out.println(person);//we will get contacts whose city is same
            });
        }
    }

    //search contact by state
    void searchByState(String state) {
        System.out.println("Search Result: ");
        for (String addressBookName : addressBookList.keySet()) {//returns a set view of the keys contained in map
            //forEach iterate through every element of the stream
            addressBookList.get(addressBookName).stream().forEach((person) -> {
                if (person.getState().equalsIgnoreCase(state))
                    System.out.println(person);//we will get contacts whose state is same
            });
        }
    }

    //Initialize city and state
    public void initializeCityAndStateContactList() {
        for (String key : addressBookList.keySet()) {
            for (ContactPerson person : addressBookList.get(key)) {
                String city = person.getCity();
                if (cityContactList.containsKey(city)) {
                    cityContactList.get(city).add(person);
                } else {
                    ArrayList<ContactPerson> list = new ArrayList<>();
                    list.add(person);
                    cityContactList.put(city, list);
                }

                String state = person.getState();
                if (stateContactList.containsKey(state)) {
                    stateContactList.get(state).add(person);
                } else {
                    ArrayList<ContactPerson> list = new ArrayList<>();
                    list.add(person);
                    stateContactList.put(state, list);
                }
            }
        }
    }

    //view contacts
    void viewContacts() {
        initializeCityAndStateContactList();//calling method
        System.out.println("*****************************\n1.View by City \n2.View by State");
        switch (sc.nextInt()) {
            case 1:
                viewContactByCity();//calling method
                break;
            case 2:
                viewContactByState();//calling method
                break;
            default:
                viewContacts();//calling method
                break;
        }
    }

    //view contact by city
    void viewContactByCity() {
        System.out.println("Enter City:");
        String city = sc.next();
        for (String key : cityContactList.keySet()) {//returns a set view of the keys contained in map
            if (key.equalsIgnoreCase(city)) {
                cityContactList.get(key).stream().forEach(person -> System.out.println(person));
            }
        }
    }

    //view contact by state
    void viewContactByState() {
        System.out.println("Enter State:");
        String state = sc.next();
        for (String key : stateContactList.keySet()) {//returns a set view of the keys contained in map
            if (key.equalsIgnoreCase(state)) {
                stateContactList.get(state).stream().forEach(person -> System.out.println(person));
            }
        }
    }

    //Get number of contact persons
    void showContactCount() {
        System.out.println("1.Count of City \n2.Count of State");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                System.out.println("Enter city :");
                String city = sc.next();
                System.out.println("Count : " + cityContactList.get(city).size());//size of city
                break;
            case 2:
                System.out.println("Enter State :");
                String state = sc.next();
                System.out.println("Count : " + stateContactList.get(state).size());//size of state
                break;
            default:
                showContactCount();
                break;
        }
    }

    /*
    sort contact
    sort entries in address book alphabetically by person's name, city, state, zipCode
     */
    void sortContact() {
        List<ContactPerson> allContacts = getAllContacts();
        List<ContactPerson> sortedContacts;

        System.out.println("1.Sort By Name \n2.Sort By City \n3.Sort By State \n4.Sort By Zipcode \n5.back");
        switch (sc.nextInt()) {
            case 1:
                sortedContacts = allContacts.stream().sorted((x, y) -> x.getFirstName().compareTo(y.getFirstName())).collect(Collectors.toList());
                sortedContacts.forEach(x -> System.out.println(x));
                break;
            case 2:
                sortedContacts = allContacts.stream().sorted((x, y) -> x.getCity().compareTo(y.getCity())).collect(Collectors.toList());
                sortedContacts.forEach(x -> System.out.println(x));
                break;
            case 3:
                sortedContacts = allContacts.stream().sorted((x, y) -> x.getState().compareTo(y.getState())).collect(Collectors.toList());
                sortedContacts.forEach(x -> System.out.println(x));
                break;
            case 4:
                sortedContacts = allContacts.stream().sorted((x, y) -> Integer.compare(x.getZipCode(), y.getZipCode())).collect(Collectors.toList());
                sortedContacts.forEach(x -> System.out.println(x));
                break;
            case 5:
                break;
            default:
                sortContact();
                break;
        }
    }

    List<ContactPerson> getAllContacts() {
        List<ContactPerson> allContacts = new ArrayList<>();//create object of list
        for (String key : addressBookList.keySet()) {//iterate loop
            allContacts.addAll(addressBookList.get(key));
        }
        return allContacts;
    }

    //writeData method
    void writeData() {
        FileIOServices fileIOService = new FileIOServices();//creating an object of FileIOServices class
        fileIOService.writeData();//using object reference calling writeData method
    }

    //readData method
    void readData() {
        FileIOServices fileIOService = new FileIOServices();//creating an object of FileIOServices class
        fileIOService.readData();//using object reference calling writeData method
    }

    //write csv file
    public void writeCSVFile(){
        try {
            FileWriter fileWriterCSV = new FileWriter(CSVFile);//creating file writer object and pass file path variable
            fileWriterCSV.write(String.valueOf(AddressBook.addressBookList));
            fileWriterCSV.close();//close file writer
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    //read csv file
    public void CSVFileReader() {
        try (Reader reader = Files.newBufferedReader(Paths.get(String.valueOf(CSVFile)));
             CSVReader csvReader = new CSVReader(reader)) {
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                System.out.println("First Name: " + nextRecord[0]);
                System.out.println("Last Name: " + nextRecord[1]);
                System.out.println("Address: " + nextRecord[2]);
                System.out.println("City: " + nextRecord[3]);
                System.out.println("State: " + nextRecord[4]);
                System.out.println("Pin Code: " + nextRecord[5]);
                System.out.println("Mobile Number: " + nextRecord[6]);
                System.out.println("Email Address: " + nextRecord[7]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}