/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAO;

import Model.connect.DBconnect;
import Model.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Nhân
 */
public class userDAO {

    private String INSERT_USER = "INSERT INTO user_auth" + "(userId,userName,userPassword,userEmail,userAddress,userPhone) VALUES" + "(?,?,?,?,?,?);";
    private String SELECT_USER_BY_ID = "SELECT * FROM user_auth where userId='?'";
    private String SELECT_ALL_USERS = "SELECT * FROM user_auth;";
    private String SEND_CUR_MONEY = "UPDATE current_acc SET curBalance = curBalance + ? WHERE userId = ?";
    private String WITHDRAW_CUR_MONEY = "UPDATE current_acc SET curBalance = curBalance - ? WHERE userId = ?";
    private String CHECK_BALANCE = "SELECT curBalance FROM current_acc where userId = ? ";

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
                checkBalance = rs.getInt("curBalance");
            }

        } catch (Exception e) {
        }
        return checkBalance;
    }

    public boolean withdraw(int id, int money) {
        try {
            Connection connection = new DBconnect().getConnection();
            PreparedStatement ps = connection.prepareStatement(WITHDRAW_CUR_MONEY);

            ps.setInt(2, id);
            ps.setInt(1, money);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean depositToCur(int id, int money) {
        try {
            Connection connection = new DBconnect().getConnection();
            PreparedStatement ps = connection.prepareStatement(SEND_CUR_MONEY);

            ps.setInt(2, id);
            ps.setInt(1, money);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
