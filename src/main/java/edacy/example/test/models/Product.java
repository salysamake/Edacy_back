package edacy.example.test.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "seq_prod", initialValue = 100, sequenceName = "seq_prod")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_prod")
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Column(length = 2000)
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @Positive
    private Integer stockQuantity;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}