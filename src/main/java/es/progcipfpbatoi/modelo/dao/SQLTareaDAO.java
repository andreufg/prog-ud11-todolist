package es.progcipfpbatoi.modelo.dao;

import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.exceptions.NotFoundException;
import es.progcipfpbatoi.modelo.dto.Categoria;
import es.progcipfpbatoi.modelo.dto.Tarea;
import es.progcipfpbatoi.services.MySqlConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SQLTareaDAO implements TareaDAO{

    private static final String TABLE_NAME = "tareas";

    @Override
    public ArrayList<Tarea> findAll() throws DatabaseErrorException {
        String sql = String.format("SELECT * FROM %s", TABLE_NAME);

        ArrayList<Tarea>tareas = new ArrayList<>();

        try (
                Connection connection =  new MySqlConnection("localhost", "tasks_db", "root", "123456").getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        ) {

            while(resultSet.next()) {
                Tarea tarea = getTaskFromResultset(resultSet);
                tareas.add(tarea);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ha ocurrido un error en la conexión o acceso a la base de datos (select)");
        }

        return tareas;
    }

    @Override
    public ArrayList<Tarea> findAll(String text) throws DatabaseErrorException {
        ArrayList<Tarea> tareasFiltradas = new ArrayList<>();
        for (Tarea tarea: findAll()) {
            if (tarea.empiezaPor(text)) {
                tareasFiltradas.add(tarea);
            }
        }

        return tareasFiltradas;
    }

    @Override
    public Tarea getById(int id) throws NotFoundException, DatabaseErrorException {
        String sql = String.format("SELECT * FROM %s WHERE id = ?",TABLE_NAME);

        try (
                Connection connection =  new MySqlConnection("localhost", "tasks_db", "root", "123456").getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Tarea tarea = getTaskFromResultset(resultSet);
                if (tarea.getId() == id) {
                    return tarea;
                }
            }

            throw new NotFoundException("No existe una tarea con el id " + id);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ha ocurrido un error en el acceso o conexión a la base de datos (select)");
        }
    }

    @Override
    public Tarea findById(int id) throws DatabaseErrorException {
        try {
            return getById(id);
        } catch (NotFoundException ex) {
            return null;
        }
    }

    @Override
    public Tarea save(Tarea tarea) throws DatabaseErrorException {
        if (findById(tarea.getId()) == null) {
            return insert(tarea);
        } else {
            return update(tarea);
        }
    }

    private Tarea insert(Tarea tarea) throws DatabaseErrorException {
        String sql = String.format("INSERT INTO %s (id, descripcion, fechaAlta, finalizada, categoria) VALUES (?,?,?,?,?)",
                TABLE_NAME);

        try (
                Connection connection =  new MySqlConnection("localhost", "tasks_db", "root", "123456").getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, tarea.getId());
            preparedStatement.setString(2, tarea.getDescripcion());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(tarea.getFechaAlta()));
            preparedStatement.setInt(4, tarea.isFinalizada()?1:0);
            preparedStatement.setInt(5, tarea.getCategoria().getId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                tarea.setId(resultSet.getInt(1));
            }

            return tarea;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ha ocurrido un error en el acceso o conexión a la base de datos (insert)");
        }
    }

    private Tarea update(Tarea tarea) throws DatabaseErrorException{
        String sql = String.format("UPDATE %s SET descripcion = ?, fechaAlta = ?, finalizada = ?, categoria = ? WHERE id = ?",
                TABLE_NAME);

        try (
                Connection connection =  new MySqlConnection("localhost", "tasks_db", "root", "123456").getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, tarea.getDescripcion());
            statement.setTimestamp(2, Timestamp.valueOf(tarea.getFechaAlta()));
            statement.setInt(3, tarea.isFinalizada()?1:0);
            statement.setInt(4, tarea.getCategoria().getId());
            statement.setInt(5, tarea.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ha ocurrido un error en el acceso o conexión a la base de datos (update)");
        }

        return tarea;
    }

    @Override
    public void remove(Tarea tarea) throws DatabaseErrorException, NotFoundException {
        String sql = String.format("DELETE FROM %s WHERE id = ?", TABLE_NAME);

        try (
                Connection connection =  new MySqlConnection("localhost", "tasks_db", "root", "123456").getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            statement.setInt(1, tarea.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ha ocurrido un error en el acceso o conexión a la base de datos (delete)");
        }
    }

    private Tarea getTaskFromResultset(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String descripcion = rs.getString("descripcion");
        LocalDateTime fecha = rs.getTimestamp("fechaAlta").toLocalDateTime();
        boolean finalizado = rs.getBoolean("finalizada");
        Integer idCategoria = rs.getInt("categoria");
        Categoria categoria = new Categoria(idCategoria);
        return new Tarea(id, descripcion, fecha, finalizado, categoria);
    }
}