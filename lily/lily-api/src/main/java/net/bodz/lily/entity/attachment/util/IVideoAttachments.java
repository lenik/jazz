package net.bodz.lily.entity.attachment.util;

import java.util.List;

import net.bodz.lily.entity.attachment.IAttachment;

public interface IVideoAttachments {

    List<IAttachment> getVideos();

    void setVideos(List<IAttachment> attachments);

}
