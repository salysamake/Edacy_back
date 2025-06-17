package edacy.example.test.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    
    private Long id;
    
    @NotBlank(message = "Le nom de la catégorie est obligatoire")
    private String name;
    
    private String description;
    
    private Long parentId;
    
    private List<CategoryDTO> subCategories = new ArrayList<>();
}