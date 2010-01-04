package net.bodz.bas.text.lop.util;

public interface XYTellable extends Tellable {

    /** current line, 0-based */
    int tellY();

    /** current column, 0-based */
    int tellX();

}
