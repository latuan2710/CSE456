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

    private int userId;
    private int money;

    public Transaction(int userId, int money) {
        this.userId = userId;
        this.money = money;
    }

    public int getUserId() {
        return userId;
    }

    public int getMoney() {
        return money;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setMoney(int money) {
        this.money = money;
    }

}
