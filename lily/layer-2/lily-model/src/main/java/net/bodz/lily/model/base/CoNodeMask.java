package net.bodz.lily.model.base;

import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;

public class CoNodeMask
        extends CoObjectMask {

    Long parentId;
    LongRange parentIdRange;

    public Integer maxDepth;
    final IntegerRange depthRange = new IntegerRange();

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public LongRange getParentIdRange() {
        return parentIdRange;
    }

    public void setParentIdRange(LongRange parentIdRange) {
        this.parentIdRange = parentIdRange;
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

    Integer id;
    IntegerRange idRange;

    public Integer getId() {
        return id;
    }

    public IntegerRange getIdRange() {
        return idRange;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdRange(IntegerRange idRange) {
        this.idRange = idRange;
    }

}
