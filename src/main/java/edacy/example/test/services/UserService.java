package edacy.example.test.services;


import edacy.example.test.dto.UserDTO;
import edacy.example.test.models.User;
import edacy.example.test.repositories.RoleRepository;
import edacy.example.test.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec l'ID: " + id));
        return convertToDTO(user);
    }
    
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur: " + username));
        return convertToDTO(user);
    }
    
    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec l'ID: " + id));
        
        // Mettre à jour les champs modifiables
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        
        // Ne pas mettre à jour le mot de passe ici - cela devrait être fait dans un service séparé
        
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }
    
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Utilisateur non trouvé avec l'ID: " + id);
        }
        userRepository.deleteById(id);
    }
    
    // Conversion entre DTO et entité
    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhoneNumber(user.getPhoneNumber());
        
        // Convertir les rôles
        Set<String> roles = user.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());
        
        dto.setRoles(roles);
        
        // Convertir les adresses (à implémenter)
        
        return dto;
    }
}