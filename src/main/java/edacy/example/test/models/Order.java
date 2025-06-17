package edacy.example.test.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String orderNumber;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();
    
    private LocalDateTime orderDate;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    private BigDecimal subtotal;
    private BigDecimal taxAmount;
    private BigDecimal shippingAmount;
    private BigDecimal totalAmount;
    
    @ManyToOne
    @JoinColumn(name = "shipping_address_id")
    private Address shippingAddress;
    
    @ManyToOne
    @JoinColumn(name = "billing_address_id")
    private Address billingAddress;
    
    private String paymentMethod;
    private String paymentTransactionId;
    
    private String trackingNumber;
    private String shippingMethod;
    
    @Column(length = 1000)
    private String customerNotes;
    
    public enum OrderStatus {
        PENDING,
        PROCESSING,
        SHIPPED,
        DELIVERED,
        CANCELLED,
        REFUNDED
    }
}