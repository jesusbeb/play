package com.jbelt.play.domain.exception;

// Clase para excepcion personalizada
public class MovieNotFound extends RuntimeException{

    public MovieNotFound(long id){
        super("La pelicula con el id: " +id +" no fue encontrada.");
    }
}
