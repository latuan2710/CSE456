/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.entity;

/**
 *
 * @author Nhân
 */
public class Transaction {

    private int userId=0;
    private int addMoney=0;
    private int withdrawMoney=0;
    private int transferMoney=0;
    private int tradeID=0;
    private String time="";
    private int curBalance=0;

    public Transaction(int userId, int addMoney, int withdrawMoney, int transferMoney, int tradeID, String time, int curBalance) {
        this.userId = userId;
        this.addMoney = addMoney;
        this.withdrawMoney = withdrawMoney;
        this.transferMoney = transferMoney;
        this.tradeID = tradeID;
        this.time = time;
        this.curBalance = curBalance;
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

    public int getTransferMoney() {
        return transferMoney;
    }

    public void setTransferMoney(int transferMoney) {
        this.transferMoney = transferMoney;
    }

    public int getTradeID() {
        return tradeID;
    }

    public void setTradeID(int tradeID) {
        this.tradeID = tradeID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCurBalance() {
        return curBalance;
    }

    public void setCurBalance(int curBalance) {
        this.curBalance = curBalance;
    }

    @Override
    public String toString() {
        if(addMoney!=0){
           return "you deposit "+addMoney+" in your account at "+time; 
        }
        if(withdrawMoney!=0){
           return "you withdraw "+withdrawMoney+" in your account at "+time; 
        }
        if(transferMoney<0){
           return "you transfer "+Math.abs(transferMoney)+" to "+tradeID+" at "+time; 
        }
        if(transferMoney>0){
           return "you receive "+Math.abs(transferMoney)+" from "+tradeID+" at "+time; 
        }
        return "";
    }
}
