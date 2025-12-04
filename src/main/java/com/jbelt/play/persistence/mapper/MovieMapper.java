package com.jbelt.play.persistence.mapper;

import com.jbelt.play.domain.dto.MovieDto;
import com.jbelt.play.persistence.Entity.MovieEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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

}