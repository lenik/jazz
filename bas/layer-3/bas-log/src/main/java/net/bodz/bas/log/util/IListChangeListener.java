package net.bodz.bas.log.util;

import java.util.EventListener;

public interface IListChangeListener
        extends EventListener {

    void onInserted(int start, int len);

    void onRemoved(int start, int len);

    /**
     * inserted or modified
     */
    void onChanged(int start, int len);

    void onCleared();

}
