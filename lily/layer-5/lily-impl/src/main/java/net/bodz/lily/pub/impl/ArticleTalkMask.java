package net.bodz.lily.pub.impl;

import net.bodz.lily.t.base.CoMessageMask;

/**
 * @see net.bodz.lily.pub.ArticleTalk
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
