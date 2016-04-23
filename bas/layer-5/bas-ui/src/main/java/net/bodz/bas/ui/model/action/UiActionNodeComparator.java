package net.bodz.bas.ui.model.action;

import net.bodz.bas.i18n.dom1.ElementComparator;
import net.bodz.bas.t.order.AbstractNonNullComparator;
import net.bodz.bas.ui.dom1.IUiElement;

public class UiActionNodeComparator
        extends AbstractNonNullComparator<UiActionNode> {

    static ElementComparator impl = ElementComparator.LOCALE;

    @Override
    public int compareNonNull(UiActionNode o1, UiActionNode o2) {
        IUiElement elm1 = o1.getElement();
        IUiElement elm2 = o2.getElement();
        return impl.compare(elm1, elm2);
    }

    public static final UiActionNodeComparator INSTANCE = new UiActionNodeComparator();

}
