package net.bodz.lily.entity.attachment;

import java.io.IOException;
import java.nio.file.Path;

import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.t.file.IPathFields;
import net.bodz.bas.t.tuple.Split;
import net.bodz.lily.storage.IVolume;
import net.bodz.lily.storage.IVolumeItem;

public interface IBackedFile
        extends IPathFields {

    default IVolumeItem toVolumeFile(IVolume volume) {
        if (volume == null)
            throw new NullPointerException("volume");
        return volume.getFile(this);
    }

    default Path toPath(IVolume volume) {
        if (volume == null)
            throw new NullPointerException("volume");
        return toVolumeFile(volume).toPath();
    }

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
     * Update size, sha1, from the actual file data.
     */
    void update(IVolume volume)
            throws IOException;

    String K_FILE_SIZE = "size"; // "fileSize";
    String K_FILE_SHA1 = "sha1"; // "fileSHA1";

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        IPathFields.super.jsonOut(out, opts);
        out.entry(K_FILE_SIZE, getFileSize());
        out.entryNotNull(K_FILE_SHA1, getFileSHA1());
    }

}
