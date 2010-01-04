package net.bodz.bas.text.lop.util;

/**
 * Position tellable
 */
public interface Tellable {

    /**
     * Current position.
     * 
     * @return -1 if current position is unknown
     */
    long tell();

}
