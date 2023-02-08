
package com.lab2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            HttpServletResponse res2 = Authentication.authenticate(req, res);
            if (res2 == null) {
                RequestDispatcher dis = req.getRequestDispatcher("/index.html");
                dis.forward(req, res);

            } else {

                RequestDispatcher dis = req.getRequestDispatcher("/home.html");
                dis.forward(req, res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        String pos = req.getParameter("position");

        Employe em = new Employe(name, email);
        em.setPassword(pass);
        em.setPosition(pos);
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            Database.insertEmploye(em);
            out.println("<h1>Succesfully registered!</h1>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h1>Registration failed for unknown reason</h1>");
        }

    }

}
