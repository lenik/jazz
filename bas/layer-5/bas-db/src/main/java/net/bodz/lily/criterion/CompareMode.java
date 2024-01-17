package net.bodz.lily.criterion;

import net.bodz.bas.err.UnexpectedException;

public enum CompareMode {

    EQUALS,

    NOT_EQUALS,

    LESS_THAN,

    LESS_OR_EQUALS,

    GREATER_THAN,

    GREATER_OR_EQUALS,

    ;

    public CompareMode not() {
        switch (this) {
        case EQUALS:
            return NOT_EQUALS;
        case NOT_EQUALS:
            return EQUALS;
        case LESS_THAN:
            return GREATER_OR_EQUALS;
        case LESS_OR_EQUALS:
            return GREATER_THAN;
        case GREATER_THAN:
            return LESS_OR_EQUALS;
        case GREATER_OR_EQUALS:
            return LESS_THAN;
        default:
            throw new UnexpectedException();
        }
    }

    public String getSqlOp() {
        switch (this) {
        case EQUALS:
            return "=";
        case NOT_EQUALS:
            return "!=";
        case LESS_THAN:
            return "<";
        case LESS_OR_EQUALS:
            return "<=";
        case GREATER_THAN:
            return ">";
        case GREATER_OR_EQUALS:
            return ">=";
        default:
            throw new UnexpectedException();
        }
    }

    public String camelName() {
        switch (this) {
        case EQUALS:
            return "equals";
        case NOT_EQUALS:
            return "notEquals";
        case LESS_THAN:
            return "lessThan";
        case LESS_OR_EQUALS:
            return "lessOrEquals";
        case GREATER_THAN:
            return "greaterThan";
        case GREATER_OR_EQUALS:
            return "greaterOrEquals";
        default:
            throw new UnexpectedException();
        }
    }

}
