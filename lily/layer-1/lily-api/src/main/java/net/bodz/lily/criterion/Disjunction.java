package net.bodz.lily.criterion;

public class Disjunction
        extends Composite {

    @Override
    public void accept(ICriterionVisitor visitor) {
        visitor.disjunction(this);
    }

}
