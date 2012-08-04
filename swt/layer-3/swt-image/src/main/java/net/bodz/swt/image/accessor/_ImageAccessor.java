package net.bodz.swt.image.accessor;

import org.eclipse.swt.graphics.RGB;

public abstract class _ImageAccessor
        implements ImageAccessor {

    @Override
    public abstract ImageAccessor clone();

    @Override
    public void incX() {
        int x = getX();
        setX(x + 1);
    }

    @Override
    public void decX() {
        int x = getX();
        setX(x - 1);
    }

    @Override
    public void incY() {
        int y = getY();
        setY(y + 1);
    }

    @Override
    public void decY() {
        int y = getY();
        setY(y - 1);
    }

    @Override
    public void getRGB(RGB rgb) {
        RGB ret = getRGB();
        rgb.red = ret.red;
        rgb.green = ret.green;
        rgb.blue = ret.blue;
    }

    @Override
    public RGB copyRGB() {
        RGB rgb = getRGB();
        return new RGB(rgb.red, rgb.green, rgb.blue);
    }

    @Override
    public void getRGB(int[] rgbv) {
        assert rgbv != null && rgbv.length >= 3 : "output rgb[]";
        RGB rgb = getRGB();
        rgbv[0] = rgb.red;
        rgbv[1] = rgb.green;
        rgbv[2] = rgb.blue;
    }

    @Override
    public void setRGB(int[] rgbv) {
        assert rgbv != null && rgbv.length >= 3 : "rgb";
        RGB rgb = new RGB(rgbv[0], rgbv[1], rgbv[2]);
        setRGB(rgb);
    }

    @Override
    public int getAlpha() {
        return 0;
    }

    @Override
    public int getRed() {
        return getRGB().red;
    }

    @Override
    public int getGreen() {
        return getRGB().green;
    }

    @Override
    public int getBlue() {
        return getRGB().blue;
    }

    @Override
    public void setAlpha(int alpha) {
        throw new UnsupportedOperationException("alpha");
    }

    @Override
    public void setRed(int red) {
        RGB rgb = getRGB();
        rgb.red = red;
        setRGB(rgb);
    }

    @Override
    public void setGreen(int green) {
        RGB rgb = getRGB();
        rgb.green = green;
        setRGB(rgb);
    }

    @Override
    public void setBlue(int blue) {
        RGB rgb = getRGB();
        rgb.blue = blue;
        setRGB(rgb);
    }

}
