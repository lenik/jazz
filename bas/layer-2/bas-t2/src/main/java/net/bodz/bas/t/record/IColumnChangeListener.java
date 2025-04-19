package net.bodz.bas.t.record;

import java.util.EventListener;

/**
 * @see net.bodz.bas.bean.api.IPropertyChangeListener
 */
public interface IColumnChangeListener
        extends EventListener {

    /**
     * This method gets called when a bound column is changed.
     *
     * @param event A IColumnChangeEvent object describing the event source and the column that has changed.
     */

    void columnChange(IColumnChangeEvent event);

    default boolean isProxy() {
        return false;
    }

    default IColumnChangeListener getListener() {
        return null;
    }

    default IColumnChangeListener deproxy() {
        if (!isProxy())
            return this;
        else
            return getListener().deproxy();
    }

}
