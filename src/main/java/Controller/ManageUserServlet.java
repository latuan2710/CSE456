/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.entity.Transaction;
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
public class ManageUserServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {

            String target = "";
            UserManager myUserManager = new UserManager();
            HttpSession mySession = request.getSession();
            int iUserId = (int) mySession.getAttribute("userId");

            String mode = request.getParameter("mode");
            System.out.println(mode);

            if (mode.equals("list")) {
                System.out.println("check list");
                ArrayList<Transaction> transactions = myUserManager.getlistTransactions(iUserId);
                mySession.setAttribute("listTransactions", transactions);
                mySession.setAttribute("displayTransaction", "block");
                target = "homePage.jsp";
            }

            

            if (mode.equals("withdraw")) {
                int iUserMoney = Integer.valueOf(request.getParameter("userMoney"));
                myUserManager.withdraw(iUserId, iUserMoney);
                System.out.println("rut tien thanh cong");
                target = "homePage.jsp";
            }

            if (mode.equals("depositToCur")) {
                int iUserMoney = Integer.valueOf(request.getParameter("userMoney"));
                myUserManager.depositToCur(iUserId, iUserMoney);
                System.out.println("nap tien vao CUR thanh cong");
//                mySession.setAttribute("userBalance", myUserManager.checkBalance(iUserId));
                target = "homePage.jsp";
            }

            if (mode.equals("transfer")) {
                int iUserMoney = Integer.valueOf(request.getParameter("userMoney"));
                int receiverId = Integer.valueOf(request.getParameter("receiverId"));
                if (myUserManager.checkUserId(receiverId) == false) {
                    System.out.println("Receiver's ID not valid");
                    target = "homePage.jsp";
                } else {
                    myUserManager.transfer(iUserId, iUserMoney, receiverId);
                    System.out.println("chuyen tien thanh cong");
//                mySession.setAttribute("userBalance", myUserManager.checkBalance(iUserId));
                    target = "homePage.jsp";
                }
            }

            mySession.setAttribute("userBalance", myUserManager.checkBalance(iUserId));
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
