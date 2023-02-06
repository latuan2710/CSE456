/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Nhân
 */
public class userInfor {
    private int userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userAddress;
    private boolean userBalance;
    private int userPhonenumber;

    public userInfor(int userId, String userName, String userPassword, String userEmail, String userAddress, boolean userBalance, int userPhonenumber) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userBalance = userBalance;
        this.userPhonenumber = userPhonenumber;
    }

    public userInfor(String userName, String userPassword, String userEmail, String userAddress, boolean userBalance, int userPhonenumber) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userBalance = userBalance;
        this.userPhonenumber = userPhonenumber;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public boolean isUserBalance() {
        return userBalance;
    }

    public int getUserPhonenumber() {
        return userPhonenumber;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setUserBalance(boolean userBalance) {
        this.userBalance = userBalance;
    }

    public void setUserPhonenumber(int userPhonenumber) {
        this.userPhonenumber = userPhonenumber;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
}

   
