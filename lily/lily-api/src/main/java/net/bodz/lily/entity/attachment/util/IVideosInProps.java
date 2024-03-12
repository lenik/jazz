package net.bodz.lily.entity.attachment.util;

import java.util.List;

import net.bodz.bas.meta.cache.Derived;
import net.bodz.lily.concrete.IAttachmentsInProps;
import net.bodz.lily.entity.attachment.IAttachment;

public interface IVideosInProps
        extends
            IAttachmentsInProps,
            IVideoAttachments {

    String K_VIDEOS = "videos";

    @Override
    default List<IAttachment> getVideos() {
        return getAttachmentGroup(K_VIDEOS);
    }

    @Override
    default void setVideos(List<IAttachment> attachments) {
        setAttachmentGroup(K_VIDEOS, attachments);
    }

    @Derived
    default int getVideoCount() {
        List<IAttachment> videos = getVideos();
        if (videos == null)
            return 0;
        else
            return videos.size();
    }

}
