package demo.springboot;

import demo.springboot.service.BookService;
import demo.springboot.service.impl.BookServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 应用启动类
 *
 * Created by bysocket on 26/09/2017.
 */
@SpringBootApplication
public class Customer {
    public static void main(String[] args) {
        SpringApplication.run(Customer.class, args);
    }
}
