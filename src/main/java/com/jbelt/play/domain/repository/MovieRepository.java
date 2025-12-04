package com.jbelt.play.domain.repository;

import com.jbelt.play.domain.dto.MovieDto;

import java.util.List;

// Interface MovieRepository
public interface MovieRepository {

    // Metodo para retornar todas las peliculas de la BD
    List<MovieDto> getAll();

    // Metodo para retornar una pelicula por ID
    MovieDto getById(long id);
}
