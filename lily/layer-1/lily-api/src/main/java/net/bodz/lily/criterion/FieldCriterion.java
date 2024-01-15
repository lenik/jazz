package net.bodz.lily.criterion;

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
