package net.bodz.lily.criterion;

public class Not
        extends Criterion {

    Criterion criterion;

    public Not(Criterion criterion) {
        if (criterion == null)
            throw new NullPointerException("criterion");
        this.criterion = criterion;
    }

    @Override
    public void accept(ICriterionVisitor visitor) {
        visitor.not(this);
    }

}
