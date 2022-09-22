package net.bodz.lily.entity.attachment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.servlet.ctx.WebAppAnchor;

public interface IAttachmentVolume {

    String getVolumeId();

    IAnchor getVolumeAnchor();

    IAttachment resolveAttachment(String path);

    boolean isValidWebPath(String webPath);

    String convertWebPathToVolumePath(String webPath);

    /**
     * Web path is the URI path part relative to the {@link WebAppAnchor webapp}.
     */
    IAnchor convertVolumePathToAnchor(String path);

    IStreamInputSource getInputSource(String path);

    /**
     * @return <code>null</code> if the attachment doesn't exist as a local file.
     */
    File getLocalFile(String path);

    boolean exists(String path);

    boolean isSymLink(String path);

    String getSymLinkTarget(String path)
            throws IOException;

    long getSize(String path)
            throws FileNotFoundException;

    String getSHA1(String path)
            throws IOException;

    boolean rename(String path, String newFileName)
            throws IOException;

    boolean moveTo(String path, String newPath)
            throws IOException;

    boolean moveTo(String path, String newDirName, String newFileName)
            throws IOException;

    boolean delete(String path)
            throws IOException;

}
