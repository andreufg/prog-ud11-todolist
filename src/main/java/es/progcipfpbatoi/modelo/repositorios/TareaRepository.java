package es.progcipfpbatoi.modelo.repositorios;

import es.progcipfpbatoi.modelo.entidades.Tarea;

import java.util.ArrayList;

public interface TareaRepository {
    ArrayList<Tarea> findAll();
    boolean save(Tarea tarea);
}
