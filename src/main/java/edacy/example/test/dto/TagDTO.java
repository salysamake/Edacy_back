package edacy.example.test.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {
    
    private Long id;
    
    @NotBlank(message = "Le nom du tag est obligatoire")
    private String name;
}