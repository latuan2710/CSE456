/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.entity.Transaction;
import Model.entity.User;
import Model.manager.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nhân
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String target = "";
        try ( PrintWriter out = response.getWriter()) {
            HttpSession mySession = request.getSession();
            String mode = "";

            mode = (String) request.getParameter("mode");
            System.out.println(mode);
            if (mode.equals("logOut")) {
                target = "index.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(target);
                rd.forward(request, response);
            }

            if (mode.equals("login")) {
                int iUserId = Integer.valueOf(request.getParameter("userId"));
                String iPassword = request.getParameter("userPassword");
                UserManager myUserManager = new UserManager();
                ArrayList<User> listUsers = myUserManager.getListUsers();

                for (int i = 0; i < listUsers.size(); i++) {
                    int userId = listUsers.get(i).getUserId();
                    String userPass = listUsers.get(i).getUserPassword();
                    if (iUserId == userId && iPassword.equals(userPass)) {
                        String name = listUsers.get(i).getUserName();
                        int money = myUserManager.checkBalance(iUserId);
                        mySession.setAttribute("userId", iUserId);
                        mySession.setAttribute("userName", name);
                        mySession.setAttribute("userBalance", money);
                        mySession.setAttribute("userId", iUserId);

                        System.out.println("Login Success");
                        target = "homePage.jsp";
                    }
                }
            }

            System.out.println(target);
            RequestDispatcher rd = request.getRequestDispatcher(target);
            rd.forward(request, response);

        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
