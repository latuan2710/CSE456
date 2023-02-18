/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.entity;

/**
 *
 * @author Nhân
 */
public class User {

    private int userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userAddress;
    private int userPhonenumber;
    private int userBalance;
    private int savingBalance;

    public User() {
    }

    public User(String userName, String userPassword, String userEmail, String userAddress, long userBalance, int userPhonenumber) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userPhonenumber = userPhonenumber;
    }

    public User(int userId, String userName, String userPassword, String userEmail, String userAddress, int userPhonenumber) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userPhonenumber = userPhonenumber;

    }

    public User(int userId, String userName, String userPassword, String userEmail, String userAddress, int userPhonenumber, int userBalance, int savingBalance) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userPhonenumber = userPhonenumber;
        this.userBalance = userBalance;
        this.savingBalance = savingBalance;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setUserPhonenumber(int userPhonenumber) {
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

    public int getUserPhonenumber() {
        return userPhonenumber;
    }

    public void setUserBalance(int userBalance) {
        this.userBalance = userBalance;
    }

    public int getUserBalance() {
        return userBalance;
    }

    public int getSavingBalance() {
        return savingBalance;
    }

    public void setSavingBalance(int savingBalance) {
        this.savingBalance = savingBalance;
    }

}
