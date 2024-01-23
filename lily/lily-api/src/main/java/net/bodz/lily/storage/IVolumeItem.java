package net.bodz.lily.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    default File getLocalFile() {
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
            throws FileNotFoundException;

    String getSHA1()
            throws IOException;

    default boolean rename(String newFileName)
            throws IOException {
        return getVolume().rename(getPath(), newFileName);
    }

    default boolean moveTo(String newPath)
            throws IOException {
        return getVolume().moveTo(getPath(), newPath);
    }

    default boolean moveTo(String newDirName, String newFileName)
            throws IOException {
        return getVolume().moveTo(getPath(), newDirName, newFileName);
    }

    default boolean delete()
            throws IOException {
        return getVolume().delete(getPath());
    }

}
