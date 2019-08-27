package com.example.petclinic.controller;

import com.example.petclinic.model.Vet;
import com.example.petclinic.service.VetService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("vet")
public class VetController implements BasicController<Vet> {

    private VetService vetService;

    public VetController(VetService vetService) {

        this.vetService = vetService;
    }

    @Override
    @PostMapping(value = "addVet", produces = "application/json")
    public Vet add(@RequestBody Vet vet) {

        return this.vetService.add(vet);
    }

    @Override
    @GetMapping(value = "addVet/{id}", produces = "application/json")
    public Vet get(@PathVariable("id") Long id) {

        Vet vet = null;
        try {
            vet = this.vetService.get(id);
        } catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner [" + id + "] Not Found", exc);
        }
        return vet;
    }

    @Override
    @PutMapping(value = "modifyVet", produces = "application/json")
    public Vet modify(@RequestBody Vet vet) {

        return this.vetService.modify(vet);
    }

    @Override
    @DeleteMapping(value = "deleteVet", produces = "application/json")
    public boolean delete(@RequestBody Vet vet) {

        return this.vetService.delete(vet);
    }

    @Override
    @GetMapping(value = "getAllVets", produces = "application/json")
    public List<Vet> getAll() {

        return this.vetService.getAll();
    }
}
