package net.bodz.bas.doc.node.conv;

public class WordUnits {

    public static double toPoints(Object mTwips) {
        Number twipsNum = (Number) mTwips;
        double twips = twipsNum.doubleValue();
        double points = twips / 20.0;
        // double inch = points / 72.0;
        // double cm = inch * 2.54;
        return points;
    }

    public static double mm2Points(double mm) {
        double inch = mm / 25.4;
        double points = inch * 72.0;
        return points;
    }

}
