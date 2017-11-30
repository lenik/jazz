package net.bodz.violet.store.impl;

import net.bodz.lily.model.base.CoNodeMask;

public class RegionMask
        extends CoNodeMask {

    Integer asArtifactId;
    Integer forArtifactCategoryId;
    Integer forArtifactId;
    Integer categoryId;
    Integer tagId;

    public Integer getAsArtifactId() {
        return asArtifactId;
    }

    public void setAsArtifactId(Integer asArtifactId) {
        this.asArtifactId = asArtifactId;
    }

    public Integer getForArtifactCategoryId() {
        return forArtifactCategoryId;
    }

    public void setForArtifactCategoryId(Integer forArtifactCategoryId) {
        this.forArtifactCategoryId = forArtifactCategoryId;
    }

    public Integer getForArtifactId() {
        return forArtifactId;
    }

    public void setForArtifactId(Integer forArtifactId) {
        this.forArtifactId = forArtifactId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

}
