package net.bodz.lily.storage;

import java.io.IOException;
import java.nio.file.Path;

import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.t.file.IPathFields;

public interface IVolumeItem
        extends
            IPathFields {

    IVolume getVolume();

    void setVolume(IVolume volume);

    default IAnchor getAnchor() {
        String path = getPath();
        return getVolume().getVolumeAnchor().join(path);
    }

    default Path getLocalFile() {
        return getVolume().getLocalFile(getPath());
    }

    /**
     * Whether the actual file data exists.
     */
    boolean exists();

    /**
     * fileName -> sha1
     */
    Boolean isSymLinkToSha1()
            throws IOException;

    /**
     * @return 0 if unknown.
     */
    long getSize()
            throws IOException;

    String getSHA1()
            throws IOException;

    default void renameTo(String newFileName)
            throws IOException {
        getVolume().renameTo(getPath(), newFileName);
    }

    default void moveTo(String newPath)
            throws IOException {
        getVolume().moveTo(getPath(), newPath);
    }

    default void moveTo(String newDirName, String newFileName)
            throws IOException {
        getVolume().moveTo(getPath(), newDirName, newFileName);
    }

    default void delete()
            throws IOException {
        getVolume().delete(getPath());
    }

}
