package net.bodz.lily.criterion;

public class Criterions {

    static final String K_JUNCTION = "and";
    static final String K_DISJUNCTION = "or";
    static final String K_NOT = "not";
    static final String K_BETWEEN = "between";
    static final String K_EQUALS = "equals";
    static final String K_NOT_EQUALS = "notEquals";
    static final String K_LESS_THAN = "lessThan";
    static final String K_LESS_OR_EQUALS = "lessOrEquals";
    static final String K_GREATER_THAN = "greaterThan";
    static final String K_GREATER_OR_EQUALS = "greaterOrEquals";
    static final String K_IN = "in";
    static final String K_LIKE = "like";

    static <T> FieldCompare<T> newCompare(CompareMode mode, Class<T> type) {
        FieldCompare<T> compare = new FieldCompare<>(type);
        compare.mode = mode;
        return compare;
    }

    public static ICriterion create(String typeKey, Class<?> valueType) {
        switch (typeKey) {
        case K_JUNCTION:
            return new Junction();
        case K_DISJUNCTION:
            return new Disjunction();
        case K_NOT:
            return new Not();
        case K_BETWEEN:
            return new FieldBetween<Object>();
        case K_EQUALS:
            return newCompare(CompareMode.EQUALS, valueType);
        case K_NOT_EQUALS:
            return newCompare(CompareMode.NOT_EQUALS, valueType);
        case K_LESS_THAN:
            return newCompare(CompareMode.LESS_THAN, valueType);
        case K_LESS_OR_EQUALS:
            return newCompare(CompareMode.LESS_OR_EQUALS, valueType);
        case K_GREATER_THAN:
            return newCompare(CompareMode.GREATER_THAN, valueType);
        case K_GREATER_OR_EQUALS:
            return newCompare(CompareMode.GREATER_OR_EQUALS, valueType);
        case K_IN:
            @SuppressWarnings({ "rawtypes", "unchecked" })
            FieldIn<Object> in = new FieldIn(valueType);
            return in;
        case K_LIKE:
            return new FieldLike();
        default:
            return null;
        }
    }

}
