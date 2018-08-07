package net.bodz.lily.pub.impl;

import net.bodz.lily.model.mx.CoMessageMask;

/**
 * @see net.bodz.lily.pub.Article
 */
public class ArticleMask
        extends CoMessageMask {

    Integer categoryId;

    @Override
    public Integer getCategoryId() {
        return categoryId;
    }

    @Override
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

}
