package net.bodz.lily.entity.attachment;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.t.file.IPathFields;
import net.bodz.bas.t.tuple.Split;
import net.bodz.lily.storage.IVolume;
import net.bodz.lily.storage.IVolumeItem;

public interface IAttachment
        extends
            IPathFields {

    default IVolumeItem toVolumeFile(IVolume volume) {
        if (volume == null)
            throw new NullPointerException("volume");
        return volume.getFile(this);
    }

    default File toLocalFile(IVolume volume) {
        if (volume == null)
            throw new NullPointerException("volume");
        return toVolumeFile(volume).getLocalFile();
    }

    String getLabel();

    void setLabel(String label);

    String getDescription();

    void setDescription(String description);

    /**
     * @return <code>null</code> if unknown.
     */
    long getFileSize();

    /**
     * @return <code>null</code> if unknown.
     */
    String getFileSHA1();

    default String getSHA1FileName() {
        String sha1 = getFileSHA1();
        String name = getFileName();
        if (name == null)
            name = getName();
        Split split = Split.nameExtension(name);
        String extension = split.b;
        if (extension != null)
            return sha1 + "." + extension;
        else
            return sha1;
    }

    /**
     * @return <code>null</code> if not available, because file doesn't exist, or in invalid image
     *         format.
     */
    IAttachment getPreviewImage(int desiredWidth, int desiredHeight);

    /**
     * Update size, sha1, preview-image, etc. from the actual file data.
     */
    void update(IVolume volume)
            throws IOException;

    String K_LABEL = "label";
    String K_DESCRIPTION = "description";
    String K_FILE_SIZE = "size"; // "fileSize";
    String K_FILE_SHA1 = "sha1"; // "fileSHA1";

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        IPathFields.super.jsonOut(out, opts);

        out.entryNotNull(K_LABEL, getLabel());
        out.entryNotNull(K_DESCRIPTION, getDescription());

        out.entry(K_FILE_SIZE, getFileSize());
        out.entryNotNull(K_FILE_SHA1, getFileSHA1());
    }

}
