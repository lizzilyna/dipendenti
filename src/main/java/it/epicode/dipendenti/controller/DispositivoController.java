package it.epicode.dipendenti.controller;

import it.epicode.dipendenti.exception.BadRequestException;
import it.epicode.dipendenti.model.Dispositivo;
import it.epicode.dipendenti.model.DispositivoRequest;
import it.epicode.dipendenti.service.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class DispositivoController {
    @Autowired
    private DispositivoService dispositivoService;

    @GetMapping("/dispositivo")
    public Page<Dispositivo> getAll(Pageable pageable) {
        return dispositivoService.cercaTuttiIDispositivi(pageable);
    }

    @GetMapping("/dispositivo/{id}")
    public Dispositivo getDispositivoById(@PathVariable int id) {
        return dispositivoService.cercaDispositivoPerId(id);
    }

    @PostMapping("/dispositivo")
    public Dispositivo saveDispositivo(@RequestBody @Validated DispositivoRequest dispositivoRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return dispositivoService.saveDispositivo(dispositivoRequest);
    }
    @PutMapping("/dispositivo/{id}")
    public Dispositivo updateDispositivo(@PathVariable int id, @RequestBody @Validated DispositivoRequest dispositivoRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return dispositivoService.aggiornaDispositivo(id, dispositivoRequest);
    }

    @DeleteMapping("/dispositivo/{id}")
    public void deleteDispositivo(@PathVariable int id){
        dispositivoService.cancellaDispositivo(id);
    }
}


