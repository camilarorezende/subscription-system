package com.camilarorezende.subscription_system.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camilarorezende.subscription_system.dto.plan.PlanCreateDTO;
import com.camilarorezende.subscription_system.dto.plan.PlanResponseDTO;
import com.camilarorezende.subscription_system.dto.plan.PlanUpdateDTO;
import com.camilarorezende.subscription_system.exceptions.PlanNotFoundException;
import com.camilarorezende.subscription_system.exceptions.PlanoComAssinaturaAtivaException;
import com.camilarorezende.subscription_system.models.Plan;
import com.camilarorezende.subscription_system.models.Status;
import com.camilarorezende.subscription_system.repository.PlanRepository;
import com.camilarorezende.subscription_system.repository.SubscriptionRepository;

@Service
public class PlanService {

    @Autowired
    PlanRepository planRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    ModelMapper modelMapper;

    public PlanResponseDTO criar(PlanCreateDTO planCreateDTO) {
        Plan plan = modelMapper.map(planCreateDTO, Plan.class);
        planRepository.save(plan);
        return modelMapper.map(plan, PlanResponseDTO.class);
    }

    public List<PlanResponseDTO> listar() {
        List<Plan> plans = planRepository.findAll();
        return plans.stream()
                .map(plan -> modelMapper.map(plan, PlanResponseDTO.class))
                .toList();
    }

    public PlanResponseDTO atualizar(Long id, PlanUpdateDTO planUpdateDTO) {
        Plan plan = planRepository.findById(id).orElseThrow(PlanNotFoundException::new);
        modelMapper.map(planUpdateDTO, plan);
        planRepository.save(plan);
        return modelMapper.map(plan, PlanResponseDTO.class);
    }

    public PlanResponseDTO buscar(Long id) {
        Plan plan = planRepository.findById(id).orElseThrow(PlanNotFoundException::new);
        return modelMapper.map(plan, PlanResponseDTO.class);
    }

    public void deletar(Long id) {
        Plan plan = planRepository.findById(id).orElseThrow(PlanNotFoundException::new);
        if (subscriptionRepository.existsByPlanIdAndStatus(id, Status.ATIVO)) {
            throw new PlanoComAssinaturaAtivaException();
        }
        planRepository.delete(plan);
    }
}
