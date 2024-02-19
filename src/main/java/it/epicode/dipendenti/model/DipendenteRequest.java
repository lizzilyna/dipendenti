package it.epicode.dipendenti.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class DipendenteRequest {

    @NotBlank(message = "campo obbligatorio")
    private String username;

    @NotBlank(message = "campo obbligatorio")
    private String nome;

    @NotBlank(message = "campo obbligatorio")
    private String cognome;

    @Email(message = "Email obbligatoria")
    private String email;
}
