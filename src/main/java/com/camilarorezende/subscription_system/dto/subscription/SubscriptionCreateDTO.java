package com.camilarorezende.subscription_system.dto.subscription;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionCreateDTO {

    @NotNull(message = "userId é obrigatório")
    private Long userId;

    @NotNull(message = "planId é obrigatório")
    private Long planId;
}
