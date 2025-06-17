package edacy.example.test.repositories;


import edacy.example.test.models.Category;
import edacy.example.test.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> , QuerydslPredicateExecutor <Product>{
    
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
    
    Page<Product> findByCategory(Category category, Pageable pageable);
    

    
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    Page<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
    
    @Query("SELECT p FROM Product p WHERE p.stockQuantity > 0")
    Page<Product> findInStock(Pageable pageable);
    
    @Query(value = "SELECT * FROM products ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Product> findRandomProducts(int limit);
}