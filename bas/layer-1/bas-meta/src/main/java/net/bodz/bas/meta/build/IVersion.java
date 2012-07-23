package net.bodz.bas.meta.build;

import java.io.Serializable;

public interface IVersion
        extends Comparable<IVersion>, Serializable {

    /**
     * The first element must be major version number (integer).
     * 
     * The second element must be minor version number (integer).
     * 
     * major.minor.release...
     */
    String[] getVersionElements();

}
