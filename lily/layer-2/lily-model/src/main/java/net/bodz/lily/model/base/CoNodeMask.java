package net.bodz.lily.model.base;

public class CoNodeMask
        extends CoObjectMask {

    public Long parentId;
    public Integer maxDepth;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(Integer maxDepth) {
        this.maxDepth = maxDepth;
    }

}
