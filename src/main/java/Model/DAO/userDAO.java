/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAO;

import Model.connect.DBconnect;
import Model.entity.Transaction;
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

    private String INSERT_USER = "INSERT INTO user_auth" + "(userId,userName,userPassword,userEmail,userAddress,userPhone,Balance) VALUES" + "(?,?,?,?,?,?,?);";
    private String SELECT_USER_BY_ID = "SELECT * FROM user_auth where userId='?'";

    private String SELECT_HISTORY_DEPOSIT = "SELECT userId, addMoney FROM current_acc where userId =?;";
    private String SELECT_HISTORY_WITHDRAW = "SELECT userId, withdrawMoney FROM current_acc where userId =?;";

    private String SELECT_ALL_USERS = "SELECT * FROM user_auth;";

    private String DEPOSIT = "UPDATE user_auth SET Balance = Balance + ? WHERE userId = ?";
    private String WITHDRAW = "UPDATE user_auth SET Balance = Balance - ? WHERE userId = ?";

    private String CHECK_BALANCE = "SELECT Balance FROM user_auth where userId = ? ";

    private String FIRST_DEPOSIT = "INSERT INTO current_acc (userId,addMoney,withdrawMoney,curBalance) VALUES (?,?,?,?);";
    private String ADD_HISTORY_DEPOSIT = "INSERT INTO current_acc (userId, addMoney, time, curBalance) VALUES (?,?,?,?);";
    private String ADD_HISTORY_WITHDRAW = "INSERT INTO current_acc (userId, withdrawMoney,time, curBalance) VALUES (?,?,?,?);";

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

    public boolean withdraw(int id, int money) {
        try {
            Connection connection = new DBconnect().getConnection();
            PreparedStatement ps = connection.prepareStatement(WITHDRAW);

            ps.setInt(2, id);
            ps.setInt(1, money);
            ps.executeUpdate();

            ps = connection.prepareStatement(ADD_HISTORY_WITHDRAW);
            ps.setInt(1, id);
            ps.setInt(2, money);
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            ps.setInt(4, this.checkBalance(id));
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

            ps = connection.prepareStatement(ADD_HISTORY_DEPOSIT);
            ps.setInt(1, id);
            ps.setInt(2, money);
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            ps.setInt(4, this.checkBalance(id));
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public ArrayList showHistoryDeposit(int id) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            Connection connection = new DBconnect().getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_HISTORY_DEPOSIT);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("userId");
                int money = rs.getInt("addMoney");

                transactions.add(new Transaction(userId, money));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return transactions;
    }
}
