package com.jbelt.play.persistence.mapper;

import com.jbelt.play.domain.dto.MovieDto;
import com.jbelt.play.domain.dto.UpdateMovieDto;
import com.jbelt.play.persistence.Entity.MovieEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

// Aqui creamos nuestro mapeador personalizado
// Se anota con Mapper para que igual entre en el ciclo de vida de Spring
// componentModel le dice a spring que tiene que inyectar esta dependencia
// Con uses indicamos que MovieMapper va a utilizar la clase GenreMapper
@Mapper(componentModel = "spring", uses = {GenreMapper.class})
public interface MovieMapper {

    // Indicamos las reglas a MapStruct de como convertir un Entity a un
    // DTO mediante anotaciones
    // Metodo toDto que devuelve un MovieDto y recibe un MovieEntity
    // En genero indicamos que se usara el metodo stringToGenre
    @Mapping(source = "titulo", target = "title")
    @Mapping(source = "duracion", target = "duration")
    @Mapping(source = "genero", target = "genre", qualifiedByName = "stringToGenre")
    @Mapping(source = "fechaEstreno", target = "releaseDate")
    @Mapping(source = "clasificacion", target = "rating")
    MovieDto toDto(MovieEntity entity);

    //Metodo toDto para obtener una Lista de DTO's, recibe un Iterable de Entity
    List<MovieDto> toDto(Iterable<MovieEntity> entities);

    // Metodo que convierte un DTO a un Entity
    // @InheritConfiguration va a tomar todos los @Mapping con el source y el target invertidos
    // Solo indicamos nuevamente el Mapping de genre para especificar que qualifiedByName va a usar
    @InheritInverseConfiguration
    @Mapping(source = "genre", target = "genero", qualifiedByName = "genreToString")
    MovieEntity toEntity(MovieDto dto);

    // Metodo que no retorna nada. Actualiza un Entity existente con los datos que vienen de un DTO
    // Recibe un DTO, y con @MappingTarget le decimos que reciba el Entity por
    // referencia (no cree un nuevo Entity, que use el mismo para actualizarlo, le aplique el mapeo).
    // Con Mapping especificamos que propiedad del DTO (source) se copiara a que propiedad del Entity (target)
    @Mapping(target = "titulo", source = "title")
    @Mapping(target = "fechaEstreno", source = "releaseDate")
    @Mapping(target = "clasificacion", source = "rating")
    void updateEntityFromDto(UpdateMovieDto updateMovieDto, @MappingTarget MovieEntity movieEntity);

}