/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAO;

import Model.connect.DBconnect;
import Model.entity.Transaction;
import Model.entity.TransactionSaving;
import Model.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Nhân
 */
public class userDAO {

    private String INSERT_USER = "INSERT INTO user_auth" + "(userId,userName,userPassword,userEmail,userAddress,userPhone,Balance,savingBalance) VALUES" + "(?,?,?,?,?,?,?,?);";
    private String SELECT_USER_BY_ID = "SELECT * FROM user_auth where userId='?'";

    private String SELECT_HISTORY_TRADE = "SELECT userId, addMoney, withdrawMoney, transferMoney, tradeID, time, curBalance FROM current_acc where userId =?;";
    private String SELECT_HISTORY_SAVING = "SELECT userId, addMoney, withdrawMoney, time FROM saving_acc where userId =?;";
    
    private String SELECT_ALL_USERS = "SELECT * FROM user_auth;";

    private String DEPOSIT = "UPDATE user_auth SET Balance = Balance + ? WHERE userId = ?";
    private String WITHDRAW = "UPDATE user_auth SET Balance = Balance - ? WHERE userId = ?";
    
    private String DEPOSIT_SAVING = "UPDATE user_auth SET savingBalance = savingBalance + ? , Balance = Balance - ? WHERE userId = ?";
    private String WITHDRAW_SAVING = "UPDATE user_auth SET savingBalance = savingBalance - ?, Balance = Balance + ? WHERE userId = ?";

    private String CHECK_BALANCE = "SELECT Balance FROM user_auth where userId = ? ";
    private String CHECK_SAVING = "SELECT savingBalance FROM user_auth where userId = ? ";

    private String ADD_HISTORY = "INSERT INTO current_acc (userId, addMoney,withdrawMoney,transferMoney,tradeID, time, curBalance) VALUES (?,?,?,?,?,?,?);";
    private String ADD_SAVING_HISTORY = "INSERT INTO saving_acc (userId, addMoney,withdrawMoney,time) VALUES (?,?,?,?);";
    
    private String TRANSFER = "INSERT INTO current_acc (userId, curBalance) VALUES (?,?);";

