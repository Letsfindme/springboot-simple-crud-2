package com.fadi.simplecrud2.controller;


import com.fadi.simplecrud2.model.Film;
import com.fadi.simplecrud2.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FilmController {

    @Autowired
    FilmRepository filmRepository;

    @GetMapping("/film")
    List<Film>findAllfilm(){
        return filmRepository.findAll();
    }


    @GetMapping("/film/{id}")
    ResponseEntity<Film> getPeopleById(@PathVariable(value="id") long id) {
        Film film = filmRepository.findOne(id);
        if(film == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(film);
    }

    @PutMapping("/acteur/{id}")
    ResponseEntity<Film> updateActeur(@PathVariable(value="id") long id, @Valid @RequestBody Film film){
        Film filmToUpdate = filmRepository.findOne(id);
        if(filmToUpdate == null)
            return ResponseEntity.notFound().build();

        // Update the mandatory attributes
        filmToUpdate.setName(film.getName());

        // Update all other not null attributes
        if(film.getYear() != 0)
            filmToUpdate.setYear(film.getYear());

        if(film.getRate() != 0)
            filmToUpdate.setRate(film.getRate());

        if(film.getRef() != 0)
            filmToUpdate.setRef(film.getRef());

        Film updatedfilm = filmRepository.save(filmToUpdate);
        return ResponseEntity.ok(updatedfilm);
    }
}
