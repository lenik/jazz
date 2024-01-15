package net.bodz.lily.criteria;

import java.util.List;

import net.bodz.bas.db.ibatis.IGenericMapper;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.err.IllegalUsageError;
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

    protected IStack<Composite> stack = new ArrayStack<>();

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
        Not not = new Not();
        // not.add(other);
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
            // disj.add(other);
        } else {
            Disjunction disj = new Disjunction();
            disj.add(top);
            // disj.add(other);
            Composite restore = defaultCombine();
            restore.add(disj);
            stack.replaceTop(restore);
        }

        stack.push(other);

        return _this();
    }

    @Override
    public self_t end() {
        Composite other = stack.pop();
        Composite top = stack.top();
        if (top instanceof Disjunction) {
            top.add(other);
        } else if (top instanceof Junction) { // restore-and
            ICriterion last = top.getLast();
            if (last instanceof Composite) {
                Composite lastComposite = (Composite) last;
                lastComposite.add(other);
            } else
                throw new IllegalUsageError("top.last isn't composite");
        } else
            throw new IllegalUsageError("unexpected top type");
        return _this();
    }

    @Override
    public void receive(ICriterion value) {
        Composite top = stack.top();
        top.add(value);
    }

    protected <T extends Number> NumberField<T> number(String fieldName, Class<T> type) {
        return new NumberField<T>(fieldName, type);
    }

    protected StringField string(String fieldName) {
        return new StringField(fieldName);
    }

    protected BooleanField bool(String fieldName) {
        return new BooleanField(fieldName);
    }

    protected <T> DateField<T> date(String fieldName, Class<T> type) {
        return new DateField<T>(fieldName, type);
    }

    protected class NumberField<T extends Number>
            extends NumberFieldCriteriaBuilder<self_t, T> {

        @SuppressWarnings("unchecked")
        public NumberField(String fieldName, Class<T> type) {
            super(fieldName, type, (self_t) CriteriaBuilder.this, CriteriaBuilder.this);
        }

    }

    protected class StringField
            extends StringFieldCriteriaBuilder<self_t> {

        @SuppressWarnings("unchecked")
        public StringField(String fieldName) {
            super(fieldName, (self_t) CriteriaBuilder.this, CriteriaBuilder.this);
        }

    }

    protected class BooleanField
            extends BooleanFieldCriteriaBuilder<self_t> {

        @SuppressWarnings("unchecked")
        public BooleanField(String fieldName) {
            super(fieldName, (self_t) CriteriaBuilder.this, CriteriaBuilder.this);
        }

    }

    protected class DateField<T>
            extends DateFieldCriteriaBuilder<self_t, T> {

        @SuppressWarnings("unchecked")
        public DateField(String fieldName, Class<T> type) {
            super(fieldName, type, (self_t) CriteriaBuilder.this, CriteriaBuilder.this);
        }

    }

    public <T> List<T> filter(IGenericMapper<T, self_t> mapper) {
        return filter(mapper, null);
    }

    public <T> List<T> filter(IGenericMapper<T, self_t> mapper, SelectOptions options) {
        return mapper.filter(_this(), options);
    }

}
