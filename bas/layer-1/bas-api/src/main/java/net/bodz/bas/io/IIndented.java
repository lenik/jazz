package net.bodz.bas.io;

public interface IIndented {

    /**
     * @return The indent-level after enterred.
     */
    int enter();

    /**
     * @return The indent-level after left.
     */
    int leave();

}
