package edacy.example.test.dto;


import edacy.example.test.models.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    
    private Long id;
    private String orderNumber;
    private Long userId;
    private String userName;
    
    private List<OrderItemDTO> items = new ArrayList<>();
    
    private LocalDateTime orderDate;
    private Order.OrderStatus status;
    
    private BigDecimal subtotal;
    private BigDecimal taxAmount;
    private BigDecimal shippingAmount;
    private BigDecimal totalAmount;
    
    private AddressDTO shippingAddress;
    private AddressDTO billingAddress;
    
    private String paymentMethod;
    private String paymentTransactionId;
    
    private String trackingNumber;
    private String shippingMethod;
    
    private String customerNotes;
}