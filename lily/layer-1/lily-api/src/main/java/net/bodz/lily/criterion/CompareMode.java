package net.bodz.lily.criterion;

public enum CompareMode {

    EQUALS,

    LESS_THAN,

    LESS_OR_EQUALS,

    GREATER_THAN,

    GREATER_OR_EQUALS,

    ;

    public String getOp() {
        switch (this) {
        case EQUALS:
            return "=";
        case LESS_THAN:
            return "<";
        case LESS_OR_EQUALS:
            return "<=";
        case GREATER_THAN:
            return ">";
        case GREATER_OR_EQUALS:
            return ">=";
        default:
            return "?";
        }
    }

    public String getOpName() {
        switch (this) {
        case EQUALS:
            return "equals";
        case LESS_THAN:
            return "lessThan";
        case LESS_OR_EQUALS:
            return "lessOrEquals";
        case GREATER_THAN:
            return "greaterThan";
        case GREATER_OR_EQUALS:
            return "greaterOrEquals";
        default:
            return "?";
        }
    }

}
