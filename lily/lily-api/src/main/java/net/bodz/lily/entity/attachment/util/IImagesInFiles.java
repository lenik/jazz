package net.bodz.lily.entity.attachment.util;

import java.util.List;

import net.bodz.bas.meta.cache.Derived;
import net.bodz.lily.concrete.IAttachmentsInFiles;
import net.bodz.lily.entity.attachment.Attachment;
import net.bodz.lily.entity.attachment.IAttachment;
import net.bodz.lily.entity.esm.TsType;

public interface IImagesInFiles
        extends
            IAttachmentsInFiles,
            IImageAttachments {

    String K_IMAGES = "images";

    @Derived
    @TsType(value = List.class, componentType = Attachment.class)
    @Override
    default List<IAttachment> getImages() {
        return getAttachmentGroup(K_IMAGES);
    }

    @Override
    default void setImages(List<IAttachment> attachments) {
        setAttachmentGroup(K_IMAGES, attachments);
    }

}
