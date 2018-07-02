package demo.springboot.web;

import demo.springboot.domain.Order;
import demo.springboot.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sun on 18-7-2.
 */
@Controller
@RequestMapping("/DB")
public class DBController {
    @Autowired
    OrderRepository orderRepository;
    @RequestMapping(value = "/Test")
    public void TestDB(){
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setUserId((long) i);
            order.setOrderId((long) i);
            orderRepository.save(order);
        }
        for (int i = 10; i < 20; i++) {
            Order order = new Order();
            order.setUserId((long) i + 1);
            order.setOrderId((long) i);
            orderRepository.save(order);
        }
    }

}
