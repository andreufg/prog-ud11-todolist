package es.progcipfpbatoi.ud12.examples.example2;

import es.progcipfpbatoi.services.MySqlConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Example2 {

    public static void main(String[] args) {
        MySqlConnection mySqlConnection = new MySqlConnection( "localhost", "tasks_db", "root", "123456");

        String sql = "SELECT * FROM tareas WHERE categoria IN (2,3)";
        Connection connection = mySqlConnection.getConnection();

        try (
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);
        )
        {
            while (rs.next()) {
                System.out.println("------------------------");
                System.out.println("Descripción: " + rs.getString("descripcion"));
                System.out.println("Finalizada: " + rs.getInt("finalizada"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

