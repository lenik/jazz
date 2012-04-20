package net.bodz.bas.c.reflect.query.predicate;

public class StringEquals
        extends _StringPredicate {

    private final String pattern;

    public StringEquals(String pattern) {
        this(pattern, null);
    }

    public StringEquals(String pattern, IStringPredicate next) {
        super(next);
        if (pattern == null)
            throw new NullPointerException("name");
        this.pattern = pattern;
    }

    @Override
    protected boolean _evaluate(String s) {
        return s.equals(pattern);
    }

}
