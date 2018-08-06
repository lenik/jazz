package net.bodz.violet.pub;

import javax.persistence.Table;

import net.bodz.lily.template.BackrefRecord;

@Table(name = "article_backref")
public class ArticleBackref
        extends BackrefRecord {

    private static final long serialVersionUID = 1L;

    Article article;

    public ArticleBackref() {
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(super.toString());
        return sb.toString();
    }

}
