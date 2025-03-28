package net.bodz.lily.entity.attachment;

import net.bodz.bas.meta.decl.NotNull;

public interface IMutableAttachment
        extends IAttachment {


    <T> void setView(@NotNull Class<T> viewType, @NotNull T view);

    <T> void addView(@NotNull T view);

    @NotNull
    default AttThumbnailSet thumbnails() {
        AttThumbnailSet thumbnails = getView(AttThumbnailSet.class);
        if (thumbnails == null)
            setView(AttThumbnailSet.class, thumbnails = new AttThumbnailSet());
        return thumbnails;
    }

    @NotNull
    default AttPreviewSet previews() {
        AttPreviewSet previews = getView(AttPreviewSet.class);
        if (previews == null)
            setView(AttPreviewSet.class, previews = new AttPreviewSet());
        return previews;
    }

    default AttScreenpackSet screenshots() {
        AttScreenpackSet screenpacks = getView(AttScreenpackSet.class);
        if (screenpacks == null)
            setView(AttScreenpackSet.class, screenpacks = new AttScreenpackSet());
        return screenpacks;
    }

}
