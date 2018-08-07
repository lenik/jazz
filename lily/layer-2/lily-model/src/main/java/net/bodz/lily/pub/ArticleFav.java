package net.bodz.lily.pub;

import javax.persistence.Table;

import net.bodz.lily.template.FavRecord;

@Table(name = "article_fav")
public class ArticleFav
        extends FavRecord {

    private static final long serialVersionUID = 1L;

    Article article;

    public ArticleFav() {
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

}
