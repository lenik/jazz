package net.bodz.dist.ins;

import java.util.EventListener;

public interface CancelListener extends EventListener {

    void cancel(CancelEvent e);

}
