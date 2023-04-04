package es.progcipfpbatoi.modelo.repositorios;

import es.progcipfpbatoi.modelo.entidades.Tarea;

import java.util.ArrayList;

public class InMemoryTareaRepository implements TareaRepository{

    private ArrayList<Tarea> tareas;

    public InMemoryTareaRepository() {
        this.tareas = new ArrayList<>();
        init();
    }

    private void init() {
        this.tareas.add(new Tarea(1, "Sacar al perro"));
        this.tareas.add(new Tarea(2, "Lavar la ropa"));
        this.tareas.add(new Tarea(3, "Hacer la comida"));
        this.tareas.add(new Tarea(4, "Ir a comprar"));
    }

    @Override
    public ArrayList<Tarea> findAll() {
        return tareas;
    }

    @Override
    public boolean save(Tarea tarea) {
        return tareas.add(tarea);
    }

    @Override
    public ArrayList<Tarea> findAll(String text) {
        ArrayList<Tarea>tareasFiltradas = new ArrayList<>();
        for (Tarea tarea: tareas) {
            if (tarea.empiezaPor(text)) {
                tareasFiltradas.add(tarea);
            }
        }

        return tareasFiltradas;
    }
}
