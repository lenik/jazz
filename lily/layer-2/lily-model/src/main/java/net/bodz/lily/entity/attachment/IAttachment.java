package net.bodz.lily.entity.attachment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.servlet.ctx.IAnchor;

public interface IAttachment
        extends
            IJsonForm {

    IAttachmentVolume getVolume();

    void setVolume(IAttachmentVolume container);

    IAnchor getAnchor();

    String getPath();

    void setPath(String path);

    String getDirName();

    void setDirName(String dirName);

    String getFileName();

    void setFileName(String fileName);

    String getName();

    void setName(String name);

    String getExtension();

    void setExtension(String extension);

    String getLabel();

    void setLabel(String label);

    String getDescription();

    void setDescription(String description);

    /**
     * @return <code>null</code> if the attachment doesn't exist as a local file.
     */
    File getFile();

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

    /**
     * @return <code>null</code> if not available, because file doesn't exist, or in invalid image
     *         format.
     */
    IAttachment getPreviewImage(int desiredWidth, int desiredHeight);

    /**
     * Update size, sha1, preview-image, etc. from the actual file data.
     */
    void update()
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

    String K_VOLUME_ID = "volumeId";
    String K_PATH = "path";
    String K_DIR_NAME = "dirName";
    String K_FILE_NAME = "fileName";
    String K_LABEL = "label";
    String K_DESCRIPTION = "description";
    String K_SYMLINK_TO_SHA1 = "symLinkToSha1";
    String K_SIZE = "size";
    String K_SHA1 = "SHA1";

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        IAttachmentVolume volume = getVolume();
        if (volume != null)
            out.entry(K_VOLUME_ID, volume.getVolumeId());
        // out.entry(K_PATH, getPath());
        out.entryNotNull(K_DIR_NAME, getDirName());
        out.entryNotNull(K_FILE_NAME, getFileName());
        out.entryNotNull(K_LABEL, getLabel());
        out.entryNotNull(K_DESCRIPTION, getDescription());

        out.entryNotNull(K_SYMLINK_TO_SHA1, isSymLinkToSha1());
        out.entry(K_SIZE, getSize());
        out.entryNotNull(K_SHA1, getSHA1());
    }

}
