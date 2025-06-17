package edacy.example.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    
    private Long userId;
    private List<CartItemDTO> items = new ArrayList<>();
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal shipping;
    private BigDecimal total;
    
    // Classe imbriquée pour les articles du panier
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartItemDTO {
        private Long productId;
        private String productName;
        private Integer quantity;
        private BigDecimal unitPrice;
        private BigDecimal subtotal;
        private String imageUrl;
    }
}