package net.bodz.violet.pub;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.mx.CoMessage;

/**
 * 文章讨论
 */
@Table(name = "article_msg")
@IdType(Long.class)
public class ArticleTalk
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    Article article;
    ArticleTalk parent;

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

    /**
     * 父节点
     */
    public ArticleTalk getParent() {
        return parent;
    }

    public void setParent(ArticleTalk parent) {
        this.parent = parent;
    }

}
