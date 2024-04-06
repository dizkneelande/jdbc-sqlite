package com.example;

public class BankAccount {
    private int id;
    private String firstName;
    private String lastName;
    private int bankBalance;

    public BankAccount(int id, String firstName, String lastName, int bankBalance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bankBalance = bankBalance;
    }

    public BankAccount(String firstName, String lastName, int bankBalance) {
        // Since the id is auto-incremented, it is nice to have a constructor without it
        this.firstName = firstName;
        this.lastName = lastName;
        this.bankBalance = bankBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(int bankBalance) {
        this.bankBalance = bankBalance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bankBalance=" + bankBalance +
                '}';
    }
}
