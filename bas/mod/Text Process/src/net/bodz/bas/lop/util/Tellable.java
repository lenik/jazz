package net.bodz.bas.lop.util;

import java.io.IOException;

/**
 * Position tellable
 */
public interface Tellable {

    /** current offset, 0-based */
    long tell() throws IOException;

}
