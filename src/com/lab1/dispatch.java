package com.lab1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class dispatch extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        PrintWriter out = res.getWriter();

        res.setContentType("text/html");

        out.println("<h1>Working just fine</h1>");

    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();

        String name = req.getParameter("name");
        String dep = req.getParameter("dep");
        String position = req.getParameter("pos");
        String age = req.getParameter("age");

        // String q = """
        // <html>
        // <script>Alert("Hello World")</script>

        // """;

        out.println("<body><h1>Your name is " + name);
        out.println("<br>Your age is : " + age);
        out.println("<br>Your department is : " + dep);
        out.println("<br>Your position is : " + position);

        out.println("</h1></body></html");
    }

}
