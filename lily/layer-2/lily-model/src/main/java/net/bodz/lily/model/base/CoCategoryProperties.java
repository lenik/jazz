package net.bodz.lily.model.base;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.site.file.ItemFile;
import net.bodz.bas.site.json.JsonMap;

public class CoCategoryProperties
        extends JsonMap {

    private static final long serialVersionUID = 1L;

    static final String K_IMAGES = "images";

    /**
     * 图片
     *
     * 对应的图片，其中第一幅图片一般用于显示主图标。
     */
    @OfGroup(StdGroup.Visual.class)
    public List<ItemFile> getImages() {
        return getAttribute(K_IMAGES);
    }

    public void setImages(List<ItemFile> images) {
        setAttribute(K_IMAGES, images);
    }

    @Override
    protected boolean readFromJson(String key, Object val)
            throws ParseException {
        switch (key) {
        case K_IMAGES:
            List<ItemFile> images = new ArrayList<>();
            for (JSONObject el : JsonFn.<JSONObject> iterate((JSONArray) val)) {
                ItemFile item = new ItemFile();
                item.readObject(el);
                images.add(item);
            }
            this.setImages(images);
            return true;
        }
        return super.readFromJson(key, val);
    }

}
