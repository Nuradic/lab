package com.lab2;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Authentication {

    public static HttpServletResponse authenticate(HttpServletRequest req, HttpServletResponse res)
            throws SQLException {
        HttpSession ses = req.getSession(true);
        ses.getAttribute("paasword");

        String email = req.getParameter("email");
        String pass = req.getParameter("password");

        if (email == null) {
            email = (String) ses.getAttribute("email");
        }
        if (pass == null) {
            pass = (String) ses.getAttribute("password");
        }

        Employe em = Database.getEmploye(email);

        if (em == null || !em.getPassword().equals(pass)) {
            return null;
        } else {
            ses.setAttribute("email", email);
            ses.setAttribute("password", pass);
            return res;
        }

    }

}
