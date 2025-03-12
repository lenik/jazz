package net.bodz.lily.storage;

import java.io.IOException;
import java.nio.file.Path;

import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.servlet.ctx.WebAppAnchor;
import net.bodz.bas.t.file.IPathFields;

public interface IVolume {

    String getVolumeId();

    IAnchor getVolumeAnchor();

    IVolumeItem getFile(IPathFields pathFields);

    boolean isValidWebPath(String webPath);

    String convertWebPathToVolumePath(String webPath);

    /**
     * Web path is the URI path part relative to the {@link WebAppAnchor webapp}.
     */
    IAnchor convertVolumePathToAnchor(String path);

    IStreamInputSource getInputSource(String path);

    Path getLocalDir();

    /**
     * @return <code>null</code> if the attachment doesn't exist as a local file.
     */
    Path getLocalFile(String path);

    boolean exists(String path);

    boolean isSymLink(String path);

    String getSymLinkTarget(String path)
            throws IOException;

    long getSize(String path)
            throws IOException;

    String getSHA1(String path)
            throws IOException;

    void renameTo(String path, String newFileName)
            throws IOException;

    void moveTo(String path, String newPath)
            throws IOException;

    void moveTo(String path, String newDirName, String newFileName)
            throws IOException;

    void delete(String path)
            throws IOException;

}
