package com.camilarorezende.subscription_system.dto.subscription;

import com.camilarorezende.subscription_system.models.Status;
import com.camilarorezende.subscription_system.models.Subscription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionResponseDTO {

    private Long id;

    private Long userId;

    private String userName;

    private Long planId;

    private String planName;

    private LocalDate startDate;

    private LocalDate endDate;

    private Status status;

    public SubscriptionResponseDTO(Subscription subscription) {
        this.id = subscription.getId();
        this.userId = subscription.getUser().getId();
        this.userName = subscription.getUser().getNome();
        this.planId = subscription.getPlan().getId();
        this.planName = subscription.getPlan().getNomeServico();
        this.startDate = subscription.getStartDate();
        this.endDate = subscription.getEndDate();
        this.status = subscription.getStatus();
    }
}
