package com.example.petclinic.controller;

import com.example.petclinic.model.Pet;
import com.example.petclinic.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("pet")
public class PetController implements BasicController<Pet> {

    private PetService petService;

    public PetController(PetService petService) {

        this.petService = petService;
    }

    @Override
    @PostMapping(value = "addPet", produces = "application/json")
    public Pet add(@RequestBody Pet pet) {

        return this.petService.add(pet);
    }

    @Override
    @RequestMapping(value = "getPet/{id}", method = RequestMethod.GET, produces = "application/json")
    public Pet get(@PathVariable("id") Long id) {

        Pet pet = null;
        try {
            pet = this.petService.get(id);
        } catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner [" + id + "] Not Found", exc);
        }
        return pet;
    }

    @Override
    @PutMapping(value = "modifyPet", produces = "application/json")
    public Pet modify(@RequestBody Pet pet) {

        return this.petService.modify(pet);
    }

    @Override
    @DeleteMapping(value = "deletePet", produces = "application/json")
    public boolean delete(@RequestBody Pet pet) {

        return this.petService.delete(pet);
    }

    @Override
    @GetMapping(value = "getAllPets", produces = "application/json")
    public List<Pet> getAll() {

        return this.petService.getAll();
    }


}
