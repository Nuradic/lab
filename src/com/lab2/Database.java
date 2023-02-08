package com.lab2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static String url = "jdbc:mysql://localhost:3306/academy";
    private static String username = "academy";
    private static String password = "pass123456";

    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(url, username, password);
        return con;

    }

    public static Boolean insertEmploye(Employe emp) throws SQLException {
        String s = "INSERT INTO EMPLOYE(name,email,password,position)VALUES(?,?,?,?)";

        Connection con = getConnection();

        PreparedStatement stm = con.prepareStatement(s);
        stm.setString(1, emp.getName());
        stm.setString(2, emp.getEmail());
        stm.setString(3, emp.getPassword());
        stm.setString(4, emp.getPosition());

        return stm.execute();

    }

    public static Employe getEmploye(String email) throws SQLException {

        Connection con = getConnection();

        String s = "SELECT* FROM EMPLOYE WHERE email=" + "'" + email + "'";

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(s);

        if (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String pass = rs.getString("password");
            String position = rs.getString("position");

            Employe e = new Employe(name, email);

            e.setId(id);
            e.setPassword(pass);
            e.setPosition(position);
            return e;

        }

        return null;
    }

}
