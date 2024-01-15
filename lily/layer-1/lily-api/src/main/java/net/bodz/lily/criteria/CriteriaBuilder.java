package net.bodz.lily.criteria;

import net.bodz.bas.t.list.ArrayStack;
import net.bodz.bas.t.list.IStack;
import net.bodz.lily.criterion.Composite;
import net.bodz.lily.criterion.Disjunction;
import net.bodz.lily.criterion.ICriterion;
import net.bodz.lily.criterion.Junction;
import net.bodz.lily.criterion.Not;

public class CriteriaBuilder<self_t extends CriteriaBuilder<self_t>>
        implements
            ICriteriaBuilder<self_t>,
            IReceiver<ICriterion> {

    IStack<Composite> stack = new ArrayStack<>();

    public CriteriaBuilder() {
        stack.push(new Junction());
    }

    @SuppressWarnings("unchecked")
    private final self_t _this() {
        return (self_t) this;
    }

    @Override
    public ICriterion get() {
        return stack.top();
    }

    Composite defaultCombine() {
        return new Junction();
    }

    @Override
    public self_t not() {
        Composite top = stack.top();
        Composite other = defaultCombine();
        Not not = new Not(other);
        top.add(not);

        stack.push(other);

        return _this();
    }

    @Override
    public self_t or() {
        Composite other = defaultCombine();

        Composite top = stack.top();
        if (top instanceof Disjunction) {
            Disjunction disj = (Disjunction) top;
            disj.add(other);
        } else {
            Disjunction disj = new Disjunction();
            disj.add(top);
            disj.add(other);
            Composite restore = defaultCombine();
            restore.add(disj);
            stack.replaceTop(restore);
        }

        stack.push(other);

        return _this();
    }

    @Override
    public self_t end() {
        stack.pop();
        return _this();
    }

    @Override
    public void accept(ICriterion value) {
        Composite top = stack.top();
        top.add(value);
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
