package com.jbelt.play.web.controller;

import com.jbelt.play.domain.dto.MovieDto;
import com.jbelt.play.domain.services.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//RestController para indicar que los metodos de esta clase seran expuestos a traves de la API
// RequestMapping para indicar que todo el controlador tendra el prejijo /movies
@RestController
@RequestMapping("/movies")
public class MovieController {
    // Inyeccion de MovieService como constante porque no cambiara tras inicializarse
    private final MovieService movieService;

    // Constructor para inicializar el MovieService
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // Este metodo ya no se le indica el path o prefijo ya que lo recibe de la clase
    // Por lo que si no se indica responde al prefijo de la clase
    // GetMapping para indicar que se trata de una solicitud GET y esta accesible en /movies
    @GetMapping
    public List<MovieDto> getAll(){
        return this.movieService.getAll();
    }

    // Metodo que retorna una movie por su id
    // @PathVariable para indicar que el parametro viene en una variable dentro del Path
    @GetMapping("/{id}")
    public MovieDto getById(@PathVariable long id) {
        return this.movieService.getById(id);
    }

}
