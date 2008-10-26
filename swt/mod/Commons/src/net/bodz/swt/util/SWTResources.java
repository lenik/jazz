package net.bodz.swt.util;

import net.bodz.bas.lang.Caller;

import org.eclipse.swt.graphics.Image;

import com.swtdesigner.SWTResourceManager;

public class SWTResources {

    public static Image getImage(String path) {
        /* OPT: to reuse the existing image cache. */
        return SWTResourceManager.getImage(path);
    }

    public static Image getImage(Object imageOrPath) {
        if (imageOrPath == null)
            return null;
        if (imageOrPath instanceof Image)
            return (Image) imageOrPath;
        assert imageOrPath instanceof String;
        return getImage((String) imageOrPath);
    }

    public static Image getImageRes(Class<?> clazz, String path) {
        /* OPT: to reuse the existing image cache. */
        return SWTResourceManager.getImage(clazz, path);
    }

    public static Image getImageRes(Class<?> clazz, Object imageOrPath) {
        if (imageOrPath == null)
            return null;
        if (imageOrPath instanceof Image)
            return (Image) imageOrPath;
        assert imageOrPath instanceof String;
        return getImageRes(clazz, (String) imageOrPath);
    }

    public static Image getImageRes(String path) {
        return getImageRes(Caller.getCallerClass(), path);
    }

    public static Image getImageRes(Object imageOrPath) {
        return getImageRes(Caller.getCallerClass(), imageOrPath);
    }

}
