package com.lab2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher dis = req.getRequestDispatcher("/login.html");
        dis.forward(req, res);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            System.out.println("trying my best");
            HttpServletResponse r2 = Authentication.authenticate(req, res);
            System.out.println(r2);
            if (r2 == null) {
                RequestDispatcher dis = req.getRequestDispatcher("/login.html");
                out.println("<h1> Wrong Credentials!</h1>");
                dis.include(req, r2);
            } else {
                Employe em = Database.getEmploye(req.getParameter("email"));
                String s = """
                        <h1>
                        name:getn <br>
                        email:gete <br>
                        position: getp<br>

                        </h1>
                        """;
                s = s.replace("getn", em.getName()).replace("gete", em.getEmail()).replace("getp", em.getPosition());

                out.println(s);

            }
        } catch (SQLException e) {
            out.print("<h1>Something went wrong</h1>");
            e.printStackTrace();
        }

    }

}
