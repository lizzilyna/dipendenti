package it.epicode.dipendenti.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "campo obbligatorio")
    private String username;
    @NotBlank(message = "campo obbligatorio")
    private String password;
}
