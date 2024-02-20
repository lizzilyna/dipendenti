package it.epicode.dipendenti.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UtenteRequest {
    @NotBlank(message = "campo obbligatorio")
    private String nome;
    @NotBlank(message = "campo obbligatorio")
    private String cognome;
    @NotBlank(message = "campo obbligatorio")
    private String username;
    @NotBlank(message = "campo obbligatorio")
    private String password;
}
