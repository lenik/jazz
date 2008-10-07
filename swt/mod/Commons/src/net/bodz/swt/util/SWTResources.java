package net.bodz.swt.util;

import org.eclipse.swt.graphics.Image;

import com.swtdesigner.SWTResourceManager;

public class SWTResources {

    @SuppressWarnings("deprecation")
    public static Image getImage(String path) {
        /* OPT: to reuse the existing image cache. */
        return SWTResourceManager.getImage(path);
    }

    @SuppressWarnings("deprecation")
    public static Image getImage(Class<?> clazz, String path) {
        /* OPT: to reuse the existing image cache. */
        return SWTResourceManager.getImage(clazz, path);
    }

    public static Image getImage(Object imageOrPath) {
        if (imageOrPath == null)
            return null;
        if (imageOrPath instanceof Image)
            return (Image) imageOrPath;
        assert imageOrPath instanceof String;
        return getImage((String) imageOrPath);
    }

    public static Image getImage(Class<?> clazz, Object imageOrPath) {
        if (imageOrPath == null)
            return null;
        if (imageOrPath instanceof Image)
            return (Image) imageOrPath;
        assert imageOrPath instanceof String;
        return getImage(clazz, (String) imageOrPath);
    }

}
