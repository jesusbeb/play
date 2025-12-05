package com.jbelt.play.domain.repository;

import com.jbelt.play.domain.dto.MovieDto;
import com.jbelt.play.domain.dto.UpdateMovieDto;

import java.util.List;

// Interface MovieRepository
public interface MovieRepository {

    // Metodo para retornar todas las peliculas de la BD
    List<MovieDto> getAll();

    // Metodo para retornar una pelicula por ID
    MovieDto getById(long id);

    // Metodo para guardar una pelicula
    MovieDto save(MovieDto movieDto);

    // Metodo para actualizar una pelicula
    MovieDto update(long id, UpdateMovieDto updateMovieDto);
}
