package net.bodz.lily.template;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.site.json.JsonMap;
import net.bodz.lily.entity.attachment.DefaultAttachment;
import net.bodz.lily.entity.attachment.IAttachment;

/**
 * A common support for object icon, etc.
 */
public class RichProperties
        extends JsonMap

{

    private static final long serialVersionUID = 1L;

    static final String K_IMAGES = "images";
    static final String K_VIDEOS = "videos";
    static final String K_PDFS = "pdfs";

    public RichProperties() {
    }

    public final synchronized <T> List<T> getListAttribute(String name) {
        List<T> list = getAttribute(name);
        if (list == null)
            setAttribute(name, list = new ArrayList<>());
        return list;
    }

    /**
     * 图片
     *
     * 对应的图片，其中第一幅图片一般用于显示主图标。
     */
    @OfGroup(StdGroup.Visual.class)
    public final List<IAttachment> getImages() {
        return getListAttribute(K_IMAGES);
    }

    public final void setImages(List<IAttachment> images) {
        setAttribute(K_IMAGES, images);
    }

    /**
     * 视频
     *
     * 对应的视频，通常最多只有一个视频。
     */
    @OfGroup(StdGroup.Visual.class)
    public final List<IAttachment> getVideos() {
        return getListAttribute(K_VIDEOS);
    }

    public final void setVideos(List<IAttachment> videos) {
        setAttribute(K_VIDEOS, videos);
    }

    /**
     * PDF
     */
    @OfGroup(StdGroup.Visual.class)
    public final List<IAttachment> getPdfs() {
        return getListAttribute(K_PDFS);
    }

    public final void setPdfs(List<IAttachment> pdfs) {
        setAttribute(K_PDFS, pdfs);
    }

    @Override
    protected boolean parseJsonEntry(String key, JsonVariant j, JsonFormOptions opts)
            throws ParseException {
        switch (key) {
        case K_IMAGES:
        case K_VIDEOS:
        case K_PDFS:
            JsonArray array = j.getArray();
            List<IAttachment> attachments = DefaultAttachment.parseJsonArray(array);
            setAttribute(key, attachments);
            return true;
        }
        return super.parseJsonEntry(key, j, opts);
    }

}
