package net.bodz.swt.image.accessor;

import org.eclipse.swt.graphics.RGB;

public interface ImageAccessor
        extends Cloneable {

    ImageAccessor clone();

    int getWidth();

    int getHeight();

    int getPosition();

    void setPosition(int position);

    int getX();

    int getY();

    void getXY(int[] xy);

    /**
     * you must guarantee that x between 0 (inclusive) and width (exclusive)
     */
    void setX(int x);

    /**
     * you must guarantee that y between 0 (inclusive) and height (exclusive)
     */
    void setY(int y);

    /**
     * you must guarantee that x between 0 (inclusive) and width (exclusive) and y between 0
     * (inclusive) and height (exclusive)
     */
    void setXY(int x, int y);

    /**
     * you must guarantee that x between 0 (inclusive) and width (exclusive)
     */
    void incX();

    /**
     * you must guarantee that x between 0 (inclusive) and width (exclusive)
     */
    void decX();

    /**
     * you must guarantee that y between 0 (inclusive) and height (exclusive)
     */
    void incY();

    /**
     * you must guarantee that y between 0 (inclusive) and height (exclusive)
     */
    void decY();

    int getPixel();

    void setPixel(int pixel);

    RGB getRGB();

    RGB copyRGB();

    void getRGB(RGB rgb);

    void setRGB(RGB rgb);

    void getRGB(int[] rgb);

    void setRGB(int[] rgb);

    /** pre-multiplied alpha? */
    int getAlpha();

    int getRed();

    int getGreen();

    int getBlue();

    /** pre-multiplied alpha? */
    void setAlpha(int alpha);

    void setRed(int red);

    void setGreen(int green);

    void setBlue(int blue);

}
