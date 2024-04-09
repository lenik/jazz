package net.bodz.lily.criteria;

import net.bodz.lily.criterion.CompareMode;
import net.bodz.lily.criterion.Disjunction;
import net.bodz.lily.criterion.FieldBetween;
import net.bodz.lily.criterion.FieldCompare;
import net.bodz.lily.criterion.ICriterion;
import net.bodz.lily.criterion.Junction;

public class DualFieldCriterionBuilder<fin_target, This, T>
        extends FieldCriterionBuilder<fin_target, This, T>
        implements
            IDualFieldCriterionSender<fin_target, This, T> {

    String fieldName1;
    String fieldName2;
    Class<T> type;

    public DualFieldCriterionBuilder(String fieldName1, String fieldName2, Class<T> type, fin_target finishTarget,
            IReceiver<? super ICriterion> receiver) {
        super(fieldName1, type, finishTarget, receiver);
        this.fieldName1 = fieldName1;
        this.fieldName2 = fieldName2;
    }

    @Override
    public ICriterion makeEq(T value) {
        return makeEq(value, value);
    }

    @Override
    public ICriterion makeNotEq(T value) {
        return makeNotEq(value, value);
    }

    /**
     * field1 == v1 and field2 == v2
     */
    @Override
    public final ICriterion makeEq(T value1, T value2) {
        Junction and = new Junction();
        and.add(new FieldCompare<T>(fieldName1, true, CompareMode.EQUALS, type, value1));
        and.add(new FieldCompare<T>(fieldName2, true, CompareMode.EQUALS, type, value2));
        return and;
    }

    /**
     * field1 != v1 or field2 != v2
     */
    @Override
    public final ICriterion makeNotEq(T value1, T value2) {
        Disjunction or = new Disjunction();
        or.add(new FieldCompare<T>(fieldName1, true, CompareMode.NOT_EQUALS, type, value1));
        or.add(new FieldCompare<T>(fieldName2, true, CompareMode.NOT_EQUALS, type, value2));
        return or;
    }

    @Override
    public fin_target eq(T value1, T value2) {
        return send(makeEq(value1, value2));
    }

    @Override
    public fin_target notEq(T value1, T value2) {
        return send(makeNotEq(value1, value2));
    }

    /**
     * field1! < value and field2! < value (null field => false)
     */
    @Override
    public ICriterion makeLessThan(T value) {
        Junction and = new Junction();
        and.add(new FieldCompare<T>(fieldName1, true, CompareMode.LESS_THAN, type, value));
        and.add(new FieldCompare<T>(fieldName2, true, CompareMode.LESS_THAN, type, value));
        return and;
    }

    /**
     * field1! <= value and field2! <= value (null field => false)
     */
    @Override
    public ICriterion makeLessOrEquals(T value) {
        Junction and = new Junction();
        and.add(new FieldCompare<T>(fieldName1, true, CompareMode.LESS_OR_EQUALS, type, value));
        and.add(new FieldCompare<T>(fieldName2, true, CompareMode.LESS_OR_EQUALS, type, value));
        return and;
    }

    /**
     * field1! > value and field2! > value (null field => false)
     */
    @Override
    public ICriterion makeGreaterThan(T value) {
        Junction and = new Junction();
        and.add(new FieldCompare<T>(fieldName1, true, CompareMode.GREATER_THAN, type, value));
        and.add(new FieldCompare<T>(fieldName2, true, CompareMode.GREATER_THAN, type, value));
        return and;
    }

    /**
     * field1! >= value and field2! >= value (null field => false)
     */
    @Override
    public ICriterion makeGreaterOrEquals(T value) {
        Junction and = new Junction();
        and.add(new FieldCompare<T>(fieldName1, true, CompareMode.GREATER_OR_EQUALS, type, value));
        and.add(new FieldCompare<T>(fieldName2, true, CompareMode.GREATER_OR_EQUALS, type, value));
        return and;
    }

    /**
     * (field1! between min and max) and (field2! between min and max)
     *
     * (null field => false)
     */
    @Override
    public ICriterion makeBetween(T min, T max) {
        Junction and = new Junction();
        and.add(new FieldBetween<T>(fieldName1, true, min, max));
        and.add(new FieldBetween<T>(fieldName2, true, min, max));
        return and;
    }

    /**
     * (field1! not-between min and max) or (field2! not-between min and max)
     *
     * (null field => false)
     */
    @Override
    public ICriterion makeNotBetween(T min, T max) {
        Disjunction or = new Disjunction();
        or.add(new FieldBetween<T>(fieldName1, false, min, max));
        or.add(new FieldBetween<T>(fieldName2, false, min, max));
        return or;
    }

    /**
     * field1 inside (start...end) and field2 inside (start..end)
     *
     * (null field => false)
     */
    @Override
    public ICriterion makeInside(T start, T end, boolean includeEnd) {
        return super.makeInside(start, end, includeEnd);
    }

    /**
     * field1 outside (start...end) or field2 outside (start..end)
     *
     * (null field => false)
     */
    @Override
    public ICriterion makeOutside(T start, T end, boolean includeEnd) {
        return super.makeOutside(start, end, includeEnd);
    }

    @Override
    public ICriterion makeOptInside(T start, T end, boolean includeEnd) {
        return super.makeOptInside(start, end, includeEnd);
    }

    @Override
    public ICriterion makeIntersects(T start, T end, boolean includeEnd) {
        // || field1 != null && field1 with-in
        // || field2 != null && field2 with-in
        Disjunction or = new Disjunction();
        CompareMode ltEnd = includeEnd ? CompareMode.LESS_OR_EQUALS : CompareMode.LESS_THAN;

        if (start != null || end != null) { // || field1 != null && field1 with-in
            Junction and = new Junction();
            and.add(new FieldCompare<>(fieldName1, true, CompareMode.NOT_EQUALS, type, null));
            if (start != null)
                and.add(new FieldCompare<>(fieldName1, true, CompareMode.GREATER_OR_EQUALS, type, start));
            if (end != null)
                and.add(new FieldCompare<>(fieldName1, true, ltEnd, type, end));
            or.add(and);
        }
        if (start != null || end != null) { // || field2 != null && field2 with-in
            Junction and = new Junction();
            and.add(new FieldCompare<>(fieldName2, true, CompareMode.NOT_EQUALS, type, null));
            if (start != null)
                and.add(new FieldCompare<>(fieldName2, true, CompareMode.GREATER_OR_EQUALS, type, start));
            if (end != null)
                and.add(new FieldCompare<>(fieldName2, true, ltEnd, type, end));
            or.add(and);
        }
        return or.reduce(); // null -> true (means intersects)
    }

    @Override
    public ICriterion makeNotIntersects(T start, T end, boolean includeEnd) {
        // || field1 != null && field1 > end
        // || field2 != null && field2 < start
        Disjunction or = new Disjunction();
        CompareMode gtEnd = includeEnd ? CompareMode.GREATER_THAN : CompareMode.GREATER_OR_EQUALS;

        if (end != null) { // || field1 != null && field1 > end
            Junction and = new Junction();
            and.add(new FieldCompare<>(fieldName1, true, CompareMode.NOT_EQUALS, type, null));
            and.add(new FieldCompare<>(fieldName1, true, gtEnd, type, end));
            or.add(and);
        }
        if (start != null) { // || field2 != null && field2 < start
            Junction and = new Junction();
            and.add(new FieldCompare<>(fieldName2, true, CompareMode.NOT_EQUALS, type, null));
            and.add(new FieldCompare<>(fieldName2, true, CompareMode.LESS_THAN, type, start));
            or.add(and);
        }
        return or.reduce(); // null -> false (means intersects)
    }

}
