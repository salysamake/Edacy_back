package edacy.example.test.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    
    private Long id;
    private Long productId;
    
    @NotNull(message = "La quantité est obligatoire")
    @Positive(message = "La quantité doit être positive")
    private Integer quantity;
    
    private String productName;
    private String productSku;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
    private String imageUrl;
}