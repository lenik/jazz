package net.bodz.bas.i18n.unit.std;

public class PointUnit
        extends LengthUnit {

    private static final long serialVersionUID = 1L;

    PointUnit() {
        super("point", "pt", 1 / 72.0, INCH);
    }

    private static final PointUnit instance = new PointUnit();

    public static PointUnit getInstance() {
        return instance;
    }

}
