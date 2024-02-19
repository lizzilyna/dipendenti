package it.epicode.dipendenti.controller;


import it.epicode.dipendenti.exception.BadRequestException;
import it.epicode.dipendenti.model.Dipendente;
import it.epicode.dipendenti.model.DipendenteRequest;
import it.epicode.dipendenti.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;



    @GetMapping("/dipendenti")
    public Page<Dipendente> getAll(Pageable pageable){
        return dipendenteService.cercaTuttiIDipendenti(pageable);
    }

    @GetMapping("/dipendenti/{id}")
    public Dipendente getDipendenteById(@PathVariable int id){
        return dipendenteService.cercaDipendentePerId(id);
    }

    @PostMapping("/dipendenti")
    public Dipendente saveDipendente(@RequestBody @Validated DipendenteRequest dipendenteRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return dipendenteService.salvaDipendente(dipendenteRequest);
    }

    @PutMapping("/dipendenti/{id}")
    public Dipendente updateDipendente(@PathVariable int id, @RequestBody @Validated DipendenteRequest dipendenteRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return dipendenteService.aggiornaDipendente(id, dipendenteRequest);
    }

    @DeleteMapping("/dipendenti/{id}")
    public void deleteDipendente(@PathVariable int id){
        dipendenteService.cancellaDipendente(id);
    }


    }



