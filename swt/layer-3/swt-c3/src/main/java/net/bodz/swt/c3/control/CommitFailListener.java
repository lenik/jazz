package net.bodz.swt.c3.control;

import java.util.EventListener;
import java.util.EventObject;

public interface CommitFailListener
        extends EventListener {

    void commitFail(EventObject event, CommitException exception);

}
