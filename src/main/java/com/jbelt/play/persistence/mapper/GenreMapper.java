package com.jbelt.play.persistence.mapper;

import com.jbelt.play.domain.Genre;
import org.mapstruct.Named;

// Esta clase contendra los metodos para convertir los generos que estan en String y en español en
// el Entity, a un valor del enum Genre que ahora estan en inglés, en los DTO. Y tambien lo hara en viceversa
public class GenreMapper {

    // Metodo estatico que retorna un Genre y recibe un String
    // Usamos la notacion Named para que funcione automaticamente con MapStruct y
    // le damos un nombre
    @Named("stringToGenre")
    public static Genre stringToGenre(String genero){
        if (genero == null) return null;

        return switch ( genero.toUpperCase() ) {
          case "ACCION" -> Genre.ACTION;
          case "COMEDIA" -> Genre.COMEDY;
          case "DRAMA" -> Genre.DRAMA;
          case "ANIMADA" -> Genre.ANIMATED;
          case "TERROR" -> Genre.HORROR;
          case "CIENCIA_FICCION" -> Genre.SCI_FI;
            default -> null;
        };
    }

    // Metodo estatico que retorna un String y recibe un Genre
    @Named("genreToString")
    public static String genreToString(Genre genre){
        if (genre == null) return null;

        return switch (genre) {
            case ACTION -> "ACCION";
            case COMEDY -> "COMEDIA";
            case DRAMA -> "DRAMA";
            case ANIMATED -> "ANIMADA";
            case HORROR -> "TERROR";
            case SCI_FI -> "CIENCIA_FICCION";
        };
    }

}
