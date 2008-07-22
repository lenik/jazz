package net.bodz.mda.parsers.io;

public interface DocumentPosition extends FilePosition {

    /** current line, 0-based */
    int line();

    /** current column, 0-based */
    int column();

}
