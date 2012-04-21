package net.bodz.swt.adapters;

import java.util.EventListener;
import java.util.EventObject;

public interface CommitListener
        extends EventListener {

    void commit(EventObject event)
            throws CommitException;

}
