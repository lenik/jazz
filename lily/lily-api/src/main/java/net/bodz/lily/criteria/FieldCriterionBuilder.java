package net.bodz.lily.criteria;

import java.util.Collection;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.lily.criterion.CompareMode;
import net.bodz.lily.criterion.Disjunction;
import net.bodz.lily.criterion.FieldBetween;
import net.bodz.lily.criterion.FieldCompare;
import net.bodz.lily.criterion.FieldIn;
import net.bodz.lily.criterion.ICriterion;
import net.bodz.lily.criterion.Junction;

public class FieldCriterionBuilder<fin_target, This, T>
        extends AbstractFieldCriterionBuilder<fin_target, This, T> {

    String fieldName;
    Class<T> type;
    IParser<T> parser;

    public FieldCriterionBuilder(String fieldName, Class<T> type, fin_target finishTarget,
            IReceiver<? super ICriterion> receiver) {
        super(finishTarget, receiver);
        this.fieldName = fieldName;
        this.type = type;
        this.parser = Typers.getTyper(type, IParser.class);
    }

    public Class<T> getValueType() {
        return type;
    }

    @SuppressWarnings("unchecked")
    protected NumberExprCriteriaBuilder<This> number(String expr) {
        return new NumberExprCriteriaBuilder<This>(expr, (This) this, receiver);
    }

    @Override
    public ICriterion makeIsNull() {
        return makeEq(null);
    }

    @Override
    public ICriterion makeIsNotNull() {
        return makeNotEq(null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public ICriterion makeIsTrue() {
        return makeEq((T) Boolean.TRUE);
    }

    @SuppressWarnings("unchecked")
    @Override
    public ICriterion makeIsFalse() {
        return makeEq((T) Boolean.FALSE);
    }

    @Override
    public ICriterion makeEq(T value) {
        return new FieldCompare<T>(fieldName, true, CompareMode.EQUALS, type, value);
    }

    @Override
    public ICriterion makeNotEq(T value) {
        return new FieldCompare<T>(fieldName, true, CompareMode.NOT_EQUALS, type, value);
    }

    @Override
    public ICriterion makeLessThan(T value) {
        return new FieldCompare<T>(fieldName, true, CompareMode.LESS_THAN, type, value);
    }

    @Override
    public ICriterion makeLessOrEquals(T value) {
        return new FieldCompare<T>(fieldName, true, CompareMode.LESS_OR_EQUALS, type, value);
    }

    @Override
    public ICriterion makeGreaterThan(T value) {
        return new FieldCompare<T>(fieldName, true, CompareMode.GREATER_THAN, type, value);
    }

    @Override
    public ICriterion makeGreaterOrEquals(T value) {
        return new FieldCompare<T>(fieldName, true, CompareMode.GREATER_OR_EQUALS, type, value);
    }

    @Override
    public ICriterion makeBetween(T min, T max) {
        return new FieldBetween<T>(fieldName, true, min, max);
    }

    @Override
    public ICriterion makeNotBetween(T min, T max) {
        return new FieldBetween<T>(fieldName, false, min, max);
    }

    @Override
    public ICriterion makeIn(Collection<T> values) {
        return new FieldIn<T>(fieldName, true, type, values);
    }

    @Override
    public ICriterion makeNotIn(Collection<T> values) {
        return new FieldIn<T>(fieldName, false, type, values);
    }

    @Override
    public ICriterion makeInside(T start, T end, boolean includeEnd) {
        if (start != null && end != null && includeEnd)
            return makeBetween(start, end);
        else {
            Junction and = new Junction();
            if (start != null)
                and.add(makeGreaterOrEquals(start));
            if (end != null)
                if (includeEnd)
                    and.add(makeLessOrEquals(end));
                else
                    and.add(makeLessThan(end));
            return and;
        }
    }

    @Override
    public ICriterion makeOptInside(T start, T end, boolean includeEnd) {
        Disjunction or = new Disjunction();
        or.add(makeIsNull());
        or.add(makeInside(start, end, includeEnd));
        return or;
    }

    @Override
    public ICriterion makeOutside(T start, T end, boolean includeEnd) {
        if (start != null && end != null && includeEnd)
            return makeNotBetween(start, end);
        else {
            Disjunction or = new Disjunction();
            if (start != null)
                or.add(makeLessThan(start));
            if (end != null)
                if (includeEnd)
                    or.add(makeGreaterThan(end));
                else
                    or.add(makeGreaterOrEquals(end));
            return or;
        }
    }

    @Override
    public ICriterion makeOptOutside(T start, T end, boolean includeEnd) {
        Disjunction or = new Disjunction();
        or.add(makeIsNull());
        or.add(makeOutside(start, end, includeEnd));
        return or;
    }

    public fin_target textBetween(String min, String max)
            throws ParseException {
        return send(makeTextBetween(min, max));
    }

    public ICriterion makeTextBetween(String min, String max)
            throws ParseException {
        if (min == null && max == null)
            // return True;
            throw new NullPointerException("both min and max are null");
        T minVal = min == null ? null : parser.parse(min);
        T maxVal = max == null ? null : parser.parse(max);

        ICriterion geMin = min == null ? null
                : new FieldCompare<T>(fieldName, true, //
                        CompareMode.GREATER_OR_EQUALS, type, minVal);

        ICriterion leMax = max == null ? null
                : new FieldCompare<T>(fieldName, true, //
                        CompareMode.LESS_OR_EQUALS, type, maxVal);

        if (min == null)
            return leMax;
        if (max == null)
            return geMin;
        return makeBetween(minVal, maxVal);
    }

}
