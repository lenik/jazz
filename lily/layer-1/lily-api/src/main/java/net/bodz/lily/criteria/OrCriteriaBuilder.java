package net.bodz.lily.criteria;

public class OrCriteriaBuilder<fin_target, end_target>
        extends CriteriaBuilder<fin_target, end_target, OrCriteriaBuilder<fin_target, end_target>> {

    public OrCriteriaBuilder(fin_target finTarget, fin_target endTarget) {
        super(finTarget, endTarget);
    }

}
