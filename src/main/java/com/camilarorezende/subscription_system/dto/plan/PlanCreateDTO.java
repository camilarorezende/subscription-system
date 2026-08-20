package com.camilarorezende.subscription_system.dto.plan;

import com.camilarorezende.subscription_system.models.BillingCycle;
import com.camilarorezende.subscription_system.models.PlanLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanCreateDTO {

    @NotBlank(message = "nome do serviço obrigatorio")
    private String nomeServico;

    @NotNull(message = "nivel obrigatorio")
    private PlanLevel level;

    @NotNull(message = "preço obrigatorio")
    @Positive(message = "preço deve ser maior que zero")
    private BigDecimal preco;

    @NotBlank(message = "descricao obrigatoria")
    private String descricao;

    @NotNull(message = "duracao obrigatoria")
    private BillingCycle billingCycle;
}
