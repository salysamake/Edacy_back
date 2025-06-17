package edacy.example.test.repositories;


import edacy.example.test.models.Order;
import edacy.example.test.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    Page<Order> findByUser(User user, Pageable pageable);
    
    Optional<Order> findByOrderNumber(String orderNumber);
    
    List<Order> findByStatus(Order.OrderStatus status);
    
    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    List<Order> findByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("SELECT COUNT(o) FROM Order o WHERE o.status = :status")
    Long countByStatus(Order.OrderStatus status);
}