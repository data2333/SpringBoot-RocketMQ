package demo.springboot.repository;

import demo.springboot.domain.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sun on 18-6-30.
 */
public interface ResourcesService extends JpaRepository<Resources,Integer>{
    @Query("select resource from Resources resource where resource.id=:id")
    List<Resources> getResources(@Param("id") Integer id);
}
