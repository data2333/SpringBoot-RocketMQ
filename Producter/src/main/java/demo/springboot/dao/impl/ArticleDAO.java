package demo.springboot.dao.impl;

import demo.springboot.dao.IArticleDAO;
import demo.springboot.domain.Article;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by sun on 18-6-26.
 */
@Repository
public class ArticleDAO implements IArticleDAO{

    @PersistenceContext
    private EntityManager entityManager;


    public Article getArticleById(int articleId) {
        return entityManager.find(Article.class, articleId);
    }

    @SuppressWarnings("unchecked")

    public List<Article> getAllArticles() {
        String hql = "FROM Article as atcl ORDER BY atcl.articleId";
        return (List<Article>) entityManager.createQuery(hql).getResultList();
    }


    public void addArticle(Article article) {
        entityManager.persist(article);
    }


    public void updateArticle(Article article) {
        Article artcl = getArticleById(article.getArticleId());
        artcl.setTitle(article.getTitle());
        artcl.setCategory(article.getCategory());
        entityManager.flush();
    }


    public void deleteArticle(int articleId) {
        entityManager.remove(getArticleById(articleId));
    }


    public boolean articleExists(String title, String category) {
        String hql = "FROM Article as atcl WHERE atcl.title = ? and atcl.category = ?";
        int count = entityManager.createQuery(hql).setParameter(1, title)
                .setParameter(2, category).getResultList().size();
        return count > 0;
    }
}
