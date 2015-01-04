package net.bodz.bas.repr.form;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.t.order.AbstractNonNullComparator;

public class FieldDeclLabelComparator
        extends AbstractNonNullComparator<IFieldDecl> {

    @Override
    public int compareNonNull(IFieldDecl o1, IFieldDecl o2) {
        String s1 = label(o1);
        String s2 = label(o2);
        int cmp = Nullables.compare(s1, s2);
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

    public static final FieldDeclLabelComparator INSTANCE = new FieldDeclLabelComparator();

}
