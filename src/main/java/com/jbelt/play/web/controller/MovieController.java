package com.jbelt.play.web.controller;

import com.jbelt.play.domain.dto.MovieDto;
import com.jbelt.play.domain.dto.SuggestRequestDto;
import com.jbelt.play.domain.dto.UpdateMovieDto;
import com.jbelt.play.domain.services.MovieService;
import com.jbelt.play.domain.services.PlayAiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//RestController para indicar que los metodos de esta clase seran expuestos a traves de la API
// RequestMapping para indicar que todo el controlador tendra el prejijo /movies
@RestController
@RequestMapping("/movies")
public class MovieController {
    // Inyeccion de MovieService como constante porque no cambiara tras inicializarse
    // Inyeccion de PlayAiService para usar la IA
    private final MovieService movieService;
    private final PlayAiService playAiService;

    // Constructor para inicializar el MovieService
    public MovieController(MovieService movieService, PlayAiService playAiService) {
        this.movieService = movieService;
        this.playAiService = playAiService;
    }

    // Este metodo ya no se le indica el path o prefijo ya que lo recibe de la clase
    // Por lo que si no se indica responde al prefijo de la clase ("/movies")
    // GetMapping para indicar que se trata de una solicitud GET y esta accesible en /movies
    // Se retorna una List<MovieDto> dentro de un ResponseEntity
    @GetMapping
    public ResponseEntity< List<MovieDto> > getAll(){
        return ResponseEntity.ok(this.movieService.getAll());
    }

    // Metodo que retorna un ResponseEntity con un MovieDto dentro
    // @PathVariable para indicar que el parametro viene en una variable dentro del Path
    // if movieDto es null, retornamos un ResponseEntity.notFound
    // Y si la pelicula se encontro, se retorna el movieDto dentro de un ResponseEntity.ok
    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getById(@PathVariable long id) {
        MovieDto movieDto = this.movieService.getById(id);

        if (movieDto == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.ok(movieDto); // 200
    }

    // Metodo endpoint donde se obtendran las sugerencias de la IA en un String dentro del ResponseEntity
    // El propmt que se envia a la IA para obtener su sugerencia se recibe en el cuerpo del request desde un json
    // p.e. { "userPreferences": "prompt con solicitud de sugerencia de peliculas" } al recibirlo se pasa a un
    // suggestRequestDto, se llama al metodo dentro de playAiService y se le envia el atributo userPreferences
    @PostMapping("/suggest")
    public ResponseEntity<String> generateMoviesSuggestion(@RequestBody SuggestRequestDto suggestRequestDto) {
        return ResponseEntity.ok(this.playAiService.generateMoviesSuggestion(suggestRequestDto.userPreferences()));
    }

    // @RequestBody para indicar que el parametro vendra en el cuerpo de la peticion
    // No usamos ResponseEntity.created porque no tenmos una URI, por lo que usamos .status y
    // le enviamos un enum de Spring con el valor CREATED y el cuerpo de la respuesta es
    // la pelicula que se guardo en la BD
    @PostMapping
    public ResponseEntity<MovieDto> add(@RequestBody MovieDto movieDto) {
        MovieDto movieDtoResponse = this.movieService.add(movieDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieDtoResponse); // 201
    }

    // Metodo para actualizar una pelicula, recibe el id por PathVariable y
    // los datos a actualizar en el cuerpo del Request
    // Retorna un ResponseEntity y adentro el MovieDto actualizado
    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> update(@PathVariable long id, @RequestBody UpdateMovieDto updateMovieDto) {
        return ResponseEntity.ok(this.movieService.update(id, updateMovieDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        this.movieService.delete(id);
        return ResponseEntity.ok().build();
    }

}



/*
En el desarrollo backend, responder con el código HTTP adecuado es esencial para comunicar correctamente el
resultado de cada petición en tu API. Esta práctica incrementa la claridad, profesionalismo y permite a
clientes interpretar con precisión cada respuesta, conforme al estándar de la web.

¿Qué son los códigos HTTP y por qué son importantes?
Los códigos HTTP son valores numéricos estándar que indican el resultado de una petición entre un cliente y un servidor.

- El 200 muestra que la solicitud fue exitosa.
- El 201 indica que se creó un recurso nuevo.
- El 400 señala una petición incorrecta.
- El 404 se refiere a recursos no encontrados.
- El 500 marca errores internos del servidor.

Utilizar estos códigos correctamente guía a quienes consumen tu API sobre lo que realmente ocurrió con cada solicitud. Así, tu backend envía respuestas precisas y profesionales.
 */