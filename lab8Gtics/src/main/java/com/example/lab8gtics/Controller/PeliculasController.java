package com.example.lab8gtics.Controller;

import com.example.lab8gtics.Entity.Pelicula;
import com.example.lab8gtics.Repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping(value = {"/peliculas"})
public class PeliculasController {
    @Autowired
    PeliculaRepository peliculaRepository;

    @GetMapping("/listar")
    public List<Pelicula> listaPeliculas() {
        return peliculaRepository.findAll();
    }


}
