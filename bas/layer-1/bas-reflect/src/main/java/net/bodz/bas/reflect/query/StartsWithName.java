package net.bodz.bas.reflect.query;

public class StartsWithName
        implements INamePredicate {

    private final String prefix;
    private final INamePredicate next;

    public StartsWithName(String prefix) {
        this(prefix, null);
    }

    public StartsWithName(String prefix, INamePredicate next) {
        if (prefix == null)
            throw new NullPointerException("prefix");
        this.prefix = prefix;
        this.next = next;
    }

    @Override
    public INamePredicate next() {
        return next;
    }

    @Override
    public boolean test(String name) {
        if (!name.startsWith(prefix))
            return false;
        if (next != null && !next.test(name))
            return false;
        return true;
    }
}
