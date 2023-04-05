package es.progcipfpbatoi.modelo.repositorios;

import es.progcipfpbatoi.modelo.entidades.Categoria;
import es.progcipfpbatoi.modelo.entidades.Tarea;

import java.util.ArrayList;

public class InMemoryTareaRepository implements TareaRepository{

    private ArrayList<Tarea> tareas;

    public InMemoryTareaRepository() {
        this.tareas = new ArrayList<>();
        init();
    }

    private void init() {
        this.tareas.add(new Tarea(1, "Sacar al perro", Categoria.HOGAR));
        this.tareas.add(new Tarea(2, "Actividad final de programación", Categoria.CLASE));
        this.tareas.add(new Tarea(3, "Hacer la comida", Categoria.HOGAR));
        this.tareas.add(new Tarea(4, "Ir al parque", Categoria.JUGAR));
    }

    @Override
    public ArrayList<Tarea> findAll() {
        return tareas;
    }

    public boolean save(Tarea tarea) {
        int index = tareas.indexOf(tarea);

        if (index == -1) {
            return this.tareas.add(tarea);
        } else {
            this.tareas.set(index, tarea);
            return true;
        }
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
