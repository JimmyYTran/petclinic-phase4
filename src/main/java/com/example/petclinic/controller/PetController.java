package com.example.petclinic.controller;

import com.example.petclinic.model.Pet;
import com.example.petclinic.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public Pet add(Pet pet) {

        return this.petService.add(pet);
    }

    @Override
    @RequestMapping(value = "getPet", method = RequestMethod.GET, produces = "application/json")
    public Pet get(Long id) {

        return this.petService.get(id);
    }

    @Override
    @PutMapping(value = "modifyPet", produces = "application/json")
    public Pet modify(Pet pet) {

        return this.petService.modify(pet);
    }

    @Override
    @DeleteMapping(value = "deletePet", produces = "application/json")
    public boolean delete(Pet pet) {

        return this.petService.delete(pet);
    }

    @Override
    @GetMapping(value = "getAllPets", produces = "application/json")
    public List<Pet> getAll() {

        return this.petService.getAll();
    }


}
