package com.camilarorezende.subscription_system.controller;

import com.camilarorezende.subscription_system.dto.plan.PlanCreateDTO;
import com.camilarorezende.subscription_system.dto.plan.PlanResponseDTO;
import com.camilarorezende.subscription_system.dto.plan.PlanUpdateDTO;
import com.camilarorezende.subscription_system.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
public class PlanController {

    @Autowired
    PlanService planService;

    @PostMapping
    public ResponseEntity<PlanResponseDTO> criarPlano(@RequestBody PlanCreateDTO planCreateDTO) {
        PlanResponseDTO planResponseDTO = planService.criar(planCreateDTO);
        return ResponseEntity.ok(planResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanResponseDTO> atualizarPlano(@PathVariable Long id, @RequestBody PlanUpdateDTO planUpdateDTO) {
        PlanResponseDTO planResponseDTO = planService.atualizar(id, planUpdateDTO);
        return ResponseEntity.ok(planResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PlanResponseDTO>> listarPlanos() {
        List<PlanResponseDTO> planResponseDTOS = planService.listar();
        return ResponseEntity.ok(planResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanResponseDTO> buscarPlano(@PathVariable Long id) {
        PlanResponseDTO planResponseDTO = planService.buscar(id);
        return ResponseEntity.ok(planResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPlano(@PathVariable Long id) {
        planService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
