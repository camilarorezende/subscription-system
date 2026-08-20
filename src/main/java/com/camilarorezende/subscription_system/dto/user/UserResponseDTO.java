package com.camilarorezende.subscription_system.dto.user;

import com.camilarorezende.subscription_system.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;

    private String nome;

    private String email;

    public UserResponseDTO (User user) {

        this.id = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();

    }
}
