package es.progcipfpbatoi.modelo.repositorios;

import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.exceptions.NotFoundException;
import es.progcipfpbatoi.modelo.dao.TareaDAO;
import es.progcipfpbatoi.modelo.dto.Tarea;

import java.util.ArrayList;

public class TareaRepository {

    private TareaDAO tareaDAO;

    public TareaRepository(TareaDAO tareaDAO) {
        this.tareaDAO = tareaDAO;
    }

    public ArrayList<Tarea> findAll() throws DatabaseErrorException{
        return tareaDAO.findAll();
    }

    public ArrayList<Tarea> findAll(String text) throws DatabaseErrorException{
        return tareaDAO.findAll(text);
    }

    public Tarea getById(int id) throws DatabaseErrorException, NotFoundException {
        return tareaDAO.getById(id);
    }

    public void save(Tarea tarea) throws DatabaseErrorException {
        tareaDAO.save(tarea);
    }

    public void remove(Tarea tarea) throws NotFoundException, DatabaseErrorException {
        tareaDAO.remove(tarea);
    }
}
