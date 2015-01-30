package net.bodz.bas.repr.form;

import java.text.Collator;
import java.util.Locale;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.t.order.AbstractNonNullComparator;

public class FieldDeclComparator
        extends AbstractNonNullComparator<IFieldDecl> {

    Collator collator = Collator.getInstance(Locale.CHINESE);

    @Override
    public int compareNonNull(IFieldDecl o1, IFieldDecl o2) {
        int cmp;

        int priority1 = o1.getPriority();
        int priority2 = o2.getPriority();
        cmp = priority1 - priority2;
        if (cmp != 0)
            return cmp;

        int level1 = o1.getDetailLevel();
        int level2 = o2.getDetailLevel();
        cmp = level1 - level2;
        if (cmp != 0)
            return cmp;

        // String name1 = o1.getName();
        // String name2 = o2.getName();
        // // cmp = name1.compareTo(name2);
        // cmp = Nullables.compare(name1, name2);
        // if (cmp != 0)
        // return cmp;

        String s1 = label(o1);
        String s2 = label(o2);
        cmp = Nullables.precompare(s1, s2);
        if (cmp == Nullables.UNKNOWN)
            cmp = collator.compare(s1, s2);
        if (cmp != 0)
            return cmp;

        int id1 = System.identityHashCode(o1);
        int id2 = System.identityHashCode(o2);
        cmp = id1 - id2;
        if (cmp != 0)
            return cmp;

        return o1.equals(o2) ? 0 : -1;
    }

    protected String label(IFieldDecl fieldDecl) {
        iString label = fieldDecl.getLabel();
        if (label != null) {
            String s = label.toString();
            if (s != null)
                return s;
        }
        String name = fieldDecl.getName();
        if (name != null)
            return name;
        return null;
    }

    public static FieldDeclComparator INSTANCE = new FieldDeclComparator();

}
