package net.bodz.lily.criterion;

public class FieldLike
        extends FieldCriterion {

    String pattern;

    public FieldLike(String fieldName, boolean not, String pattern) {
        super(fieldName, not);
        this.pattern = pattern;
    }

}
