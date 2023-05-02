package es.progcipfpbatoi.modelo.dao;

import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.exceptions.NotFoundException;
import es.progcipfpbatoi.modelo.dto.Categoria;
import es.progcipfpbatoi.modelo.dto.Prioridad;
import es.progcipfpbatoi.modelo.dto.Tipo;

public class SQLCategoriaDAO implements CategoriaDAO{
    @Override
    public Categoria findById(int id) throws DatabaseErrorException {
        return null;
    }

    @Override
    public Categoria getByTypeAndPriority(Tipo tipo, Prioridad prioridad) throws NotFoundException, DatabaseErrorException {
        return null;
    }
}
