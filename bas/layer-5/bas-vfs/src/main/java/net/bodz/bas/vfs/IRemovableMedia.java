package net.bodz.bas.vfs;

import java.io.IOException;

public interface IRemovableMedia {

    boolean isMediaRemoved();

    void removeMedia()
            throws IOException;

}
