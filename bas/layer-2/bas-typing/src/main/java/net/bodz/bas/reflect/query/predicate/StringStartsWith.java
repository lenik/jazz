package net.bodz.bas.reflect.query.predicate;

public class StringStartsWith
        extends _StringPredicate {

    private final String pattern;

    public StringStartsWith(String pattern) {
        this(pattern, null);
    }

    public StringStartsWith(String pattern, IStringPredicate next) {
        super(next);
        if (pattern == null)
            throw new NullPointerException("prefix");
        this.pattern = pattern;
    }

    @Override
    protected boolean _evaluate(String s) {
        if (!s.startsWith(pattern))
            return false;
        return true;
    }
}
