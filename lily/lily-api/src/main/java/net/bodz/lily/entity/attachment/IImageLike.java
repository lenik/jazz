package net.bodz.lily.entity.attachment;

import net.bodz.bas.c.object.Nullables;

public interface IImageLike {

    int getWidth();

    void setWidth(int width);

    int getHeight();

    void setHeight(int height);

    String getFormat();

    void setFormat(String format);

    int getQuality();

    void setQuality(int quality);

    // 640x480:png-75
    default String getTypeKey() {
        int width = getWidth();
        int height = getHeight();
        String format = getFormat();
        int quality = getQuality();
        StringBuilder sb = new StringBuilder();
        if (width == height)
            sb.append(width);
        else
            sb.append(width).append("x").append(height);
        if (format != null)
            sb.append(":").append(format);
        if (quality != 0)
            sb.append("-").append(quality);
        return sb.toString();
    }

    default boolean matchType(ImageType type) {
        if (type.width != 0 && type.width != getWidth())
            return false;
        if (type.height != 0 && type.height != getHeight())
            return false;
        if (type.quality != 0 && type.quality != getQuality())
            return false;
        if (type.format != null)
            if (Nullables.notEquals(getFormat(), type.format))
                return false;
        return true;
    }

}
