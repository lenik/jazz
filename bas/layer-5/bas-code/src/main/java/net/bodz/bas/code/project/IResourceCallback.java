package net.bodz.bas.code.project;

import java.net.URL;

public interface IResourceCallback {

    /**
     * @return <code>false</code> to cancel search.
     */
    boolean resource(String name, URL url);

}
