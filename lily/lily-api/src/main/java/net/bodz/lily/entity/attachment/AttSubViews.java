package net.bodz.lily.entity.attachment;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public class AttSubViews
        implements IJsonForm {

    private IAttachmentManifest manifest;

    public AttThumbnailSet thumbnails;
    public AttPreviewSet previews;
    public AttScreenpackSet screenpacks;

    public AttSubViews(IAttachmentManifest manifest) {
        if (manifest == null)
            throw new NullPointerException("manifest");
        this.manifest = manifest;
    }

    public boolean hasThumbnail() {
        return thumbnails != null && !thumbnails.isEmpty();
    }

    public boolean hasPreview() {
        return previews != null && !previews.isEmpty();
    }

    public boolean hasScreenshot() {
        return screenpacks != null && !screenpacks.isEmpty();
    }

    private static final String K_THUMBNAILS = "thumb";
    private static final String K_PREVIEWS = "preview";
    private static final String K_SCREENPACKS = "screen";

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        thumbnails = o.readInto(K_THUMBNAILS, null, AttThumbnailSet::new);
        previews = o.readInto(K_PREVIEWS, null, AttPreviewSet::new);
        screenpacks = o.readInto(K_SCREENPACKS, null, AttScreenpackSet::new);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entryNotNull(K_THUMBNAILS, thumbnails);
        out.entryNotNull(K_PREVIEWS, previews);
        out.entryNotNull(K_SCREENPACKS, screenpacks);
    }

}
