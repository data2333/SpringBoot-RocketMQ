package demo.springboot.repository;

import demo.springboot.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sun on 18-6-26.
 */
public interface ArticleRepository extends JpaRepository<Article,Long> {

}
