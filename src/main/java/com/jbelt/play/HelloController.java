package com.jbelt.play;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//RestController indica que es un controlador de peticiones
@RestController
public class HelloController {
    private final PlayAiService aiService; //constante

    //Constructor
    public HelloController(PlayAiService aiService) {
        this.aiService = aiService;
    }

    //Metodo que retorna un saludo
    //GetMapping indica que recibe peticion de tipo get
    @GetMapping("/")
    public String hello(){
        return this.aiService.generateGreeting();
    }
}
