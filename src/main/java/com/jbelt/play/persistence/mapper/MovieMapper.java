package com.jbelt.play.persistence.mapper;

import com.jbelt.play.domain.dto.MovieDto;
import com.jbelt.play.persistence.Entity.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

// Aqui creamos nuestro mapeador personalizado
// Se anota con Mapper para que igual entre en el ciclo de vida de Spring
// componentModel le dice a spring que tiene que inyectar esta dependencia
@Mapper(componentModel = "spring")
public interface MovieMapper {

    // Indicamos las reglas a MapStruct de como convertir un Entity a un
    // DTO mediante anotaciones
    // Metodo toDto que devuelve un MovieDto y recibe un MovieEntity
    @Mapping(source = "titulo", target = "title")
    @Mapping(source = "duracion", target = "duration")
    @Mapping(source = "genero", target = "genre")
    @Mapping(source = "fechaEstreno", target = "releaseDate")
    @Mapping(source = "clasificacion", target = "rating")
    MovieDto toDto(MovieEntity entity);

    //Metodo toDto para obtener una Lista de DTO's, recibe un Iterable de Entity
    List<MovieDto> toDto(Iterable<MovieEntity> entities);

}