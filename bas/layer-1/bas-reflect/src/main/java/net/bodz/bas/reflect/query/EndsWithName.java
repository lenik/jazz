package net.bodz.bas.reflect.query;

public class EndsWithName
        implements INamePredicate {

    private final String suffix;
    private final INamePredicate next;

    public EndsWithName(String suffix) {
        this(suffix, null);
    }

    public EndsWithName(String suffix, INamePredicate next) {
        if (suffix == null)
            throw new NullPointerException("suffix");
        this.suffix = suffix;
        this.next = next;
    }

    @Override
    public INamePredicate next() {
        return next;
    }

    @Override
    public boolean test(String name) {
        if (!name.endsWith(suffix))
            return false;
        if (next != null && !next.test(name))
            return false;
        return true;
    }

}
