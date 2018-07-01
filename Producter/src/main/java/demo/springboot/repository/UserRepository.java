package demo.springboot.repository;

import demo.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by sun on 18-6-30.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select user from User user where user.username=:username")
    User selectByUsername(@Param("username") String username);
}
