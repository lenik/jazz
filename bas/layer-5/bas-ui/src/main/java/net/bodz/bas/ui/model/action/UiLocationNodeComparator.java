package net.bodz.bas.ui.model.action;

import net.bodz.bas.i18n.dom1.ElementComparator;
import net.bodz.bas.t.order.AbstractNonNullComparator;

public class UiLocationNodeComparator
        extends AbstractNonNullComparator<UiLocationNode> {

    static ElementComparator impl = ElementComparator.LOCALE;

    @Override
    public int compareNonNull(UiLocationNode o1, UiLocationNode o2) {
        UiLocationDecl decl1 = o1.getDecl();
        UiLocationDecl decl2 = o2.getDecl();
        return impl.compare(decl1, decl2);
    }

    public static final UiLocationNodeComparator INSTANCE = new UiLocationNodeComparator();

}
