package com.bridgelabz;

import java.io.IOException;
import java.util.Scanner;

public class AddressBookMain {

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Address Book program");//display welcome message
        AddressBook addressBook = new AddressBook();//creating object of address book
        Scanner sc = new Scanner(System.in);//create an object of scanner class

        boolean flag1 = true;
        while (flag1) {
            System.out.println("*************\n"+addressBook.addressBookList.keySet());
            System.out.println("current AddressBook Name: "+addressBook.currentAddressBookName);
            System.out.println("************\nSelect Option :\n1.Add Contact\n2.Edit Contact\n3.Delete Contact\n4.View contacts\n5.Add New Address Book\n6.Select Address Book\n7.Search contact\n8.Show contact count\n9.Sort Contact\n10.Write Data\n11.Read Data\n12.Writ CSV file\n13.CSV File Reader\n14.Exit");
            int option = sc.nextInt();
            switch (option) { //select option
                case 1:
                    ContactPerson contactPerson = addressBook.createContact();
                    addressBook.addContact(contactPerson);
                    break;
                case 2:
                    addressBook.editContact();
                    break;
                case 3:
                    addressBook.deleteContact();
                    break;
                case 4:
                    addressBook.viewContacts();
                    break;
                case 5:
                    addressBook.addNewAddressBook();
                    break;
                case 6:
                    addressBook.selectAddressBook();
                    break;
                case 7:
                    addressBook.searchContact();
                    break;
                case 8:
                    addressBook.showContactCount();
                    break;
                case 9:
                    addressBook.sortContact();
                    break;
                case 10:
                    addressBook.writeData();
                    break;
                case 11:
                    addressBook.readData();
                    break;
                case 12:
                    addressBook.writeCSVFile();
                    break;
                case 13:
                    addressBook.CSVFileReader();
                    break;
                case 14:
                    flag1 = false;
                    break;
                default:
                    System.out.println(option + " is not valid option");
                    break;
            }
        }
    }
}