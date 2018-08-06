package net.bodz.violet.pub;

import javax.persistence.Table;

import net.bodz.lily.template.VoteRecord;

@Table(name = "article_vote")
public class ArticleVote
        extends VoteRecord {

    private static final long serialVersionUID = 1L;

    Article article;

    public ArticleVote() {
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

}
