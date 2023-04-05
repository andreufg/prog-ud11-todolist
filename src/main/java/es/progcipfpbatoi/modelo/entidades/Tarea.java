package es.progcipfpbatoi.modelo.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Tarea {

    private int id;

    private String descripcion;

    private LocalDateTime fechaAlta;

    private boolean finalizada;

    private Categoria categoria;

    public Tarea(int id, String descripcion, Categoria categoria) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaAlta = LocalDateTime.now();
        this.finalizada = false;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return descripcion + " " + finalizada;
    }

    public boolean empiezaPor(String text) {
        return this.descripcion.startsWith(text);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void cambiarEstado() {
        this.finalizada = !this.finalizada;
    }

    public LocalDate getFechaAltaSinTiempo() {
        return this.fechaAlta.toLocalDate();
    }
}
