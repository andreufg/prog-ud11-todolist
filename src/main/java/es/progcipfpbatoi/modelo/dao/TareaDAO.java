package es.progcipfpbatoi.modelo.dao;

import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.modelo.dto.Tarea;

import java.util.ArrayList;

public interface TareaDAO {
    ArrayList<Tarea> findAll();
    ArrayList<Tarea> findAll(String text);
    boolean save(Tarea tarea) throws DatabaseErrorException;
}
