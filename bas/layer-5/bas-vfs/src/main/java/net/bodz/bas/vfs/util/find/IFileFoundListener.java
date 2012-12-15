package net.bodz.bas.vfs.util.find;

import java.util.EventListener;

public interface IFileFoundListener
        extends EventListener {

    void fileFound(FileFoundEvent event);

}
