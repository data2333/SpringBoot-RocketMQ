package demo.springboot.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sun on 18-6-26.
 */
@Entity
@Table(name = "articles")
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "article_id")
    private int articleId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "title")

    private String title;
    @Column(name = "category")
    private String category;

    // 省略getter/setter
}
