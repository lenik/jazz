package net.bodz.lily.criterion;

import java.util.Arrays;
import java.util.Collection;

public class FieldIn<T>
        extends FieldCriterion {

    Collection<T> values;

    @SafeVarargs
    public FieldIn(String fieldName, boolean not, T... values) {
        super(fieldName, not);
        if (values == null)
            throw new NullPointerException("values");
        this.values = Arrays.asList(values);
    }

    public FieldIn(String fieldName, boolean not, Collection<T> values) {
        super(fieldName, not);
        if (values == null)
            throw new NullPointerException("values");
        this.values = values;
    }

    @Override
    public void accept(ICriterionVisitor visitor) {
        visitor.fieldIn(this);
    }

}
