package net.bodz.swt.c.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.*;

import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.err.IllegalArgumentTypeException;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.jvm.stack.Caller;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.t.map.IndexMap;

public class StrictDeviceResources
        implements II18nCapable {

    /** XXX - don't know how to dispose... */
    static final boolean weakCache = false;
    public static boolean diag = true;

    Device device;

    Map<File, Image> imageCache;
    Map<File, ImageData> imageDataCache;

    Map<RGB, Color> colorCache;
    Map<FontData, Font> fontCache;

    IndexMap<Color> indexedColors;

    public StrictDeviceResources(Device device, StrictDeviceResources reuse) {
        if (device == null)
            throw new NullPointerException("device");
        this.device = device;
        if (reuse == null) {
            if (weakCache) {
                imageCache = new WeakHashMap<File, Image>();
                imageDataCache = new WeakHashMap<File, ImageData>();
                colorCache = new WeakHashMap<RGB, Color>();
                fontCache = new WeakHashMap<FontData, Font>();
            } else {
                imageCache = new HashMap<File, Image>();
                imageDataCache = new HashMap<File, ImageData>();
                colorCache = new HashMap<RGB, Color>();
                fontCache = new HashMap<FontData, Font>();
            }
            indexedColors = new IndexMap<Color>();
        } else {
            imageCache = reuse.imageCache;
            imageDataCache = reuse.imageDataCache;
            colorCache = reuse.colorCache;
            fontCache = reuse.fontCache;
            indexedColors = reuse.indexedColors;
        }
    }

    public ImageData getImageData(InputStream in)
            throws IOException {
        if (in == null)
            throw new NullPointerException("in");
        try {
            return new ImageData(in);
        } catch (SWTException e) {
            if (e.code == SWT.ERROR_IO)
                throw new IOException(tr._("SWT I/O Exception"), e);
            throw e;
        }
    }

    public ImageData getImageData(File file)
            throws IOException {
        ImageData imageData = imageDataCache.get(file);
        if (imageData != null)
            return imageData;
        FileInputStream in = new FileInputStream(file);
        imageData = getImageData(in);
        imageDataCache.put(file, imageData);
        return imageData;
    }

    public ImageData getImageData(URL url)
            throws IOException {
        File file = FileURL.toFile(url);
        ImageData imageData = imageDataCache.get(file);
        if (imageData == null) {
            InputStream in = url.openStream();
            imageData = getImageData(in);
            imageDataCache.put(file, imageData);
        }
        return imageData;
    }

    /**
     * Using the caller loader to locate the resource.
     * <p>
     * Example:
     * 
     * <pre>
     * getImageDataRes(&quot;/foo/bar/App.jpg&quot;)
     * </pre>
     * 
     * @see #getImageDataRes(Class, String)
     */
    public ImageData getImageDataRes(String classResPath)
            throws IOException {
        return getImageDataRes(classResPath, 1);
    }

    public ImageData getImageDataRes(String classResPath, int caller)
            throws IOException {
        Class<?> callerClass = Caller.getCallerClass(1 + caller);
        return getImageDataRes(callerClass, classResPath);
    }

    /**
     * Example:
     * 
     * <pre>
     * getImageDataRes(App.class, &quot;/foo/bar/App.jpg&quot;)
     * </pre>
     * 
     * @param classResPath
     *            class-relative resource path, if the referred resource isn't within the package
     *            this <code>clazz</code> belongs to, then the path should precede with '/'.
     */
    public ImageData getImageDataRes(Class<?> clazz, String classResPath)
            throws IOException {
        URL url = clazz.getResource(classResPath);
        if (url == null) {
            diagResPath(clazz, classResPath);
            throw new IOException(tr._("bad resource: ") + classResPath);
        }
        return getImageData(url);
    }

    /**
     * Example:
     * 
     * <pre>
     * getImageDataRes(classLoader, &quot;foo/bar/App.jpg&quot;)
     * </pre>
     * 
     * @param loaderResPath
     *            absolute resource path, don't start with '/'.
     */
    public ImageData getImageDataRes(ClassLoader loader, String loaderResPath)
            throws IOException {
        URL url = loader.getResource(loaderResPath);
        if (url == null) {
            diagResPath(loader, loaderResPath);
            throw new IOException(tr._("bad resource: ") + loaderResPath);
        }
        return getImageData(url);
    }

    public ImageData _castImageData(Object var)
            throws IOException {
        if (var == null)
            return null;
        if (var instanceof ImageData)
            return (ImageData) var;
        if (var instanceof File)
            return getImageData((File) var);
        if (var instanceof URL)
            return getImageData((URL) var);
        if (var instanceof String)
            return getImageDataRes((String) var, 1);
        throw new IllegalArgumentTypeException(var.getClass());
    }

    /**
     * @return <code>null</code> if <code>imageData</code> is <code>null</code>.
     */
    public Image getImage(ImageData imageData) {
        if (imageData == null)
            return null;
        Image image;
        if (imageData.transparentPixel > 0) {
            ImageData maskData = imageData.getTransparencyMask();
            image = new Image(device, imageData, maskData);
        } else
            image = new Image(device, imageData);
        return image;
    }

    /**
     * @see #getImageData(InputStream)
     */
    public Image getImage(InputStream in)
            throws IOException {
        ImageData data = getImageData(in);
        return getImage(data);
    }

    /**
     * @see #getImageData(File)
     */
    public Image getImage(File file)
            throws IOException {
        Image image = imageCache.get(file);
        if (image == null) {
            FileInputStream in = new FileInputStream(file);
            image = getImage(in);
            imageCache.put(file, image);
        }
        return image;
    }

    /**
     * @see #getImageData(URL)
     */
    public Image getImage(URL url)
            throws IOException {
        File file = FileURL.toFile(url);
        Image image = imageCache.get(file);
        if (image == null) {
            InputStream in = url.openStream();
            image = getImage(in);
            imageCache.put(file, image);
        }
        return image;
    }

    /**
     * @see #getImageDataRes(String)
     */
    public Image getImageRes(String classResPath)
            throws IOException {
        return getImageRes(classResPath, 1);
    }

    public Image getImageRes(String classResPath, int caller)
            throws IOException {
        Class<?> callerClass = Caller.getCallerClass(1 + caller);
        return getImageRes(callerClass, classResPath);
    }

    /**
     * @see #getImageDataRes(Class, String)
     */
    public Image getImageRes(Class<?> clazz, String classResPath)
            throws IOException {
        URL url = clazz.getResource(classResPath);
        if (url == null) {
            diagResPath(clazz, classResPath);
            throw new IOException(tr._("bad resource: ") + classResPath);
        }
        return getImage(url);
    }

    /**
     * @see #getImageDataRes(ClassLoader, String)
     */
    public Image getImageRes(ClassLoader loader, String loaderResPath)
            throws IOException {
        URL url = loader.getResource(loaderResPath);
        if (url == null) {
            diagResPath(loader, loaderResPath);
            throw new IOException(tr._("bad resource: ") + loaderResPath);
        }
        return getImage(url);
    }

    /**
     * @see #_castImageData(Object)
     */
    public Image _castImage(Object var)
            throws IOException {
        if (var == null)
            return null;
        if (var instanceof Image)
            return (Image) var;
        if (var instanceof ImageData)
            return new Image(device, (ImageData) var);
        if (var instanceof File)
            return getImage((File) var);
        if (var instanceof URL)
            return getImage((URL) var);
        if (var instanceof String)
            return getImageRes((String) var, 1);
        throw new IllegalArgumentTypeException(var.getClass());
    }

    public Color getColor(int red, int green, int blue) {
        RGB rgb = new RGB(red, green, blue);
        return getColor(rgb);
    }

    public Color getColor(RGB rgb) {
        Color color = colorCache.get(rgb);
        if (color == null) {
            color = new Color(device, rgb);
            colorCache.put(rgb, color);
        }
        return color;
    }

    public Color getColor(int colorId) {
        Color color = indexedColors.get(colorId);
        if (color == null) {
            color = device.getSystemColor(colorId);
            indexedColors.put(colorId, color);
        }
        return color;
    }

    public Font getFont(String name, int height, int style) {
        FontData fd = new FontData(name, height, style);
        return getFont(fd);
    }

    public Font getFont(FontData fd) {
        Font font = fontCache.get(fd);
        if (font == null) {
            font = new Font(device, fd);
            fontCache.put(fd, font);
        }
        return font;
    }

    static void diagResPath(ClassLoader loader, String path) {
        if (diag) {
            System.err.println(tr._("bad resource: ") + path);
            dumpLoader(loader);
        }
    }

    static void diagResPath(Class<?> clazz, String path) {
        if (diag) {
            System.err.printf(tr._("Bad resource path: %s, class=%s\n"), path, clazz);
            dumpLoader(clazz.getClassLoader());
        }
    }

    static void dumpLoader(ClassLoader loader) {
        IPrintOut out = Stdio.cerr;
        URLClassLoaders.dump(loader, out);
        out.flush();
    }

}
