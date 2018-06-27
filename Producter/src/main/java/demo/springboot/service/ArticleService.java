package demo.springboot.service;

import demo.springboot.dao.IArticleDAO;
import demo.springboot.domain.Article;
import demo.springboot.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by sun on 18-6-26.
 */
@Service
public class ArticleService {

    @Resource
    private IArticleDAO articleDAO;

    public Article getArticleById(int articleId) {
        Article obj = articleDAO.getArticleById(articleId);
        return obj;
    }
    @Autowired
    private ArticleRepository userRepository;
    public void getAllArticles() {
//        System.out.println("now shit");
        System.out.println(userRepository.findAll());
//        return articleDAO.getAllArticles();
    }

    public synchronized boolean addArticle(Article article) {
        if (articleDAO.articleExists(article.getTitle(), article.getCategory())) {
            return false;
        } else {
            articleDAO.addArticle(article);
            return true;
        }
    }

    public void updateArticle(Article article) {
        articleDAO.updateArticle(article);
    }

    public void deleteArticle(int articleId) {
        articleDAO.deleteArticle(articleId);
    }
}
