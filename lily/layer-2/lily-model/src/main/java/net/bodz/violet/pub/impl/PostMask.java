package net.bodz.violet.pub.impl;

import net.bodz.lily.model.mx.CoMessageMask;

/**
 * @see net.bodz.violet.pub.Post
 */
public class PostMask
        extends CoMessageMask {

    Integer categoryId;
    Long parentId;

    @Override
    public Integer getCategoryId() {
        return categoryId;
    }

    @Override
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}
