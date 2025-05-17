package net.bodz.lily.entity.attachment;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public class AttPreview
        extends DefaultBackedFile
        implements IVideoLike {

    int width;
    int height;
    String format;
    int quality;

    int videoBitRate;
    int audioBitRate;
    String videoFormat;
    String audioFormat;

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String getFormat() {
        return format;
    }

    @Override
    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public int getQuality() {
        return quality;
    }

    @Override
    public void setQuality(int quality) {
        this.quality = quality;
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

    private static final String K_WIDTH = "width";
    private static final String K_HEIGHT = "height";
    private static final String K_FORMAT = "format";
    private static final String K_QUALITY = "quality";
    private static final String K_VIDEO_FORMAT = "videoFormat";
    private static final String K_AUDIO_FORMAT = "audioFormat";
    private static final String K_VIDEO_BIT_RATE = "videoBitRate";
    private static final String K_AUDIO_BIT_RATE = "audioBitRate";

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts) {
        super.jsonIn(o, opts);
        width = o.getInt(K_WIDTH, 0);
        height = o.getInt(K_HEIGHT, 0);
        format = o.getString(K_FORMAT);
        quality = o.getInt(K_QUALITY, 0);
        videoFormat = o.getString(K_VIDEO_FORMAT);
        audioFormat = o.getString(K_AUDIO_FORMAT);
        videoBitRate = o.getInt(K_VIDEO_BIT_RATE, 0);
        audioBitRate = o.getInt(K_AUDIO_BIT_RATE, 0);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        super.jsonOut(out, opts);
        out.entryNot0(K_WIDTH, width);
        out.entryNot0(K_HEIGHT, height);
        out.entryNotNull(K_FORMAT, format);
        out.entryNot0(K_QUALITY, quality);
        out.entryNotNull(K_VIDEO_FORMAT, videoFormat);
        out.entryNotNull(K_AUDIO_FORMAT, audioFormat);
        out.entryNot0(K_VIDEO_BIT_RATE, videoBitRate);
        out.entryNot0(K_AUDIO_BIT_RATE, audioBitRate);
    }

    @Override
    public void readObject(IElement o)
            throws ParseException, LoaderException {
        width = o.a(K_WIDTH).getInt(0);
        height = o.a(K_HEIGHT).getInt(0);
        format = o.a(K_FORMAT).getString();
        quality = o.a(K_QUALITY).getInt(0);
        videoFormat = o.a(K_VIDEO_FORMAT).getString();
        audioFormat = o.a(K_AUDIO_FORMAT).getString();
        videoBitRate = o.a(K_VIDEO_BIT_RATE).getInt(0);
        audioBitRate = o.a(K_AUDIO_BIT_RATE).getInt(0);
    }

    @Override
    public void writeObject(IXmlOutput out)
            throws XMLStreamException {
        out.attributeNot0(K_WIDTH, width);
        out.attributeNot0(K_HEIGHT, height);
        out.attributeNotNull(K_FORMAT, format);
        out.attributeNot0(K_QUALITY, quality);
        out.attributeNotNull(K_VIDEO_FORMAT, videoFormat);
        out.attributeNotNull(K_AUDIO_FORMAT, audioFormat);
        out.attributeNot0(K_VIDEO_BIT_RATE, videoBitRate);
        out.attributeNot0(K_AUDIO_BIT_RATE, audioBitRate);
    }

}
