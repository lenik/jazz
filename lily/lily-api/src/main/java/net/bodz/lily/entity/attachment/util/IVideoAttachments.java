package net.bodz.lily.entity.attachment.util;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.meta.cache.Derived;
import net.bodz.lily.entity.attachment.IAttachment;

public interface IVideoAttachments {

    List<IAttachment> getVideos();

    void setVideos(List<IAttachment> attachments);

    @Derived
    default IAttachment getVideo() {
        List<IAttachment> videos = getVideos();
        if (videos == null || videos.isEmpty())
            return null;
        else
            return videos.get(0);
    }

    default void setVideo(IAttachment video) {
        List<IAttachment> videos = getVideos();
        if (videos == null) {
            videos = new ArrayList<>();
            setVideos(videos);
        }
        if (videos.isEmpty())
            videos.add(video);
        else
            videos.set(0, video);
    }

}
