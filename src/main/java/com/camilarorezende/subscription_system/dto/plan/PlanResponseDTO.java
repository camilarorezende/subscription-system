package com.camilarorezende.subscription_system.dto.plan;

import com.camilarorezende.subscription_system.models.Plan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanResponseDTO {

    private Long id;

    private String nomeServico;

    private String level;

    private BigDecimal preco;

    private String descricao;

    private String durationType;

    public PlanResponseDTO(Plan plan) {
        this.id = plan.getId();
        this.nomeServico = plan.getNomeServico();
        this.level = plan.getLevel().toString();
        this.preco = plan.getPreco();
        this.descricao = plan.getDescricao();
        this.durationType = plan.getBillingCycle().toString();
    }
}
