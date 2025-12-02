package com.jbelt.play.web.controller;

import com.jbelt.play.persistence.Entity.MovieEntity;
import com.jbelt.play.persistence.crud.CrudMovieEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//RestController para indicar que los metodos de esta clase seran expuestos a traves de la API
@RestController
public class MovieController {
    // Instancia de CrudMovieEntity como una constante porque no cambiara tras inicializarse
    private final CrudMovieEntity crudMovieEntity;

    // Constructor para inicializar el CrudMovieEntity
    public MovieController(CrudMovieEntity crudMovieEntity) {
        this.crudMovieEntity = crudMovieEntity;
    }

    // Metodo que retorna una lista de MovieEntity, con todas las peliculas
    // findAll() retorna un Iterable de MovieEntity, por lo que se castea a una List
    // GetMapping para indicar que se trata de una solicitud GET y esta accesible en /movies
    @GetMapping("/movies")
    public List<MovieEntity> getAll(){
        return (List<MovieEntity>) this.crudMovieEntity.findAll();
    }

}
