package net.bodz.lily.template;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.site.file.ItemFile;
import net.bodz.bas.site.json.JsonMap;

public class RichProperties
        extends JsonMap {

    private static final long serialVersionUID = 1L;

    static final String K_IMAGES = "images";
    static final String K_VIDEOS = "videos";

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
    public final List<ItemFile> getImages() {
        return getListAttribute(K_IMAGES);
    }

    public final void setImages(List<ItemFile> images) {
        setAttribute(K_IMAGES, images);
    }

    /**
     * 视频
     *
     * 对应的视频，通常最多只有一个视频。
     */
    @OfGroup(StdGroup.Visual.class)
    public final List<ItemFile> getVideos() {
        return getListAttribute(K_VIDEOS);
    }

    public final void setVideos(List<ItemFile> videos) {
        setAttribute(K_VIDEOS, videos);
    }

    @Override
    protected boolean readFromJson(String key, Object val)
            throws ParseException {
        switch (key) {
        case K_IMAGES:
            setImages(ItemFile.convert((JSONArray) val));
            return true;
        case K_VIDEOS:
            setVideos(ItemFile.convert((JSONArray) val));
            return true;
        }
        return super.readFromJson(key, val);
    }

}
