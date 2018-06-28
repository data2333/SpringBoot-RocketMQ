package demo.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Spring Boot 应用启动类
 * <p>
 * Created by bysocket on 26/09/2017.
 */
@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = {"demo.springboot"})
public class Productor {
    public static void main(String[] args) {
        SpringApplication.run(Productor.class, args);
    }
}
