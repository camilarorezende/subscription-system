package com.camilarorezende.subscription_system.controller;

import com.camilarorezende.subscription_system.dto.subscription.SubscriptionCreateDTO;
import com.camilarorezende.subscription_system.dto.subscription.SubscriptionResponseDTO;
import com.camilarorezende.subscription_system.dto.subscription.SubscriptionUpdateDTO;
import com.camilarorezende.subscription_system.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<SubscriptionResponseDTO> criarAssinatura(@RequestBody SubscriptionCreateDTO subscriptionCreateDTO) {
        SubscriptionResponseDTO subscriptionResponseDTO = subscriptionService.criar(subscriptionCreateDTO);
        return ResponseEntity.ok(subscriptionResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionResponseDTO> atualizarAssinatura(@PathVariable Long id, @RequestBody SubscriptionUpdateDTO subscriptionUpdateDTO) {
        SubscriptionResponseDTO subscriptionResponseDTO = subscriptionService.atualizar(id, subscriptionUpdateDTO);
        return ResponseEntity.ok(subscriptionResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionResponseDTO>> listarAssinaturas() {
        List<SubscriptionResponseDTO> subscriptionResponseDTOS = subscriptionService.listar();
        return ResponseEntity.ok(subscriptionResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResponseDTO> buscarAssinatura(@PathVariable Long id) {
        SubscriptionResponseDTO subscriptionResponseDTO = subscriptionService.buscar(id);
        return ResponseEntity.ok(subscriptionResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAssinatura(@PathVariable Long id) {
        subscriptionService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelarAssinatura(@PathVariable Long id) {
        subscriptionService.cancelar(id);
        return ResponseEntity.ok().build();
    }

}
