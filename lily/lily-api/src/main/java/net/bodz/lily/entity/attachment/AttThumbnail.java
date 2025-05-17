package net.bodz.lily.entity.attachment;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public class AttThumbnail
        extends DefaultBackedFile
        implements IImageLike {

    int width;
    int height;
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
        return super.getExtension();
    }

    @Override
    public void setFormat(String s) {
        super.setExtension(s);
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
    private static final String K_QUALITY = "quality";

    @Override
    public void jsonIn(@NotNull JsonObject o)
            throws ParseException {
        super.jsonIn(o);
        width = o.getInt(K_WIDTH, 0);
        height = o.getInt(K_HEIGHT, 0);
        quality = o.getInt(K_QUALITY, 0);
    }

    @Override
    public void jsonOut(IJsonOut out)
            throws IOException, FormatException {
        super.jsonOut(out);
        out.entryNot0(K_WIDTH, width);
        out.entryNot0(K_HEIGHT, height);
        out.entryNot0(K_QUALITY, quality);
    }

}
