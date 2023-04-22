package es.progcipfpbatoi.modelo.repositorios;

import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.modelo.dao.TareaDAO;
import es.progcipfpbatoi.modelo.dto.Tarea;

import java.util.ArrayList;

public class TareaRepository {

    private TareaDAO tareaDAO;

    public TareaRepository(TareaDAO tareaDAO) {
        this.tareaDAO = tareaDAO;
    }

    public ArrayList<Tarea> findAll() {
        return tareaDAO.findAll();
    }

    public ArrayList<Tarea> findAll(String text) {
        return tareaDAO.findAll(text);
    }

    public boolean save(Tarea tarea) throws DatabaseErrorException {
        return tareaDAO.save(tarea);
    }
}
