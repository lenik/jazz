package net.bodz.lily.pub.impl;

import net.bodz.lily.t.base.CoMessageMask;

/**
 * @see net.bodz.lily.pub.Post
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
