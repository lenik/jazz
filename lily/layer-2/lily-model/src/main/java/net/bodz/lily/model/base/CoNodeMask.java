package net.bodz.lily.model.base;

import net.bodz.bas.t.range.IntRange;

public class CoNodeMask
        extends CoObjectMask {

    public Long parentId;
    public Integer maxDepth;
    final IntRange depthRange = new IntRange();

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

    public IntRange getDepthRange() {
        return depthRange;
    }

}
