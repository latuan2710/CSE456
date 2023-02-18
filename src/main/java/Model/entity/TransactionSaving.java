/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.entity;

/**
 *
 * @author Nhân
 */
public class TransactionSaving {

    private int userId = 0;
    private int addMoney = 0;
    private int withdrawMoney = 0;
    private String time = "";

    public TransactionSaving(int userID, int addMoney, int withdrawMoney, String time) {
        this.userId = userID;
        this.addMoney = addMoney;
        this.withdrawMoney = withdrawMoney;
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAddMoney() {
        return addMoney;
    }

    public void setAddMoney(int addMoney) {
        this.addMoney = addMoney;
    }

    public int getWithdrawMoney() {
        return withdrawMoney;
    }

    public void setWithdrawMoney(int withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        if (addMoney != 0) {
            return "you deposit " + addMoney + " in your saving account at " + time;
        }
        if (withdrawMoney != 0) {
            return "you withdraw " + withdrawMoney + " in your saving account at " + time;
        }
        return "";
    }
}
