package edacy.example.test.services;


import edacy.example.test.dto.ProductDTO;
import edacy.example.test.models.Category;
import edacy.example.test.models.Product;
import edacy.example.test.repositories.CategoryRepository;
import edacy.example.test.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j

public class ProductService {


    private  final ProductRepository productRepository;
    

    private final CategoryRepository categoryRepository;
    



    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;

    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public Page<ProductDTO> getProductsPaginated(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(this::convertToDTO);
    }
    
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé avec l'ID: " + id));
        return convertToDTO(product);
    }
    
    public Page<ProductDTO> searchProducts(String keyword, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCase(keyword, pageable)
                .map(this::convertToDTO);
    }
    
    public Page<ProductDTO> getProductsByCategory(Long categoryId, Pageable pageable) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Catégorie non trouvée avec l'ID: " + categoryId));
        return productRepository.findByCategory(category, pageable)
                .map(this::convertToDTO);
    }
    

    

    
    public Page<ProductDTO> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        return productRepository.findByPriceRange(minPrice, maxPrice, pageable)
                .map(this::convertToDTO);
    }
    
    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO) {
        try {
            Product product = convertToEntity(productDTO);
            log.info("Create new product: " + product);
            Optional<Category> category = categoryRepository.findByName(productDTO.getCategoryName());
            if (!category.isPresent()) {
                throw new RuntimeException("category not found");

            }
            product.setCategory(category.get());
            Product savedProduct = productRepository.save(product);
            return convertToDTO(savedProduct);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    
    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Produit non trouvé avec l'ID: " + id);
        }
        
        Product product = convertToEntity(productDTO);
        product.setId(id);
        Product updatedProduct = productRepository.save(product);
        return convertToDTO(updatedProduct);
    }
    
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Produit non trouvé avec l'ID: " + id);
        }
        productRepository.deleteById(id);
    }
    
    // Conversion entre DTO et entité
    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setImageUrl(product.getImageUrl());

        


        

        
        return dto;
    }
    
    private Product convertToEntity(ProductDTO dto) {
        Product product = new Product();
        //product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setImageUrl(dto.getImageUrl());

        

        


        
        return product;
    }
}