package com.data.dto.operation.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.dto.operation.Repository.UserRepository;
import com.data.dto.operation.entity.User;
import com.data.dto.operation.requestDTO.UserRequestDTO;
import com.data.dto.operation.responseDTO.UserResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());

        User savedUser = userRepository.save(user);

        return mapToResponseDTO(savedUser);
    }

    // Get a user by ID
    public Optional<UserResponseDTO> getUser(Long id) {
        return userRepository.findById(id).map(this::mapToResponseDTO);
    }

    // Get all users
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Update a user by ID
    public Optional<UserResponseDTO> updateUser(Long id, UserRequestDTO userRequestDTO) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(userRequestDTO.getName());
            existingUser.setEmail(userRequestDTO.getEmail());
            User updatedUser = userRepository.save(existingUser);
            return mapToResponseDTO(updatedUser);
        });
    }

    // Delete a user by ID
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Utility method to map entity to response DTO
    private UserResponseDTO mapToResponseDTO(User user) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setName(user.getName());
        responseDTO.setEmail(user.getEmail());
        return responseDTO;
    }
}
