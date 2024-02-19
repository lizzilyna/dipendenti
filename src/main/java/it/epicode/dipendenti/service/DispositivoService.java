package it.epicode.dipendenti.service;

import it.epicode.dipendenti.exception.ManutenzioneException;
import it.epicode.dipendenti.exception.NotFoundException;
import it.epicode.dipendenti.model.Dipendente;
import it.epicode.dipendenti.model.Dispositivo;
import it.epicode.dipendenti.model.DispositivoRequest;
import it.epicode.dipendenti.model.StatoDispositivo;
import it.epicode.dipendenti.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private DipendenteService dipendenteService;

    public Page<Dispositivo>cercaTuttiIDispositivi(Pageable pageable)
    {return dispositivoRepository.findAll(pageable);
}

    public Dispositivo cercaDispositivoPerId(int id) throws NotFoundException {
        return dispositivoRepository.findById(id).
                orElseThrow(()->new NotFoundException("Dispositivo con id="+id+" non trovato"));
    }

        public Dispositivo saveDispositivo(DispositivoRequest dispositivoRequest)throws ManutenzioneException {
        Dipendente dipendente=dipendenteService.cercaDipendentePerId(dispositivoRequest.getId());
        Dispositivo dispositivo=new Dispositivo( dispositivoRequest.getTipo(), dispositivoRequest.getStatoDispositivo(), dipendente);
        if( dispositivoRequest.getStatoDispositivo()== StatoDispositivo.ASSEGNATO){
            throw new ManutenzioneException("dispositivo già assegnato");

        } else if (dispositivoRequest.getStatoDispositivo().equals(StatoDispositivo.MANUTENZIONE)) {
            throw new NotFoundException("dispositivo non assegnabile");

        }else if (dispositivoRequest.getStatoDispositivo().equals(StatoDispositivo.DISMESSO)){
            throw new ManutenzioneException("dispositivo dismesso");

        }


        return dispositivoRepository.save(dispositivo);
    }

    public Dispositivo aggiornaDispositivo(int id, DispositivoRequest dispositivoRequest) throws NotFoundException{
        Dispositivo dispositivo = cercaDispositivoPerId(id);

        Dipendente dipendente = dipendenteService.cercaDipendentePerId(dispositivoRequest.getId());

        dispositivo.setDipendente(dispositivoRequest.getDipendente());
        dispositivo.setId(dispositivoRequest.getId());
        dispositivo.setTipo(dispositivoRequest.getTipo());
        dispositivo.setStatoDispositivo(dispositivoRequest.getStatoDispositivo());


        return dispositivoRepository.save(dispositivo);
    }

    public void cancellaDispositivo(int id) throws NotFoundException{
        Dispositivo dispositivo = cercaDispositivoPerId(id);
        dispositivoRepository.delete(dispositivo);
    }

}