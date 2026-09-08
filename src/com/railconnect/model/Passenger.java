package com.railconnect.model;

public class Passenger {

    private int passengerId;

    private User user;

    private String firstName;

    private String lastName;

    private int age;

    private String gender;

    private String passengerType;

    private String idProofType;

    private String idProofNumber;

    public Passenger() {
    }

    public Passenger(int passengerId,
                     User user,
                     String firstName,
                     String lastName,
                     int age,
                     String gender,
                     String passengerType,
                     String idProofType,
                     String idProofNumber) {

        this.passengerId = passengerId;
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.passengerType = passengerType;
        this.idProofType = idProofType;
        this.idProofNumber = idProofNumber;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }

    public String getIdProofType() {
        return idProofType;
    }

    public void setIdProofType(String idProofType) {
        this.idProofType = idProofType;
    }

    public String getIdProofNumber() {
        return idProofNumber;
    }

    public void setIdProofNumber(String idProofNumber) {
        this.idProofNumber = idProofNumber;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerId=" + passengerId +
                ", user=" + user +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", passengerType='" + passengerType + '\'' +
                ", idProofType='" + idProofType + '\'' +
                ", idProofNumber='" + idProofNumber + '\'' +
                '}';
    }
}