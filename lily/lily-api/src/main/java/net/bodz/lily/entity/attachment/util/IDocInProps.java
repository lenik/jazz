package net.bodz.lily.entity.attachment.util;

import java.util.List;

import net.bodz.lily.concrete.IAttachmentsInProps;
import net.bodz.lily.entity.attachment.IAttachment;

public interface IDocInProps
        extends
            IAttachmentsInProps,
            IDocAttachments {

    String K_DOCS = "docs";

    @Override
    default List<IAttachment> getDocuments() {
        return getAttachmentGroup(K_DOCS);
    }

    @Override
    default void setDocuments(List<IAttachment> attachments) {
        setAttachmentGroup(K_DOCS, attachments);
    }

}
