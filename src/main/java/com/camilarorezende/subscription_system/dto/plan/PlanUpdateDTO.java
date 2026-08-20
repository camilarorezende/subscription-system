package com.camilarorezende.subscription_system.dto.plan;

import com.camilarorezende.subscription_system.models.BillingCycle;
import com.camilarorezende.subscription_system.models.PlanLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanUpdateDTO {

    private PlanLevel level;

    private BigDecimal preco;

    private BillingCycle billingCycle;


}
