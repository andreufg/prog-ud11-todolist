package es.progcipfpbatoi.ud12.examples.example3;

import es.progcipfpbatoi.services.MySqlConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Example3 {

    public static void main(String[] args) {
        MySqlConnection mySqlConnection = new MySqlConnection("localhost", "tasks_db", "root", "123456");

        String sql = "INSERT INTO categorias (tipo, prioridad) VALUES ('CLASE', 'MUY ALTA')";
        Connection connection = mySqlConnection.getConnection();

        try ( Statement statement = connection.createStatement())
        {
            int affectedRows = statement.executeUpdate(sql);
            System.out.println("Filas modificadas: " + affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

