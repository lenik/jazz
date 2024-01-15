package net.bodz.lily.criterion;

public class Not
        extends Composite {

    public Not() {
    }

    @Override
    public void accept(ICriterionVisitor visitor) {
        visitor.not(this);
    }

}
