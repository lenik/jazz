package net.bodz.lily.pub;

import javax.persistence.Table;

import net.bodz.lily.template.CoTalk;

/**
 * 文章讨论
 */
@Table(name = "article_msg")
public class ArticleTalk
        extends CoTalk<ArticleTalk> {

    private static final long serialVersionUID = 1L;

    Article article;

    public ArticleTalk() {
    }

    /**
     * 所属文章
     */
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

}
