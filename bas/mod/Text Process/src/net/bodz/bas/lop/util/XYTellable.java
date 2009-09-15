package net.bodz.bas.lop.util;

public interface XYTellable extends Tellable {

    /** current line, 0-based */
    int tellY();

    /** current column, 0-based */
    int tellX();

}
