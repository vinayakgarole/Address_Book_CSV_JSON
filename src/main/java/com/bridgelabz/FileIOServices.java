package com.bridgelabz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOServices {

    //Path of the file
    private static final String FILE_PATH = "C:\\Users\\Infinty system\\IdeaProjects\\Address_Book_CSV_JSON\\src\\main\\resources\\contact.txt";

    //Write data method
    public void writeData() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            fileWriter.write(String.valueOf(AddressBook.addressBookList));
            fileWriter.close();//close the writer

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Read data method
    public void readData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String store;
            while ((store = reader.readLine()) != null) {
                System.out.println(store);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}