package com.jbelt.play.domain.dto;

import com.jbelt.play.domain.Genre;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

// Clase record, es inmutable y nos sirve para representar los datos expuestos. Internamente crea constructores,
// y metodos de acceso. Creamos los atributos en inglés porque asi respondera la API
// Agregamos el atributo id, pero no es necesario mapearlo con MovieMapper ya que en el Entity y en el DTO se llaman igual
public record MovieDto(

        Long id,

        @NotBlank(message = "El Título es obligatorio")
        String title,

        @Positive(message = "Debe ingresarse la duración (minutos) en numeros positivos")
        Integer duration,

        @NotNull(message = "El género es obligatorio")
        Genre genre,

        @NotNull(message = "Ingrese la fecha de lanzamiento")
        @PastOrPresent(message = "La fecha de lanzamiento debe ser anterior a la fecha actual")
        LocalDate releaseDate,

        @Min(value = 0, message = "El rating no puede ser menor que 0")
        @Max(value = 5, message = "El rating no puede ser mayor que 5")
        Double rating
) {
}



/*
Una API robusta, limpia y preparada para cambios necesita una arquitectura sólida. ¿Por qué? Porque un código acoplado a
la base de datos limita la evolución y expone detalles que deberían permanecer internos. Una API se debe estructurar adecuadamente
usando buenas prácticas, enfocándonos en la introducción de la capa de dominio y el uso del patrón Data.

¿Cómo afecta el acoplamiento entre controlador y base de datos el diseño de tu API?

Actualmente, el controlador llama directamente al CRUD del Movie Entity, exponiendo la estructura interna de la
base de datos. Esto implica varios riesgos:

- Alto acoplamiento: cualquier cambio en la base de datos puede romper la API fácilmente.
- Exposición de datos sensibles o innecesarios: al no filtrar la salida, pueden aparecer campos indeseados.
- Mezcla de idiomas: el endpoint es /movies pero el contenido de la respuesta está en español.

Estos problemas hacen que la API sea difícil de mantener, frágil y dependiente de la estructura interna de la base de datos.

¿Por qué es clave separar responsabilidades entre capas en una API?

Separar responsabilidades permite que cada parte del sistema cumpla una función clara y focalizada. Dejar fuera la capa de
dominio va contra los principios de una buena arquitectura. Con una correcta separación:

- Cada capa conoce solo lo necesario de las demás.
- La evolución de una capa no obliga a reescribir las otras.
- La lógica de dominio es independiente de la infrastructura técnica.
- El patrón Data Mapper ayuda a lograr esto separando el modelo de dominio de cómo se expone en la API o se almacena en la base de datos.

*/