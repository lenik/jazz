package net.bodz.bas.doc.node.util;

import java.math.BigInteger;

import net.bodz.bas.doc.property.HorizAlignment;
import net.bodz.bas.doc.property.MeasureLength;

public interface IListStyle {

    default boolean isCss3() {
        return false;
    }

    boolean isOrdered();

    default BigInteger getStartNumber() {
        return BigInteger.ONE;
    }

    default boolean isContinuous() {
        return false;
    }

    default String format(int number) {
        return format(number, number);
    }

    String format(int number, int max);

    default String markdownFormat(int number) {
        return format(number);
    }

    HorizAlignment getJustify();

    MeasureLength getLeft();

    MeasureLength getHanging();

    MeasureLength getTabPosition();

}
