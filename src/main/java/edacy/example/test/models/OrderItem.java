package edacy.example.test.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    @NotNull
    @Positive
    private Integer quantity;
    
    @NotNull
    private BigDecimal unitPrice;
    
    private BigDecimal subtotal;
    
    // Informations stockées au moment de la commande au cas où le produit changerait
    private String productName;
    private String productSku;
}