package com.jbelt.play.domain.services;

import com.jbelt.play.domain.dto.MovieDto;
import com.jbelt.play.domain.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    // Inyeccion de MovieRepository como constante porque no cambiara tras inicializarse
    private final MovieRepository movieRepository;

    // Constructor para inicializar el MovieRepository
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Metodo para retornar List<MovieDTO>
    public List<MovieDto> getAll(){
        return this.movieRepository.getAll();
    }

}
