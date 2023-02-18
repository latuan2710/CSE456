/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.manager;

import Model.DAO.userDAO;
import Model.entity.Transaction;
import Model.entity.User;
import java.util.ArrayList;

/**
 *
 * @author Nhân
 */
public class UserManager {

    private ArrayList<User> listUser;
    private ArrayList<Transaction> listTransaction;
    private userDAO myUserDAO;

    public UserManager() {
        myUserDAO = new userDAO();
    }

    public User getUserByID(int id) {
        User tempUser = null;
        if (listUser == null) {
            return tempUser;
        } else {
            for (int i = 0; i < listUser.size(); i++) {
                tempUser = listUser.get(i);
                if (tempUser.getUserId() == id) {
                    return tempUser;
                }
            }
        }
        return null;
    }

    public boolean addUser(User user) {
        boolean result = false;
        if (this.getUserByID(user.getUserId()) == null) {
            myUserDAO.insertUser(user);
            result = true;
        }
        return result;
    }

    public ArrayList getListUsers() {
        return this.listUser = myUserDAO.selectAllUsers();
    }

    public boolean checkUserId(int id) {
        ArrayList<User> listUser = this.getListUsers();
        for (int i = 0; i < listUser.size(); i++) {
            if (id == listUser.get(i).getUserId()) {
                return true;
            }
        }
        return false;
    }

    public int checkBalance(int id) {
        if (checkUserId(id) == true) {
            return myUserDAO.checkBalance(id);
        }
        return -1;
    }

    public int checkSavingBalance(int id) {
        if (checkUserId(id) == true) {
            return myUserDAO.checkSavingBalance(id);
        }
        return -1;
    }

    public boolean withdraw(int id, int money) {
        myUserDAO.withdraw(id, money);
        return true;

    }

    public boolean withdrawSaving(int id, int money) {
        myUserDAO.withdrawSaving(id, money);
        return true;

    }

    public boolean depositToCur(int id, int money) {
        myUserDAO.depositToCur(id, money);
        return true;

    }

    public boolean depositSaving(int id, int money) {
        myUserDAO.depositSaving(id, money);
        return true;

    }

    public boolean transfer(int id, int money, int anotherId) {
        System.out.println("check 1");
        if (checkUserId(id) == true) {
            myUserDAO.transfer(id, anotherId, money);
            return true;
        }
        return false;
    }

    public ArrayList getlistTransactions(int id) {
        return this.listTransaction = myUserDAO.showHistory(id);
    }

    public ArrayList getlistTransactionsSaving(int id) {
        return this.listTransaction = myUserDAO.showHistorySaving(id);
    }
}
