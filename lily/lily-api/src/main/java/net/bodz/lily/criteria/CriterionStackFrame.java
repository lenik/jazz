package net.bodz.lily.criteria;

import net.bodz.lily.criterion.Composite;

public class CriterionStackFrame {

    public Composite composite;
    public ReduceMode mode;

    public CriterionStackFrame(Composite composite, ReduceMode mode) {
        this.composite = composite;
        this.mode = mode;
    }

}
