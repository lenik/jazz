package net.bodz.lily.criteria;

import java.math.BigDecimal;
import java.math.BigInteger;
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

    protected IntegerField integer(String fieldName) {
        return new IntegerField(fieldName);
    }

    protected ByteField _byte(String fieldName) {
        return new ByteField(fieldName);
    }

    protected ShortField _short(String fieldName) {
        return new ShortField(fieldName);
    }

    protected LongField _long(String fieldName) {
        return new LongField(fieldName);
    }

    protected FloatField _float(String fieldName) {
        return new FloatField(fieldName);
    }

    protected DoubleField _double(String fieldName) {
        return new DoubleField(fieldName);
    }

    protected BigIntegerField bigInteger(String fieldName) {
        return new BigIntegerField(fieldName);
    }

    protected BigDecimalField bigDecimal(String fieldName) {
        return new BigDecimalField(fieldName);
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

    protected class IntegerField
            extends NumberField<Integer> {

        public IntegerField(String fieldName) {
            super(fieldName, Integer.class);
        }

    }

    protected class ByteField
            extends NumberField<Byte> {

        public ByteField(String fieldName) {
            super(fieldName, Byte.class);
        }

    }

    protected class ShortField
            extends NumberField<Short> {

        public ShortField(String fieldName) {
            super(fieldName, Short.class);
        }

    }

    protected class LongField
            extends NumberField<Long> {

        public LongField(String fieldName) {
            super(fieldName, Long.class);
        }

    }

    protected class FloatField
            extends NumberField<Float> {

        public FloatField(String fieldName) {
            super(fieldName, Float.class);
        }

    }

    protected class DoubleField
            extends NumberField<Double> {

        public DoubleField(String fieldName) {
            super(fieldName, Double.class);
        }

    }

    protected class BigIntegerField
            extends NumberField<BigInteger> {

        public BigIntegerField(String fieldName) {
            super(fieldName, BigInteger.class);
        }

    }

    protected class BigDecimalField
            extends NumberField<BigDecimal> {

        public BigDecimalField(String fieldName) {
            super(fieldName, BigDecimal.class);
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

    public <T> List<T> filter(IGenericMapper<T> mapper) {
        return filter(mapper, SelectOptions.ALL);
    }

    public <T> List<T> filter(IGenericMapper<T> mapper, SelectOptions options) {
        ICriterion criterion = get();
        return mapper.filter(criterion, options);
    }

}
