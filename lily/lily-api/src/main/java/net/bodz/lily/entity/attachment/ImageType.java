package net.bodz.lily.entity.attachment;

import net.bodz.bas.err.ParseException;

public class ImageType
        implements IImageLike {

    public int width;
    public int height;
    public String format;
    public int quality;

    public ImageType() {
    }

    public ImageType(IImageLike like) {
        width = like.getWidth();
        height = like.getHeight();
        format = like.getFormat();
        quality = like.getQuality();
    }

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

    static int parseInt(String s, int defaultVal)
            throws ParseException {
        if (s == null || s.isEmpty())
            return defaultVal;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new ParseException(e);
        }
    }

    public void parse(String key)
            throws ParseException {
        // 640x480:png-75
        int colon = key.indexOf(':');
        if (colon != -1) {
            String formatQ = key.substring(colon + 1);
            int dash = formatQ.lastIndexOf('-');
            if (dash != -1) {
                String qStr = formatQ.substring(dash + 1);
                quality = parseInt(qStr, 0);
                formatQ = formatQ.substring(0, dash);
            }
            format = formatQ;
            key = key.substring(0, colon);
        }

        int x = key.indexOf('x');
        if (x != -1) {
            String hStr = key.substring(x + 1);
            height = parseInt(hStr, 0);
            key = key.substring(0, x);
        }
        width = parseInt(key, 0);
        if (x == -1)
            height = width;
    }

    public static ImageType ofKey(String key)
            throws ParseException {
        ImageType type = new ImageType();
        type.parse(key);
        return type;
    }

    @Override
    public String toString() {
        return getTypeKey();
    }

    public int compareGeom(IImageLike o1, IImageLike o2) {
        if (this.width == 0 && this.height == 0)
            return 0;

        int dw1 = 0, dw2 = 0, dh1 = 0, dh2 = 0;
        if (this.width != 0) {
            dw1 = Math.abs(o1.getWidth() - this.width);
            dw2 = Math.abs(o2.getWidth() - this.width);
        }
        if (this.height != 0) {
            dh1 = Math.abs(o1.getHeight() - this.height);
            dh2 = Math.abs(o2.getHeight() - this.height);
        }

        int maj1 = 0;
        int maj2 = 0;
        if (dw1 == 0 && dw2 != 0)
            maj1++;
        if (dw1 != 0 && dw2 == 0)
            maj2++;
        if (dh1 == 0 && dh2 != 0)
            maj1++;
        if (dh1 != 0 && dh2 == 0)
            maj2++;
        if (maj1 != maj2)
            return -(maj1 - maj2);

        int d1 = dw1 + dh1;
        int d2 = dw2 + dh2;
        return d1 - d2;
    }

}