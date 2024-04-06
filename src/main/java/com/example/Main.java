package com.example;
import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Connection connection = DatabaseConnection.getInstance();

        BankAccountDAO bankAccountDAO = new BankAccountDAO();
        bankAccountDAO.createTable();

        // Insert some new records
        bankAccountDAO.insert(new BankAccount("John", "Doe", 10000));
        bankAccountDAO.insert(new BankAccount("Jane", "Doe", 20000));
        bankAccountDAO.insert(new BankAccount("Alice", "Smith", 30000));

        // Retrieve all records
        List<BankAccount> accounts = bankAccountDAO.getAll();
        for (BankAccount acc : accounts) {
            System.out.println(acc);
        }

        // Retrieve a record by ID
        BankAccount account = bankAccountDAO.getById(2);
        System.out.println("Before update:");
        System.out.println(account);

        // Update a record
        account.setBankBalance(25000);
        bankAccountDAO.update(account);
        System.out.println("After update balance to 25000:");
        System.out.println(bankAccountDAO.getById(2));

        // Delete a record
        System.out.println("Before deleting record with id = 1:");
        for (BankAccount acc : bankAccountDAO.getAll()) {
            System.out.println(acc);
        }

        bankAccountDAO.delete(1);
        System.out.println("After deleting record with id = 1:");
        for (BankAccount acc : bankAccountDAO.getAll()) {
            System.out.println(acc);
        }

        bankAccountDAO.close();
    }
}