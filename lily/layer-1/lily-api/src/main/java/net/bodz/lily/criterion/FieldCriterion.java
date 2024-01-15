package net.bodz.lily.criterion;

import net.bodz.bas.t.criteria.ICriterion;

public abstract class FieldCriterion
        implements
            ICriterion {

    String fieldName;
    boolean not;

    public FieldCriterion(String fieldName, boolean not) {
        this.fieldName = fieldName;
        this.not = not;
    }

}
