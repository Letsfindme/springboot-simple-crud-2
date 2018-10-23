package com.fadi.simplecrud2.controller;


import com.fadi.simplecrud2.model.Acteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fadi.simplecrud2.repository.ActeurRepository;

import javax.validation.Valid;
import java.util.List;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class ActeurController {


    @Autowired
    ActeurRepository acteurRepository;

    @GetMapping("/acteur")
    List<Acteur>getAllActeur(){
        return acteurRepository.findAll();
    }

    @GetMapping("/acteur/{id}")
    ResponseEntity<Acteur> getActeurById(@PathVariable(value="id") long id){
        Acteur acteur= acteurRepository.findOne(id);
        if(acteur == null){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok().body(acteur);
    }

    @PostMapping("/acteur")
    Acteur addActeur(@Valid @RequestBody Acteur acteur){
        return acteurRepository.save(acteur);
    }

    @PutMapping("/acteur/{id}")
    ResponseEntity<Acteur> updateActeur(@PathVariable(value="id") long id, @Valid @RequestBody Acteur acteur){
        Acteur acteurToUpdate = acteurRepository.findOne(id);
        if(acteurToUpdate == null)
            return ResponseEntity.notFound().build();

        // Update the mandatory attributes
        acteurToUpdate.setFirstname(acteur.getFirstname());
        acteurToUpdate.setName(acteur.getName());

        // Update all other not null attributes
        if(acteur.getAddress() != null)
            acteurToUpdate.setAddress(acteur.getAddress());

        if(acteur.getPhone() != null)
            acteurToUpdate.setPhone(acteur.getPhone());

        if(acteur.getEmail() != null)
            acteurToUpdate.setEmail(acteur.getEmail());

        Acteur updatedPeople = acteurRepository.save(acteurToUpdate);
        return ResponseEntity.ok(updatedPeople);
    }
}
