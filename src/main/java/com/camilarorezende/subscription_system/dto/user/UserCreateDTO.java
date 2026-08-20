package com.camilarorezende.subscription_system.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {

    @NotBlank(message = "nome obrigatório")
    private String nome;

    @NotBlank(message = "email obrigatório")
    @Email(message = "email inválido")
    private String email;

    @NotBlank(message = "senha obrigatória")
    private String senha;
}
