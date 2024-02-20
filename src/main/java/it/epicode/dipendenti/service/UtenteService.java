package it.epicode.dipendenti.service;

import it.epicode.dipendenti.exception.NotFoundException;
import it.epicode.dipendenti.model.Utente;
import it.epicode.dipendenti.model.UtenteRequest;
import it.epicode.dipendenti.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;
    public Utente save(UtenteRequest utenteRequest){
        Utente utente=new Utente();
        utente.setNome(utenteRequest.getNome());
        utente.setCognome(utenteRequest.getCognome());
        utente.setUsername(utente.getUsername());
        utente.setPassword(utenteRequest.getPassword());
return utenteRepository.save(utente);
    }

    public Utente getUtenteByUsername(String username) throws NotFoundException{
        return utenteRepository.findByUsername(username).orElseThrow(()->new NotFoundException("Username non trovato"));
    }
}
