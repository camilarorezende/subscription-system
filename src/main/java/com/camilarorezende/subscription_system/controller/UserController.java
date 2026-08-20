package com.camilarorezende.subscription_system.controller;

import com.camilarorezende.subscription_system.dto.user.UserCreateDTO;
import com.camilarorezende.subscription_system.dto.user.UserResponseDTO;
import com.camilarorezende.subscription_system.dto.user.UserUpdateDTO;
import com.camilarorezende.subscription_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> criarUsuario(@RequestBody UserCreateDTO userCreateDTO) {
        UserResponseDTO userResponseDTO= userService.criar(userCreateDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> atualizarUsuario(@PathVariable Long id, @RequestBody UserUpdateDTO userUpdateDTO) {
        UserResponseDTO userResponseDTO = userService.atualizar(id, userUpdateDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listarUsuarios() {
        List<UserResponseDTO> userResponseDTOS = userService.listar();
        return ResponseEntity.ok(userResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> buscarUsuario(@PathVariable Long id) {
        UserResponseDTO userResponseDTO = userService.buscar(id);
        return ResponseEntity.ok(userResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        userService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
