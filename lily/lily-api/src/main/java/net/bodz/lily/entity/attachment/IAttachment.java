package net.bodz.lily.entity.attachment;

import java.io.IOException;

import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.lily.storage.IVolume;

public interface IAttachment
        extends IBackedFile {

    String getLabel();

    void setLabel(String label);

    String getDescription();

    void setDescription(String description);

    default String getKey() {
        return getFileSHA1();
    }

    <T> T getView(Class<T> viewType);

    default AttThumbnailSet getThumbnails() {
        return getView(AttThumbnailSet.class);
    }

    default AttPreviewSet getPreviews() {
        return getView(AttPreviewSet.class);
    }

    default AttScreenpackSet getScreenshots() {
        return getView(AttScreenpackSet.class);
    }

    /**
     * Update size, sha1, preview-image, etc. from the actual file data.
     */
    void update(IVolume volume)
            throws IOException;

    String K_LABEL = "label";
    String K_DESCRIPTION = "description";

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        IBackedFile.super.jsonOut(out, opts);
        out.entryNotNull(K_LABEL, getLabel());
        out.entryNotNull(K_DESCRIPTION, getDescription());
    }

}
