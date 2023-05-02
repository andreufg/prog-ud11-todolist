package es.progcipfpbatoi.modelo.dao;

import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.exceptions.NotFoundException;
import es.progcipfpbatoi.modelo.dto.Categoria;
import es.progcipfpbatoi.modelo.dto.Tarea;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SQLTareaDAO implements TareaDAO{
    @Override
    public ArrayList<Tarea> findAll() throws DatabaseErrorException {
        return null;
    }

    @Override
    public ArrayList<Tarea> findAll(String text) throws DatabaseErrorException {
        return null;
    }

    @Override
    public Tarea getById(int id) throws NotFoundException, DatabaseErrorException {
        return null;
    }

    @Override
    public Tarea findById(int id) throws DatabaseErrorException {
        return null;
    }

    @Override
    public void save(Tarea tarea) throws DatabaseErrorException {

    }

    @Override
    public void remove(Tarea tarea) throws DatabaseErrorException, NotFoundException {

    }

    private Tarea getTaskFromResultset(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String descripcion = rs.getString("descripcion");
        LocalDateTime fecha = rs.getTimestamp("fechaAlta").toLocalDateTime();
        boolean finalizado = rs.getBoolean("finalizada");
        int idCategoria = rs.getInt("categoria");
        Categoria categoria = new Categoria(idCategoria);
        return new Tarea(id, descripcion, fecha, finalizado, categoria);
    }
}