    public boolean insertUser(User user) {
        try {
            Connection connection = new DBconnect().getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_USER);

            ps.setInt(1, user.getUserId());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getUserPassword());
            ps.setString(4, user.getUserEmail());
            ps.setString(5, user.getUserAddress());
            ps.setInt(6, user.getUserPhonenumber());
            ps.setInt(7, user.getUserBalance());
            ps.setInt(8, user.getSavingBalance());
            
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public ArrayList<User> selectAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            Connection connection = new DBconnect().getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_USERS);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("userId");
                String userName = rs.getString("userName");
                String userPassword = rs.getString("userPassword");
                String userEmail = rs.getString("userEmail");
                String userAddress = rs.getString("userAddress");
                int userPhone = rs.getInt("userPhone");

                users.add(new User(userId, userName, userPassword, userEmail, userAddress, userPhone
                ));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public int checkBalance(int id) {
        int checkBalance = 0;
        try {
            Connection connection = new DBconnect().getConnection();
            PreparedStatement ps = connection.prepareCall(CHECK_BALANCE);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                checkBalance = rs.getInt("Balance");
            }

        } catch (Exception e) {
        }
        return checkBalance;
    }
    
    public int checkSavingBalance(int id) {
        int checkSaving = 0;
        try {
            Connection connection = new DBconnect().getConnection();
            PreparedStatement ps = connection.prepareCall(CHECK_SAVING);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                checkSaving = rs.getInt("savingBalance");
            }

        } catch (Exception e) {
        }
        return checkSaving;
    }

    public boolean withdraw(int id, int money) {
        try {
            Connection connection = new DBconnect().getConnection();
            PreparedStatement ps = connection.prepareStatement(WITHDRAW);

            ps.setInt(2, id);
            ps.setInt(1, money);
            ps.executeUpdate();

            ps = connection.prepareStatement(ADD_HISTORY);
            ps.setInt(1, id);
            ps.setInt(2, 0);
            ps.setInt(3, money);
            ps.setInt(4, 0);
            ps.setInt(5, 0);
            ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            ps.setInt(7, this.checkBalance(id));
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    
    public boolean withdrawSaving(int id, int money) {
        try {
            Connection connection = new DBconnect().getConnection();
            PreparedStatement ps = connection.prepareStatement(WITHDRAW_SAVING);

            ps.setInt(1, money);
            ps.setInt(2, money);
            ps.setInt(3, id);
            ps.executeUpdate();

            ps = connection.prepareStatement(ADD_SAVING_HISTORY);
            ps.setInt(1, id);
            ps.setInt(2, 0);
            ps.setInt(3, money);
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean depositToCur(int id, int money) {
        try {
            Connection connection = new DBconnect().getConnection();
            PreparedStatement ps = connection.prepareStatement(DEPOSIT);

            ps.setInt(1, money);
            ps.setInt(2, id);
            ps.executeUpdate();

            ps = connection.prepareStatement(ADD_HISTORY);
            ps.setInt(1, id);
            ps.setInt(2, money);
            ps.setInt(3, 0);
            ps.setInt(4, 0);
            ps.setInt(5, 0);
            ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            ps.setInt(7, this.checkBalance(id));
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    
    public boolean depositSaving(int id, int money) {
        try {
            Connection connection = new DBconnect().getConnection();
            PreparedStatement ps = connection.prepareStatement(DEPOSIT_SAVING);

            ps.setInt(1, money);
            ps.setInt(2, money);
            ps.setInt(3, id);
            ps.executeUpdate();

            ps = connection.prepareStatement(ADD_SAVING_HISTORY);
            ps.setInt(1, id);
            ps.setInt(2, money);
            ps.setInt(3, 0);
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean transfer(int id, int anotherid, int money) {

        try {
            Connection connection = new DBconnect().getConnection();
            PreparedStatement ps;

            ps = connection.prepareStatement(DEPOSIT);
            ps.setInt(1, money);
            ps.setInt(2, anotherid);
            ps.executeUpdate();

            ps = connection.prepareStatement(ADD_HISTORY);
            ps.setInt(1, anotherid);
            ps.setInt(2, 0);
            ps.setInt(3, 0);
            ps.setInt(4, money);
            ps.setInt(5, id);
            ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            ps.setInt(7, this.checkBalance(anotherid));
            ps.executeUpdate();

            ps = connection.prepareStatement(WITHDRAW);
            ps.setInt(2, id);
            ps.setInt(1, money);
            ps.executeUpdate();

            ps = connection.prepareStatement(ADD_HISTORY);
            ps.setInt(1, id);
            ps.setInt(2, 0);
            ps.setInt(3, 0);
            ps.setInt(4, -money);
            ps.setInt(5, anotherid);
            ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            ps.setInt(7, this.checkBalance(id));
            ps.executeUpdate();

            System.out.println("transfer ok");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    public ArrayList showHistory(int id) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            Connection connection = new DBconnect().getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_HISTORY_TRADE);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("userId");
                int addMoney = rs.getInt("addMoney");
                int withdrawMoney = rs.getInt("withdrawMoney");
                int transferMoney = rs.getInt("transferMoney");
                int tradeID = rs.getInt("tradeID");
                String time = rs.getString("time");
                int curBalance = rs.getInt("curBalance");

                transactions.add(new Transaction(userId, addMoney, withdrawMoney, transferMoney, tradeID, time, curBalance));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return transactions;
    }
    public ArrayList showHistorySaving(int id) {
        ArrayList<TransactionSaving> transactions = new ArrayList<>();
        try {
            Connection connection = new DBconnect().getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_HISTORY_SAVING);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("userId");
                int addMoney = rs.getInt("addMoney");
                int withdrawMoney = rs.getInt("withdrawMoney");
                String time = rs.getString("time");

                transactions.add(new TransactionSaving(userId, addMoney, withdrawMoney, time));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return transactions;
    }
}
