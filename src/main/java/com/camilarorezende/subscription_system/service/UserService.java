package com.camilarorezende.subscription_system.service;

import com.camilarorezende.subscription_system.dto.user.UserCreateDTO;
import com.camilarorezende.subscription_system.dto.user.UserResponseDTO;
import com.camilarorezende.subscription_system.dto.user.UserUpdateDTO;
import com.camilarorezende.subscription_system.exceptions.SenhaInvalidaException;
import com.camilarorezende.subscription_system.exceptions.UserNotFoundException;
import com.camilarorezende.subscription_system.exceptions.UsuarioComAssinaturaAtivaException;
import com.camilarorezende.subscription_system.models.Status;
import com.camilarorezende.subscription_system.models.User;
import com.camilarorezende.subscription_system.repository.SubscriptionRepository;
import com.camilarorezende.subscription_system.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    ModelMapper modelMapper;

    public UserResponseDTO criar(UserCreateDTO userCreateDTO) {
        if (userCreateDTO.getSenha().length() < 6) {
            throw new SenhaInvalidaException();
        }
        User user = modelMapper.map(userCreateDTO, User.class);
        userRepository.save(user);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public List<UserResponseDTO> listar() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .toList();
    }

    public UserResponseDTO atualizar(Long id, UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        modelMapper.map(userUpdateDTO, user);
        userRepository.save(user);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public UserResponseDTO buscar(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public void deletar(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if (subscriptionRepository.existsByUserIdAndStatus(id, Status.ATIVO)) {
            throw new UsuarioComAssinaturaAtivaException();
        }
        userRepository.delete(user);
    }


}
