package com.example.petclinic.controller;

import com.example.petclinic.model.Visit;
import com.example.petclinic.service.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("visit")
public class VisitController implements BasicController<Visit> {

    private VisitService visitService;

    public VisitController(VisitService visitService) {

        this.visitService = visitService;
    }

    @Override
    @PostMapping(value = "addVisit", produces = "application/json")
    public Visit add(@RequestBody Visit visit) {

        return this.visitService.add(visit);
    }

    @Override
    @GetMapping(value = "getVisit/{id}", produces = "application/json")
    public Visit get(@PathVariable("id") Long id) {

        Visit visit = null;
        try {
            visit = this.visitService.get(id);
        } catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner [" + id + "] Not Found", exc);
        }
        return visit;
    }

    @Override
    @PutMapping(value = "modifyVisit", produces = "application/json")
    public Visit modify(@RequestBody Visit visit) {

        return this.visitService.modify(visit);
    }

    @Override
    @DeleteMapping(value = "deleteVisit", produces = "application/json")
    public boolean delete(@RequestBody Visit visit) {

        return this.visitService.delete(visit);
    }

    @Override
    @GetMapping(value = "getAllVisits", produces = "application/json")
    public List<Visit> getAll() {

        return this.visitService.getAll();

    }
}
