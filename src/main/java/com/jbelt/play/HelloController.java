package com.jbelt.play;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//RestController indica que es un controlador de peticiones
@RestController
public class HelloController {
    private final String plataform;
    private final PlayAiService aiService; //constante

    // Constructor
    // Value("${}" sirve para inyectar el valor de una variable que esta dentro del
    // archivo application.properties al parametro String plataform
    public HelloController(@Value("${spring.application.name}") String plataform, PlayAiService aiService) {
        this.plataform = plataform;
        this.aiService = aiService;
    }

    //Metodo que retorna un saludo
    //GetMapping indica que recibe peticion de tipo get
    @GetMapping("/")
    public String hello(){
        return this.aiService.generateGreeting(plataform);
    }
}
