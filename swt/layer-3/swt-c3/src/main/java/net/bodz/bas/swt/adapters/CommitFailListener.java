package net.bodz.swt.adapters;

import java.util.EventListener;
import java.util.EventObject;

public interface CommitFailListener extends EventListener {

    void commitFail(EventObject event, CommitException exception);

}
