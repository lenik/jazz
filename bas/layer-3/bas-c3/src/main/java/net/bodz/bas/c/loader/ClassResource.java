package net.bodz.bas.c.loader;

import java.net.URL;

import net.bodz.bas.io.res.builtin.URLResource;

public class ClassResource
        extends net.bodz.bas.c.type.ClassResource {

    /**
     * The class bytes resource of the <code>clazz</code>.
     * 
     * @return <code>null</code> if the resource isn't existed.
     */
    public static URLResource getClassBytes(Class<?> clazz) {
        URL url = getClassBytesURL(clazz);
        if (url == null)
            return null;
        else
            return new URLResource(url);
    }

    /**
     * @param extension
     *            don't include the dot(.)
     * @return <code>null</code> if the data resource isn't existed.
     */
    public static URLResource getData(Class<?> clazz, String extension) {
        URL url = getDataURL(clazz, extension);
        if (url == null)
            return null;
        else
            return new URLResource(url);
    }

}
