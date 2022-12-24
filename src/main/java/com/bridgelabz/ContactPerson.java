package com.bridgelabz;

public class ContactPerson implements Comparable {

    private String firstName,lastName,address,city,state,email;
    private int zipCode;
    private long phoneNumber;

    public ContactPerson() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState(){
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", email='" + email + '\'' +
                ", zip=" + zipCode +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        ContactPerson person = (ContactPerson) o;
        return this.getFirstName().compareTo(person.getFirstName());
    }
}