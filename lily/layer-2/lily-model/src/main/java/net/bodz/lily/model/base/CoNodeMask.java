package net.bodz.lily.model.base;

import net.bodz.bas.t.range.IntegerRange;

public class CoNodeMask
        extends CoObjectMask {

    public Long parentId;
    public Integer maxDepth;
    final IntegerRange depthRange = new IntegerRange();

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

    public IntegerRange getDepthRange() {
        return depthRange;
    }

}
