package com.example;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDAO {
    private Connection connection;

    public BankAccountDAO() {
        connection = DatabaseConnection.getInstance();
    }

    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS bankAccounts ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "firstName VARCHAR NOT NULL, "
                    + "lastName VARCHAR NOT NULL, "
                    + "bankBalance INTEGER NOT NULL"
                    + ")"
            );
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void insert(BankAccount bankAccount) {
        try {
            PreparedStatement insertAccount = connection.prepareStatement(
                    "INSERT INTO bankAccounts (firstName, lastName, bankBalance) VALUES (?, ?, ?)"
            );
            insertAccount.setString(1, bankAccount.getFirstName());
            insertAccount.setString(2, bankAccount.getLastName());
            insertAccount.setInt(3, bankAccount.getBankBalance());
            insertAccount.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void update(BankAccount bankAccount) {
        try {
            PreparedStatement updateAccount = connection.prepareStatement(
                    "UPDATE bankAccounts SET firstName = ?, lastName = ?, bankBalance = ? WHERE id = ?"
            );
            updateAccount.setString(1, bankAccount.getFirstName());
            updateAccount.setString(2, bankAccount.getLastName());
            updateAccount.setInt(3, bankAccount.getBankBalance());
            updateAccount.setInt(4, bankAccount.getId());
            updateAccount.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void delete(int id) {
        try{
            PreparedStatement deleteAccount = connection.prepareStatement("DELETE FROM bankAccounts WHERE id = ?");
            deleteAccount.setInt(1, id);
            deleteAccount.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<BankAccount> getAll() {
        List<BankAccount> accounts = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM bankAccounts");
            while (rs.next()) {
                accounts.add(
                        new BankAccount(
                                rs.getInt("id"),
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getInt("bankBalance")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return accounts;
    }

    public BankAccount getById(int id) {
        try{
            PreparedStatement getAccount = connection.prepareStatement("SELECT * FROM bankAccounts WHERE id = ?");
            getAccount.setInt(1, id);
            ResultSet rs = getAccount.executeQuery();
            if (rs.next()) {
                return new BankAccount(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("bankBalance")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
