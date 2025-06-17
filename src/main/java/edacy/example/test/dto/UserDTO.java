package edacy.example.test.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    
    private Long id;
    
    @NotBlank(message = "Le nom d'utilisateur est obligatoire")
    @Size(min = 3, max = 20, message = "Le nom d'utilisateur doit contenir entre 3 et 20 caractères")
    private String username;
    
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;
    
    private String firstName;
    private String lastName;
    private String phoneNumber;
    
    private Set<String> roles = new HashSet<>();
    
    private Set<AddressDTO> addresses = new HashSet<>();
}