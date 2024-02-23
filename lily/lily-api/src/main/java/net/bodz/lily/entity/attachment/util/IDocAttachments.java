package net.bodz.lily.entity.attachment.util;

import java.util.List;

import net.bodz.lily.entity.attachment.IAttachment;

public interface IDocAttachments {

    List<IAttachment> getDocuments();

    void setDocuments(List<IAttachment> attachments);

}
