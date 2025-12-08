package com.jbelt.play.domain.services;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.UserName;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

//En esta clase ponemos todo lo perteneciente a ayudas de IA
@AiService
public interface PlayAiService {

    // Metodo que envia un mensaje mediante IA
    // Recibe un String cuyo valor se incluira como parte del prompt que generara la
    // IA, para lo cual se usa la anotacion @V("nombreVariable")
    @UserMessage("""
            Genera un saludo de bienvenida a la plataforma de Gestion de Peliculas {{plataform}}.
            Usa menos de 120 caracteres y hazlo con un estilo amistoso.
            """)
    String generateGreeting(@V("plataform") String plataform);

    // Metodo para que la IA sugiera peliculas. Recibe un String que se anota con @UserMessage.
    // Con @SystemMessage enviamos el String que sera el prompt que indica el rol de la IA
    @SystemMessage("""
            Eres un experto en cine que recomienda peliculas personalizadas segun los gustos del usuario.
            Debes recomendar maximo 3 peliculas.
            No incluyas peliculas que esten por fuera de la plataforma Play.
            """)
    String generateMoviesSuggestion(@UserMessage String userMessage);
}
