package ru.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

    public static void main(String[] args) {
        final String jdbcUrlStudentTracker = "hb_student_tracker";
        final String jdbcUrlOneToOne = "hb-01-one-to-one-uni";

        String jdbcUrl = "jdbc:mysql://localhost:3306/" + jdbcUrlOneToOne + "?useSSL=false&serverTimezone=UTC";
        String user = "hbstudent";
        String pass = "hbstudent";

        try {
            System.out.println("Connecting to database: " + jdbcUrl);

            Connection conn = DriverManager.getConnection(jdbcUrl, user, pass);

            System.out.println("Connection successful!");

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
