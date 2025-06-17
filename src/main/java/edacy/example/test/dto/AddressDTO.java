package edacy.example.test.dto;


import edacy.example.test.models.Address;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    
    private Long id;
    
    @NotBlank(message = "L'adresse est obligatoire")
    private String streetAddress;
    
    private String streetAddressLine2;
    
    @NotBlank(message = "La ville est obligatoire")
    private String city;
    
    @NotBlank(message = "La région/département est obligatoire")
    private String state;
    
    @NotBlank(message = "Le code postal est obligatoire")
    private String postalCode;
    
    @NotBlank(message = "Le pays est obligatoire")
    private String country;
    
    private Boolean isDefault = false;
    
    private Address.AddressType addressType = Address.AddressType.BOTH;
}