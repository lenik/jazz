package net.bodz.lily.criteria;

import java.lang.reflect.Constructor;

import net.bodz.bas.t.criteria.ICriterion;
import net.bodz.lily.criterion.Disjunction;
import net.bodz.lily.criterion.Junction;

public class CriteriaBuilder<fin_target, end_target, self_t extends CriteriaBuilder<fin_target, end_target, self_t>>
        implements
            ICriteriaBuilder<end_target, self_t>,
            IReceiver<ICriterion> {

    Junction root = new Junction();

    fin_target finTarget;
    fin_target endTarget;

    public CriteriaBuilder(fin_target finTarget, fin_target endTarget) {
        this.finTarget = finTarget;
        this.endTarget = endTarget;
    }

    <FT, ET> self_t newInstance(FT finTarget, ET endTarget) {
        Class<? extends Object> finClass = finTarget.getClass();
        Class<? extends Object> endClass = endTarget.getClass();
        Constructor<?> ctor;
        try {
            ctor = getClass().getConstructor(finClass, endClass);

            @SuppressWarnings("unchecked")
            self_t newInstance = (self_t) ctor.newInstance(finTarget, endTarget);
            return newInstance;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public self_t or() {
        Disjunction disj = new Disjunction();
        root.add(disj);
        OrCriteriaBuilder or = new OrCriteriaBuilder();
        self_t newInstance = newInstance(or, this);
        return newInstance;
    }

    @Override
    public self_t end() {
        return endTarget;
    }

    @Override
    public void accept(ICriterion value) {
        root.add(value);
    }

    @SuppressWarnings("unchecked")
    protected NumberFieldCriteriaBuilder<self_t> number(String fieldName) {
        return new NumberFieldCriteriaBuilder<self_t>(fieldName, (self_t) this, this);
    }

    @SuppressWarnings("unchecked")
    protected StringFieldCriteriaBuilder<self_t> string(String fieldName) {
        return new StringFieldCriteriaBuilder<self_t>(fieldName, (self_t) this, this);
    }

}
