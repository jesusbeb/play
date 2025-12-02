package com.jbelt.play.web.controller;

import com.jbelt.play.domain.dto.MovieDto;
import com.jbelt.play.domain.services.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//RestController para indicar que los metodos de esta clase seran expuestos a traves de la API
@RestController
public class MovieController {
    // Inyeccion de MovieService como constante porque no cambiara tras inicializarse
    private final MovieService movieService;

    // Constructor para inicializar el MovieService
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // GetMapping para indicar que se trata de una solicitud GET y esta accesible en /movies
    @GetMapping("/movies")
    public List<MovieDto> getAll(){
        return this.movieService.getAll();
    }

}
