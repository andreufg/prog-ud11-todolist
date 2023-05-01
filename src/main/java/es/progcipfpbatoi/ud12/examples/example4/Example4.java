package es.progcipfpbatoi.ud12.examples.example4;

import es.progcipfpbatoi.services.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Example4 {
    public static void main(String[] args) {

        MySqlConnection mySqlConnection = new MySqlConnection( "localhost", "tasks_db", "root", "123456");
        String sql = "INSERT INTO categorias (tipo, prioridad) VALUES (?,?)";
        Connection connection = mySqlConnection.getConnection();
        try ( PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1 , "JUGAR");
            preparedStatement.setString (2, "NORMAL");
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("Affected Rows: " + affectedRows);

            preparedStatement.setString(1, "HOGAR");
            preparedStatement.setString (2, "NORMAL");
            affectedRows = preparedStatement.executeUpdate();
            System.out.println("Affected Rows: " + affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

