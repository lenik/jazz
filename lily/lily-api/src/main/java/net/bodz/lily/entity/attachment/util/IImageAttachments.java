package net.bodz.lily.entity.attachment.util;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.meta.cache.Derived;
import net.bodz.lily.entity.attachment.IAttachment;

public interface IImageAttachments {

    List<IAttachment> getImages();

    void setImages(List<IAttachment> attachments);

    @Derived
    default IAttachment getImage() {
        List<IAttachment> images = getImages();
        if (images == null || images.isEmpty())
            return null;
        else
            return images.get(0);
    }

    default void setImage(IAttachment image) {
        List<IAttachment> images = getImages();
        if (images == null) {
            images = new ArrayList<>();
            setImages(images);
        }
        if (images.isEmpty())
            images.add(image);
        else
            images.set(0, image);
    }

    @Derived
    default int getImageCount() {
        List<IAttachment> images = getImages();
        if (images == null)
            return 0;
        else
            return images.size();
    }

}
