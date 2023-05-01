package es.progcipfpbatoi.ud12.actividad3;

import es.progcipfpbatoi.services.MySqlConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Actividad3 {
    private static void consulta1() {
        MySqlConnection service = new MySqlConnection(
                "localhost", "crm_db", "root", "123456");
        String sql = "SELECT firstName, email, phoneNumber FROM User";

        Connection connection = service.getConnection();

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        ) {

            while(resultSet.next()) {
                System.out.print(
                        resultSet.getString("firstName") + " " +
                                resultSet.getString("email") + " " +
                                resultSet.getString("phoneNumber"));
                System.out.println();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static void consulta2() {
        MySqlConnection service = new MySqlConnection(
                "localhost", "crm_db", "root", "123456");
        String sql = "SELECT name, address, city FROM Enterprise";

        Connection connection = service.getConnection();

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        ) {

            while(resultSet.next()) {
                System.out.print(
                        resultSet.getString("name") + " " +
                                resultSet.getString("address") + " " +
                                resultSet.getString("city"));
                System.out.println();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void consulta3() {
        MySqlConnection service = new MySqlConnection(
                "localhost", "crm_db", "root", "123456");
        String sql = "SELECT u.firstName, u.lastName, e.name FROM User u " +
                "INNER JOIN Enterprise e ON u.idEnterprise = e.id " +
                "WHERE e.id = 1 OR e.id = 2";

        Connection connection = service.getConnection();

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        ) {

            while(resultSet.next()) {
                System.out.print(
                        resultSet.getString("firstName") + " " +
                                resultSet.getString("lastName") + " " +
                                resultSet.getString("name"));
                System.out.println();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static void main(String[] args) {
        consulta1();
        consulta2();
        consulta3();
    }
}
