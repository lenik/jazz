package net.bodz.bas.i18n.unit.std;

public class PixelUnit
        extends LengthUnit {

    private static final long serialVersionUID = 1L;

    /**
     * @see javax.swing.text.html.CSS.LengthUnit
     */
    public static final double PIXELS_PER_POINT = 1.3;

    PixelUnit() {
        super("pixel", "px", PIXELS_PER_POINT, POINT);
    }

    @Override
    public int pixels(int ppi) {
        return 1;
    }

    @Override
    public double forPixels(int ppi, int pixels) {
        return pixels;
    }

    private static final PixelUnit instance = new PixelUnit();

    public static PixelUnit getInstance() {
        return instance;
    }

}
