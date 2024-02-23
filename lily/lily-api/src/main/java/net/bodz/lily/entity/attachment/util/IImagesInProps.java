package net.bodz.lily.entity.attachment.util;

import java.util.List;

import net.bodz.lily.concrete.IAttachmentsInProps;
import net.bodz.lily.entity.attachment.IAttachment;

public interface IImagesInProps
        extends
            IAttachmentsInProps,
            IImageAttachments {

    String K_IMAGES = "images";

    @Override
    default List<IAttachment> getImages() {
        return getAttachmentGroup(K_IMAGES);
    }

    @Override
    default void setImages(List<IAttachment> attachments) {
        setAttachmentGroup(K_IMAGES, attachments);
    }

}
