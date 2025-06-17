package edacy.example.test.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String streetAddress;
    
    private String streetAddressLine2;
    
    @NotBlank
    private String city;
    
    @NotBlank
    private String state;
    
    @NotBlank
    private String postalCode;
    
    @NotBlank
    private String country;
    
    private Boolean isDefault = false;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Enumerated(EnumType.STRING)
    private AddressType addressType;
    
    public enum AddressType {
        BILLING,
        SHIPPING,
        BOTH
    }
}