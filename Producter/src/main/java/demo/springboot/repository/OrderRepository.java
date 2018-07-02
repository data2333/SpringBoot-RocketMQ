package demo.springboot.repository;

import demo.springboot.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sun on 18-7-2.
 */
public interface OrderRepository extends JpaRepository<Order,Long>{

}
