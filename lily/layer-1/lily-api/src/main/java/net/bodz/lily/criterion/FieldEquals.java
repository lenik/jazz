package net.bodz.lily.criterion;

public class FieldEquals<T>
        extends FieldCriterion {

    T value;

    public FieldEquals(String fieldName, boolean not, T value) {
        super(fieldName, not);
        this.value = value;
    }

    @Override
    public void accept(ICriterionVisitor visitor) {
        visitor.fieldEquals(this);
    }

}
