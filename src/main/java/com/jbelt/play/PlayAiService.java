package com.jbelt.play;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

//En esta clase ponemos todo lo perteneciente a ayudas de IA
@AiService
public interface PlayAiService {

    //Metodo que envia un mensaje mediante IA
    @UserMessage("""
            Genera un saludo de bienvenida a la plataforma de Gestion de Peliculas.
            Usa menos de 120 caracteres y hazlo con un estilo amistoso.
            """)
    String generateGreeting();
}
