package com.example.petclinic.controller;

import com.example.petclinic.model.Vet;
import com.example.petclinic.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public Vet add(Vet vet) {

        return this.vetService.add(vet);
    }

    @Override
    @GetMapping(value = "addVet", produces = "application/json")
    public Vet get(Long id) {

        return this.vetService.get(id);
    }

    @Override
    @PutMapping(value = "modifyVet", produces = "application/json")
    public Vet modify(Vet vet) {

        return this.vetService.modify(vet);
    }

    @Override
    @DeleteMapping(value = "deleteVet", produces = "application/json")
    public boolean delete(Vet vet) {

        return this.vetService.delete(vet);
    }

    @Override
    @GetMapping(value = "getAllPets", produces = "application/json")
    public List<Vet> getAll() {

        return this.vetService.getAll();
    }
}
