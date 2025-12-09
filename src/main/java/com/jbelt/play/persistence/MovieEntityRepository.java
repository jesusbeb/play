package com.jbelt.play.persistence;

import com.jbelt.play.domain.dto.MovieDto;
import com.jbelt.play.domain.dto.UpdateMovieDto;
import com.jbelt.play.domain.exception.MovieAlreadyExistsException;
import com.jbelt.play.domain.exception.MovieNotFound;
import com.jbelt.play.domain.repository.MovieRepository;
import com.jbelt.play.persistence.Entity.MovieEntity;
import com.jbelt.play.persistence.crud.CrudMovieEntity;
import com.jbelt.play.persistence.mapper.MovieMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

// Esta clase implementa MovieRepository
@Repository
public class MovieEntityRepository implements MovieRepository {
    // Inyecciones de crudMovieEntity y movieMapper
    private final CrudMovieEntity crudMovieEntity;
    private final MovieMapper movieMapper;

    public MovieEntityRepository(CrudMovieEntity crudMovieEntity, MovieMapper movieMapper) {
        this.crudMovieEntity = crudMovieEntity;
        this.movieMapper = movieMapper;
    }

    // Retornamos una List<MovieDTO> usando el metodo toDto de movieMapper, al que le
    // enviamos el Iterable<MovieEntity> que retorna findAll del crudMovieEntity
    @Override
    public List<MovieDto> getAll() {
        return this.movieMapper.toDto(this.crudMovieEntity.findAll());
    }

    // Obtenemos una pelicula por id capturandola en un MovieEntity que retorna el metodo findById de
    // crudMovieEntity y como es un opcional, usamos orElse para asignar null en caso de no encontrarlo
    // Despues hacemos uso de movieMapper para convertirla a un DTO
    @Override
    public MovieDto getById(long id) {
        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);
        return this.movieMapper.toDto(movieEntity);
    }

    // Metodo para guardar una pelicula en la BD
    // El DTO que se recibe se convierte a Entity con movieMapper
    // Retornamos un DTO de la pelicula Entity que se guarda en la BD
    @Override
    public MovieDto save(MovieDto movieDto) {

        // Buscamos si en la BD ya existe una pelicula con el mismo nombre, de ser asi
        // lanzamos un exception y enviamos el titulo de la pelicula
        if (this.crudMovieEntity.findFirstByTitulo(movieDto.title()) != null){
            throw new MovieAlreadyExistsException(movieDto.title());
        }

        MovieEntity movieEntity = this.movieMapper.toEntity(movieDto);
        movieEntity.setEstado("D"); // D de Disponible

        return this.movieMapper.toDto(this.crudMovieEntity.save(movieEntity));
    }

    // Buscamos la movieEntity en la BD por el id que llega
    // Si encontramos la pelicula, pasamos los datos que vienen de updateMovieDto al movieEntity
    // Al final guardamos el Entity actualizado en la BD y lo retornamos como DTO
    @Override
    public MovieDto update(long id, UpdateMovieDto updateMovieDto) {
        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);

        if (movieEntity == null) return null;

//        movieEntity.setTitulo(updateMovieDto.title());
//        movieEntity.setFechaEstreno(updateMovieDto.releaseDate());
//        movieEntity.setClasificacion(BigDecimal.valueOf(updateMovieDto.rating()));
//        // Otra forma de hacer lo anterior para pasar los datos al Entity desde un DTO,
//        // es hacer un metodo con MapStruct: (Este metodo recibe por referencia a movieEntity,
//        // por eso no se asigna el resultado, ademas que el metodo no retorna nada)

        this.movieMapper.updateEntityFromDto(updateMovieDto, movieEntity);
        return this.movieMapper.toDto(this.crudMovieEntity.save(movieEntity));
    }

    @Override
    public void delete(long id) {
        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);

        if (movieEntity == null){
            throw new MovieNotFound(id);
        }
        this.crudMovieEntity.deleteById(id);
    }

}



/*
¿Qué problema resuelve Mapstruct al convertir entidades en DTO en Java con Spring?
Al trabajar en aplicaciones empresariales, suele ser necesario separar las entidades de base de datos (MovieEntity) y
los objetos de transferencia de datos (MovieDTO). Usar Mapstruct permite convertir de forma automática y sin
esfuerzo manual entre estos dos tipos, logrando:

- Desacoplar la lógica de persistencia de la lógica de dominio y de presentación.
- Evitar errores y simplificar el código al eliminar transformaciones manuales.
- Mantener el código limpio y preparado para cambios futuros en el modelo de datos.

 */