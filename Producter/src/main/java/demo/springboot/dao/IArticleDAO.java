package demo.springboot.dao;

import demo.springboot.domain.Article;

import java.util.List;

/**
 * Created by sun on 18-6-26.
 */
public interface IArticleDAO {
    List<Article> getAllArticles();

    Article getArticleById(int articleId);

    void addArticle(Article article);

    void updateArticle(Article article);

    void deleteArticle(int articleId);

    boolean articleExists(String title, String category);
}
