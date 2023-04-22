package es.progcipfpbatoi.modelo.dao;

import es.progcipfpbatoi.modelo.dto.Tarea;

import java.util.ArrayList;

public class FileTareaDAO implements TareaDAO{

    @Override
    public ArrayList<Tarea> findAll() {
        return null;
    }

    @Override
    public ArrayList<Tarea> findAll(String text) {
        return null;
    }

    @Override
    public boolean save(Tarea tarea) {
        return false;
    }
}
