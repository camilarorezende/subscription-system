package com.camilarorezende.subscription_system.dto.user;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {

    private String nome;

    @Email(message = "email inválido")
    private String email;

    private String senha;

}
