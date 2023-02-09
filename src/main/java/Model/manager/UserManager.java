/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.manager;

import Model.DAO.userDAO;
import Model.entity.User;
import java.util.ArrayList;

/**
 *
 * @author Nhân
 */
public class UserManager {

    private ArrayList<User> listUser;
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
}
