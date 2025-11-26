package com.jbelt.play;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
- Vamos a start.spring.io
- Project: Gradle - Groovy, Languaje: Java, Spring Boot: 3.5.3 o el que este seleccionado por default
Indicamos el Group, Artifact: play, una descripcion, Packaging: Jar, Java: 21,
Clic en Add Dependencies: Buscamos y agregamos Spring Web
- Damos clic en Generate para descargar el proyecto en zip y descomprimimos
- Abrimos IntelliJ, abrir archivo, buscamos en la carpeta descomprimida el archivo build.gradle y
lo abrimos, seleccionamos abrir como proyecto.
- Clic en Menu File, Project Structure, Project, y verificamos que este indicado el SDK 21 en cualquiera
de sus distribuciones JDK 21

Corremos esta clase para verificar que el servicio Tomcat se inicialice correctamente. Vamos al navegador
y abrimos localhost:8080, si nos aparece "Whitelabel Error Page" es porque aun no hemos escrito codigo del proyecto y
el servicio Tomcat se ha inicializado correctamente
*/



@SpringBootApplication
public class PlayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayApplication.class, args);
	}

}
