package net.bodz.violet.pub.impl;

import net.bodz.lily.model.mx.CoMessageMask;

/**
 * @see net.bodz.violet.pub.ArticleTalk
 */
public class ArticleTalkMask
        extends CoMessageMask {

    Long articleId;
    Long parentId;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}
