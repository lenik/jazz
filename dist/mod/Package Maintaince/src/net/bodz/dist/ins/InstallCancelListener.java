package net.bodz.dist.ins;

import java.util.EventListener;

public interface InstallCancelListener extends EventListener {

    void installCancel(InstallCancelEvent e);

}
