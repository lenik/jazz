package net.bodz.lily.criterion;

import java.util.HashMap;
import java.util.Map;

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

    static final Map<String, ICriterionCreator> map = new HashMap<>();
    static {
        map.put(K_JUNCTION, () -> new Junction());
        map.put(K_DISJUNCTION, () -> new Disjunction());
        map.put(K_NOT, () -> new Not());
        map.put(K_BETWEEN, () -> new FieldBetween<Object>());
        map.put(K_EQUALS, () -> newCompare(CompareMode.EQUALS));
        map.put(K_NOT_EQUALS, () -> newCompare(CompareMode.NOT_EQUALS));
        map.put(K_LESS_THAN, () -> newCompare(CompareMode.LESS_THAN));
        map.put(K_LESS_OR_EQUALS, () -> newCompare(CompareMode.LESS_OR_EQUALS));
        map.put(K_GREATER_THAN, () -> newCompare(CompareMode.GREATER_THAN));
        map.put(K_GREATER_OR_EQUALS, () -> newCompare(CompareMode.GREATER_OR_EQUALS));
        map.put(K_IN, () -> new FieldIn<Object>());
        map.put(K_LIKE, () -> new FieldLike());
    }

    static FieldCompare<Object> newCompare(CompareMode mode) {
        FieldCompare<Object> compare = new FieldCompare<>();
        compare.mode = mode;
        return compare;
    }

    public static ICriterion create(String typeKey) {
        ICriterionCreator creator = map.get(typeKey);
        if (creator == null)
            return null;
        else
            return creator.create();
    }

}
