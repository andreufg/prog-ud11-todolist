package es.progcipfpbatoi.modelo.entidades;

import java.time.LocalDateTime;

public class Tarea {

    private int id;

    private String descripcion;

    private LocalDateTime fechaAlta;

    private boolean finalizada;

    public Tarea(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaAlta = LocalDateTime.now();
        this.finalizada = false;
    }

    @Override
    public String toString() {
        return descripcion + " " + finalizada;
    }
}
