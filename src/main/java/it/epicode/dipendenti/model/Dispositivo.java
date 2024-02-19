package it.epicode.dipendenti.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="tipo")
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column(name="stato_dispositivo")
    @Enumerated(EnumType.STRING)
    private StatoDispositivo statoDispositivo;

    @ManyToOne
    @JoinColumn(name="dipendente_id")
    private Dipendente dipendente;

    public Dispositivo(Tipo tipo, StatoDispositivo statoDispositivo, Dipendente dipendente) {

        this.tipo = tipo;
        this.statoDispositivo = statoDispositivo;
        this.dipendente = dipendente;
    }


    }

