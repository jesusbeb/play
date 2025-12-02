package com.jbelt.play.persistence.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

// Una clase Entity representa una Tabla en una BD
@Entity
@Table(name = "play_peliculas")
public class MovieEntity {
    //Atributos

    //Id como primary key
    //Se genera automaticamente con la estrategia IDENTITY para asegurar que no se repita
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Columna no nula, longitud 150, titulo de la pelicula es unico
    @Column(nullable = false, length = 150, unique = true)
    private String titulo;

    //precision es el numero maximo de caracteres
    @Column(nullable = false, precision = 3)
    private Integer duracion;

    @Column(nullable = false, length = 40)
    private String genero;

    //nombre que tendra la columna en la BD
    @Column(name = "fecha_estreno")
    private LocalDate fechaEstreno;

    //3 caracteres, 2 decimales
    @Column(precision = 3, scale = 2)
    private BigDecimal clasificacion;

    @Column(nullable = false, length = 1)
    private String estado;


    //Getters & Setters

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public BigDecimal getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(BigDecimal clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}



/* PARA CONECTARNOS A LA BD USAREMOS DATAGRIP (PODRIA USARSE PGADMIN O CUALQUIER OTRO)
- Clic en +, Data Source, PostgreSQL, PostgreSQL
- Pegamos la URL y el Name cambiara automaticamente
- Indicamos el User y Password
- Hacemos un Test de conexion para ver que se haga correctamente
- Clic en Aplicar y OK
- Y ya aparecera la BD del lado izquierdo
- Del lado derecho aparecera una pestaña de Consola para ingresar los comandos SQL
*/