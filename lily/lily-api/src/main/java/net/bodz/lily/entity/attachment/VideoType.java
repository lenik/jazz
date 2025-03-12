package net.bodz.lily.entity.attachment;

import net.bodz.bas.err.ParseException;

public class VideoType
        extends ImageType
        implements IVideoLike {

    public String videoFormat;
    public String audioFormat;
    public int videoBitRate;
    public int audioBitRate;

    public VideoType() {
    }

    public VideoType(IVideoLike like) {
        super(like);
        videoFormat = like.getVideoFormat();
        audioFormat = like.getAudioFormat();
        videoBitRate = like.getVideoBitRate();
        audioBitRate = like.getAudioBitRate();
    }

    @Override
    public String getVideoFormat() {
        return videoFormat;
    }

    @Override
    public void setVideoFormat(String videoFormat) {
        this.videoFormat = videoFormat;
    }

    @Override
    public String getAudioFormat() {
        return audioFormat;
    }

    @Override
    public void setAudioFormat(String audioFormat) {
        this.audioFormat = audioFormat;
    }

    @Override
    public int getVideoBitRate() {
        return videoBitRate;
    }

    @Override
    public void setVideoBitRate(int videoBitRate) {
        this.videoBitRate = videoBitRate;
    }

    @Override
    public int getAudioBitRate() {
        return audioBitRate;
    }

    @Override
    public void setAudioBitRate(int audioBitRate) {
        this.audioBitRate = audioBitRate;
    }

    public void parse(String key)
            throws ParseException {
        // 640x480:mp4-0/h264-300/ogg-192
        int slash = key.indexOf('/');
        if (slash != -1) {
            String va = key.substring(slash + 1);
            int slash2 = va.indexOf('/');
            if (slash2 != -1) {
                String audio = va.substring(slash2 + 1);
                _parseAudioCode(audio);
                va = va.substring(0, slash2);
            }
            _parseVideoCode(va);
            key = key.substring(0, slash);
        }
        super.parse(key);
    }

    void _parseVideoCode(String video)
            throws ParseException {
        int dash = video.lastIndexOf('-');
        if (dash != -1) {
            String bitRateStr = video.substring(dash + 1);
            videoBitRate = parseInt(bitRateStr, 0);
            video = video.substring(0, dash);
        }
        videoFormat = video;
    }

    void _parseAudioCode(String audio)
            throws ParseException {
        int dash = audio.lastIndexOf('-');
        if (dash != -1) {
            String bitRateStr = audio.substring(dash + 1);
            audioBitRate = parseInt(bitRateStr, 0);
            audio = audio.substring(0, dash);
        }
        audioFormat = audio;
    }

    public static VideoType ofKey(String key)
            throws ParseException {
        VideoType type = new VideoType();
        type.parse(key);
        return type;
    }

}
