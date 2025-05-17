package net.bodz.lily.entity.attachment;

import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public class AttScreenpack
        extends ArrayList<AttScreenshot>
        implements IImageLike,
                   IJsonForm {

    @Serial
    private static final long serialVersionUID = 1L;

    int width;
    int height;
    String format;
    int quality;

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

    private static final String K_WIDTH = "width";
    private static final String K_HEIGHT = "height";
    private static final String K_FORMAT = "format";
    private static final String K_QUALITY = "quality";
    private static final String K_LIST = "list";

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts) {
        width = o.getInt(K_WIDTH, 0);
        height = o.getInt(K_HEIGHT, 0);
        format = o.getString(K_FORMAT);
        quality = o.getInt(K_QUALITY, 0);

        clear();
        JsonArray ja = o.getJsonArray(K_LIST);
        if (ja != null) {
            int n = ja.length();
            for (int i = 0; i < n; i++) {
                JsonObject child = ja.getJsonObject(i);
                AttScreenshot item = new AttScreenshot(this);
                item.jsonIn(child, opts);
                add(item);
            }
        }
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entryNot0(K_WIDTH, width);
        out.entryNot0(K_HEIGHT, height);
        out.entryNotNull(K_FORMAT, format);
        out.entryNot0(K_QUALITY, quality);

        if (!isEmpty()) {
            out.key(K_LIST);
            out.array();
            for (AttScreenshot item : this) {
                item.jsonOutValue(out, opts);
            }
            out.endArray();
        }
    }

}
