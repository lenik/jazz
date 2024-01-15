package net.bodz.lily.criterion;

public class Junction
        extends Composite {

    @Override
    public void accept(ICriterionVisitor visitor) {
        visitor.junction(this);
    }

}
