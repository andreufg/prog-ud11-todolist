package es.progcipfpbatoi.ud12.actividad1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Actividad1 {
    public static void main(String[] args) {

        try {
            String dbURL = "jdbc:mysql://localhost/tasks_db";
            Connection con = DriverManager.getConnection(dbURL, "root", "123456");
            System.out.println("La conexión se ha establecido con éxito");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
