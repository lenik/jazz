package net.bodz.bas.c.loader.scan;

import java.net.URI;
import java.net.URL;

public interface IResourceItem {

//    String getName();

    /**
     * relative from container path.
     */
    String getRelativePath();

    URI toURI();

    URL toURL();

}
