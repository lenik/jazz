package net.bodz.bas.vfs.util;

import java.util.EventListener;

public interface IFileFoundListener
        extends EventListener {

    void fileFound(FileFoundEvent event);

}
