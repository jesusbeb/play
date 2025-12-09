package com.jbelt.play.persistence.crud;

import com.jbelt.play.persistence.Entity.MovieEntity;
import org.springframework.data.repository.CrudRepository;

// Repositorio que extiende de CrudRepository para tener accceso a todos los metodos para
// realizar operaciones en la BD sin escribir codigo SQL
//Recibe dos elementos genericos, nombre del Entity y tipo de dato de su clave primaria
public interface CrudMovieEntity extends CrudRepository<MovieEntity, Long> {

    // QueryMethod que retorna un MovieEntity y recibe un String
    MovieEntity findFirstByTitulo(String titulo);
}
