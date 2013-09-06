package net.bodz.bas.io;

/**
 * Position tellable
 */
public interface ITellable {

    /**
     * Current position.
     * 
     * @return -1 if current position is unknown
     */
    long tell();

}
