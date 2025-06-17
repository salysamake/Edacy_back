package edacy.example.test.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    
    private Long id;
    
    @NotBlank(message = "Le nom du produit est obligatoire")
    private String name;
    
    @NotBlank(message = "La description du produit est obligatoire")
    private String description;
    
    @NotNull(message = "Le prix du produit est obligatoire")
    @Positive(message = "Le prix doit être positif")
    private BigDecimal price;
    
    @NotNull(message = "La quantité en stock est obligatoire")
    @Positive(message = "La quantité en stock doit être positive")
    private Integer stockQuantity;
    
    private String imageUrl;
    

    private String categoryName;
//    private Long categoryId;
    
//    private Set<TagDTO> tags = new HashSet<>();
//
//    private Boolean featured = false;
//    private Boolean onSale = false;
//
//    private String technicalSpecs;
//    private String manufacturer;
//    private String modelNumber;
//    private Integer warrantyMonths;
}