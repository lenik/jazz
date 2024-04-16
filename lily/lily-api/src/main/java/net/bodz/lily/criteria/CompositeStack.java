package net.bodz.lily.criteria;

import net.bodz.bas.t.list.ArrayStack;
import net.bodz.lily.criterion.Composite;

public class CompositeStack
        extends ArrayStack<CriterionStackFrame> {

    private static final long serialVersionUID = 1L;

    public void push(Composite item) {
        CriterionStackFrame frame = new CriterionStackFrame(item, ReduceMode.APPEND);
        super.push(frame);
    }

    public void push(Composite item, ReduceMode reduceMode) {
        CriterionStackFrame frame = new CriterionStackFrame(item, reduceMode);
        super.push(frame);
    }

}
