package com.camilarorezende.subscription_system.dto.subscription;

import com.camilarorezende.subscription_system.models.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionUpdateDTO {

    @NotNull(message = "status é obrigatório")
    private Status status;
}
