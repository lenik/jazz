package net.bodz.swt.c.functional;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

@FunctionalInterface
public interface SelectionFunc
        extends
            SelectionListener {

    @Override
    default void widgetDefaultSelected(SelectionEvent e) {
    }

}
