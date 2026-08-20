package com.camilarorezende.subscription_system.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camilarorezende.subscription_system.dto.subscription.SubscriptionCreateDTO;
import com.camilarorezende.subscription_system.dto.subscription.SubscriptionResponseDTO;
import com.camilarorezende.subscription_system.dto.subscription.SubscriptionUpdateDTO;
import com.camilarorezende.subscription_system.exceptions.PlanNotFoundException;
import com.camilarorezende.subscription_system.exceptions.SubscriptionNotFoundException;
import com.camilarorezende.subscription_system.exceptions.UserNotFoundException;
import com.camilarorezende.subscription_system.models.Plan;
import com.camilarorezende.subscription_system.models.Status;
import com.camilarorezende.subscription_system.models.Subscription;
import com.camilarorezende.subscription_system.models.User;
import com.camilarorezende.subscription_system.repository.PlanRepository;
import com.camilarorezende.subscription_system.repository.SubscriptionRepository;
import com.camilarorezende.subscription_system.repository.UserRepository;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    PlanRepository planRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    public SubscriptionResponseDTO criar(SubscriptionCreateDTO subscriptionCreateDTO) {
        User user = userRepository.findById(subscriptionCreateDTO.getUserId()).orElseThrow(UserNotFoundException::new);
        Plan plan = planRepository.findById(subscriptionCreateDTO.getPlanId()).orElseThrow(PlanNotFoundException::new);

        if (subscriptionRepository.existsByUserIdAndPlanIdAndStatus(subscriptionCreateDTO.getUserId(), subscriptionCreateDTO.getPlanId(), Status.ATIVO)) {
            throw new IllegalStateException("Usuário já possui assinatura ativa para este plano.");
        }

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setPlan(plan);
        subscription.setStartDate(LocalDate.now());
        subscription.setNextBillingDate(calcularProximaCobranca(plan));
        subscription.setStatus(Status.ATIVO);
        subscriptionRepository.save(subscription);

        return modelMapper.map(subscription, SubscriptionResponseDTO.class);
    }

    public List<SubscriptionResponseDTO> listar() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        return subscriptions.stream()
                .map(subscription -> modelMapper.map(subscription, SubscriptionResponseDTO.class))
                .toList();
    }

    public SubscriptionResponseDTO atualizar(Long id, SubscriptionUpdateDTO subscriptionUpdateDTO) {
        Subscription subscription = subscriptionRepository.findById(id).orElseThrow(SubscriptionNotFoundException::new);
        if (subscription.getStatus() == Status.CANCELADO || subscription.getStatus() == Status.EXPIRADO) {
            throw new IllegalStateException("A assinatura não pode ser atualizada pois está cancelada ou expirada.");
        }
        modelMapper.map(subscriptionUpdateDTO, subscription);
        subscriptionRepository.save(subscription);
        return modelMapper.map(subscription, SubscriptionResponseDTO.class);
    }

    public SubscriptionResponseDTO buscar(Long id) {
        Subscription subscription = subscriptionRepository.findById(id).orElseThrow(SubscriptionNotFoundException::new);
        return modelMapper.map(subscription, SubscriptionResponseDTO.class);
    }

    public void deletar(Long id) {
        Subscription subscription = subscriptionRepository.findById(id).orElseThrow(SubscriptionNotFoundException::new);
        if (subscription.getStatus() == Status.ATIVO) {
            throw new IllegalStateException("A assinatura não pode ser deletada pois está ativa.");
        }
        subscriptionRepository.delete(subscription);
    }

    public void cancelar(Long id) {
        Subscription subscription = subscriptionRepository.findById(id).orElseThrow(SubscriptionNotFoundException::new);
        if (subscription.getStatus() != Status.ATIVO) {
            throw new IllegalStateException("Apenas assinaturas ativas podem ser canceladas.");
        }
        subscription.setStatus(Status.CANCELADO);
        subscription.setEndDate(LocalDate.now());
        subscriptionRepository.save(subscription);
    }

    private LocalDate calcularProximaCobranca(Plan plan) {
        switch (plan.getBillingCycle()) {
            case WEEKLY -> {
                return LocalDate.now().plusWeeks(1);
            }
            case MONTHLY -> {
                return LocalDate.now().plusMonths(1);
            }
            case YEARLY -> {
                return LocalDate.now().plusYears(1);
            }
            default -> throw new IllegalStateException("Ciclo inválido.");
        }
    }

    private BigDecimal calcularTotalAtivoPorUsuario(Long id) {

        List<Subscription> subscriptions = subscriptionRepository.findByUserIdAndStatus(id, Status.ATIVO);

        BigDecimal total = BigDecimal.ZERO;

        for (Subscription subscription : subscriptions) {
            BigDecimal preco = subscription.getPlan().getPreco();
            total = total.add(preco);
        }
        return total;

    }

}
