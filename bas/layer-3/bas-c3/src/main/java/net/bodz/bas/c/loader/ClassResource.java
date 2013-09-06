package net.bodz.bas.c.loader;

import net.bodz.bas.io.res.builtin.URLResource;

public class ClassResource
        extends net.bodz.bas.c.type.ClassResource {

    /**
     * The class bytes resource of the <code>clazz</code>.
     */
    public static URLResource getClassBytes(Class<?> clazz) {
        return new URLResource(getClassBytesURL(clazz));
    }

    /**
     * @param extension
     *            don't include the dot(.)
     */
    public static URLResource getData(Class<?> clazz, String extension) {
        return new URLResource(getDataURL(clazz, extension));
    }

}
