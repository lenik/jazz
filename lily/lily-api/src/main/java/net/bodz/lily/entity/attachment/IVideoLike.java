package net.bodz.lily.entity.attachment;

import net.bodz.bas.c.object.Nullables;

public interface IVideoLike
        extends IImageLike {

    String getVideoFormat();

    void setVideoFormat(String videoFormat);

    String getAudioFormat();

    void setAudioFormat(String audioFormat);

    int getVideoBitRate();

    void setVideoBitRate(int videoBitRate);

    int getAudioBitRate();

    void setAudioBitRate(int audioBitRate);

    // 640x480:mp4-0/h264-300/ogg-192
    default String getTypeKey() {
        String videoFormat = getVideoFormat();
        int videoBitRate = getVideoBitRate();
        String audioFormat = getAudioFormat();
        int audioBitRate = getAudioBitRate();
        StringBuilder sb = new StringBuilder(IImageLike.super.getTypeKey());
        if (videoFormat != null) {
            sb.append("/").append(videoFormat);
            if (videoBitRate != 0)
                sb.append("-").append(videoBitRate);
        }
        if (audioFormat != null) {
            if (videoFormat == null)
                sb.append("/");
            sb.append("/").append(audioFormat);
            if (audioBitRate != 0)
                sb.append("-").append(audioBitRate);
        }
        return sb.toString();
    }

    default boolean matchType(VideoType type) {
        if (!IImageLike.super.matchType(type))
            return false;
        if (type.videoBitRate != 0 && type.videoBitRate != getVideoBitRate())
            return false;
        if (type.audioBitRate != 0 && type.audioBitRate != getAudioBitRate())
            return false;
        if (type.videoFormat != null)
            if (Nullables.notEquals(getVideoFormat(), type.videoFormat))
                return false;
        if (type.audioFormat != null)
            if (Nullables.notEquals(getAudioFormat(), type.audioFormat))
                return false;
        return true;
    }

}
