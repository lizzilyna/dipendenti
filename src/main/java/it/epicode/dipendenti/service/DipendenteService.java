package it.epicode.dipendenti.service;

import it.epicode.dipendenti.exception.NotFoundException;
import it.epicode.dipendenti.model.Dipendente;
import it.epicode.dipendenti.model.DipendenteRequest;
import it.epicode.dipendenti.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    private List<Dipendente> dipendenti=new ArrayList<>();
    public Page<Dipendente>cercaTuttiIDipendenti
            (Pageable pageable){
        return dipendenteRepository.findAll(pageable);
    }

    public Dipendente cercaDipendentePerId(int id){
        return dipendenteRepository.findById(id).orElseThrow(()->new NotFoundException("Dipendente con id "+id+" non trovato"));
    }

    public Dipendente salvaDipendente (DipendenteRequest dipendenteRequest) {
        Dipendente dipendente= new Dipendente

                (dipendenteRequest.getNome(),
                dipendenteRequest.getCognome(),
                dipendenteRequest.getEmail(),
                dipendenteRequest.getUsername());
        return dipendenteRepository.save(dipendente);
    }

    public Dipendente aggiornaDipendente(int id, DipendenteRequest dipendenteRequest)
        throws NotFoundException{
        Dipendente d =cercaDipendentePerId(id);
        d.setNome(dipendenteRequest.getNome());
        d.setCognome(dipendenteRequest.getCognome());
        d.setEmail(dipendenteRequest.getEmail());
        d.setUsername(dipendenteRequest.getUsername());

        return dipendenteRepository.save(d);
    }

    public void cancellaDipendente(int id) throws NotFoundException{
        Dipendente d = cercaDipendentePerId(id);

        dipendenteRepository.delete(d);
    }

    public Dipendente uploadAvatar(int id, String url){
        Dipendente dipendente = cercaDipendentePerId(id);
        dipendente.setAvatar(url);
        return dipendenteRepository.save(dipendente);
    }
}
