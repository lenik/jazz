package net.bodz.bas.sio.position;

public interface IXYTellable
        extends ITellable {

    /** current line, 0-based */
    int tellY();

    /** current column, 0-based */
    int tellX();

}
