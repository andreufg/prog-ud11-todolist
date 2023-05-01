package es.progcipfpbatoi.ud12.examples.example1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Example1 {

    public static void main(String[] args) {

        try {
            String dbURL = "jdbc:mysql://localhost/tasks_db?serverTimezone=UTC &allowPublicKeyRetrieval=true";
            Connection con = DriverManager.getConnection(dbURL, "root", "123456");
            System.out.println("La conexión se ha establecido con éxito");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

