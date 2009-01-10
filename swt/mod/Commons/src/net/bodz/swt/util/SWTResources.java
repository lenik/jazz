package net.bodz.swt.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import net.bodz.bas.io.CharOuts;
import net.bodz.bas.lang.Caller;
import net.bodz.bas.lang.err.IllegalArgumentTypeException;
import net.bodz.bas.loader.UCL;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

import com.swtdesigner.SWTResourceManager;

public class SWTResources {

    public static Image getImage(String path) {
        /* OPT: to reuse the existing image cache. */
        return SWTResourceManager.getImage(path);
    }

    public static Image getImage(InputStream in) {
        Display display = Display.getCurrent();
        ImageData data = new ImageData(in);
        if (data.transparentPixel > 0)
            return new Image(display, data, data.getTransparencyMask());
        return new Image(display, data);
    }

    public static Image getImage(URL url) throws IOException {
        if (url == null)
            return null;
        return getImage(url.openStream());
    }

    public static Image getImage(Object imageOrPath) {
        if (imageOrPath == null)
            return null;
        if (imageOrPath instanceof Image)
            return (Image) imageOrPath;
        if (imageOrPath instanceof String)
            return getImage((String) imageOrPath);
        if (imageOrPath instanceof URL)
            try {
                return getImage((URL) imageOrPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        throw new IllegalArgumentTypeException(imageOrPath.getClass());
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
        if (path.startsWith("/"))
            path = path.substring(1);
        ClassLoader loader = Caller.getCallerClassLoader();
        InputStream in = loader.getResourceAsStream(path);
        if (in == null) {
            UCL.dump(loader, CharOuts.stderr);
            CharOuts.stderr.flush();
            throw new IllegalArgumentException("bad path: " + path);
        }
        return getImage(in);
        // return getImageRes(Caller.getCallerClass(1), path);
    }

    public static Image getImageRes(Object imageOrPath) {
        return getImageRes(Caller.getCallerClass(1), imageOrPath);
    }

}
