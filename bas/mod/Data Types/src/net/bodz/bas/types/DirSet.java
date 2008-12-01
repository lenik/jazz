package net.bodz.bas.types;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;

public class DirSet extends HierSet<String> {

    private static final long serialVersionUID = -8164634967774450061L;

    public DirSet() {
        super();
    }

    public DirSet(Collection<? extends String> c) {
        super(c);
    }

    public DirSet(Comparator<? super String> comparator) {
        super(comparator);
    }

    public DirSet(SortedSet<String> s) {
        super(s);
    }

    @Override
    public boolean derives(String sup, String sub) {
        return DirMap._derives(sup, sub);
    }

    @Override
    public String shrink(String e) {
        return DirMap._shrink(e);
    }

}
