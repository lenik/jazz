package net.bodz.bas.reflect.query.predicate;

public class StringEndsWith
        extends _StringPredicate {

    private final String pattern;

    public StringEndsWith(String pattern) {
        this(pattern, null);
    }

    public StringEndsWith(String pattern, IStringPredicate next) {
        super(next);
        if (pattern == null)
            throw new NullPointerException("suffix");
        this.pattern = pattern;
    }

    @Override
    protected boolean _evaluate(String s) {
        return s.endsWith(pattern);
    }

}
