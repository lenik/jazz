package net.bodz.bas.c.java.beans;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;

import com.googlecode.openbeans.SimpleBeanInfo;

@SuppressWarnings("removal")
public class AbstractBeanInfo
        extends SimpleBeanInfo {

    private final String simpleName;

    public AbstractBeanInfo() {
        String name = getClass().getSimpleName();
        if (name.endsWith("BeanInfo"))
            name = name.substring(0, name.length() - 8);
        simpleName = name;
    }

    @Override
    public java.awt.Image getIcon(int iconKind) {
        // XXX - use Logger instead...
        // try {
        // Files.append(new File("t:/geticon"), //
        // "info=" + getClass() + ", kind=" + iconKind + "\n");
        // } catch (IOException e) {
        // }
        switch (iconKind) {
        case ICON_MONO_16x16:
            return loadImage(simpleName + "-16x16x4.gif");
        case ICON_MONO_32x32:
            return loadImage(simpleName + "-32x32x4.gif");
        case ICON_COLOR_16x16:
            return loadImage(simpleName + "-16x16x8.gif");
        case ICON_COLOR_32x32:
        default:
            return loadImage(simpleName + "-32x32x8.gif");
        }
        // return null;
    }

    static class GetContent
            implements
                PrivilegedAction<Object> {
        Class<?> c;
        String resourceName;

        public GetContent(Class<?> c, String resourceName) {
            super();
            this.c = c;
            this.resourceName = resourceName;
        }

        @Override
        public Object run() {
            URL url;
            if ((url = c.getResource(resourceName)) == null) {
                return null;
            } else {
                try {
                    return url.getContent();
                } catch (IOException ioe) {
                    return null;
                }
            }
        }
    }

    public static Image loadImage(final Class<?> c, final String resourceName) {
        try {
            @SuppressWarnings({ "deprecation" })
            ImageProducer producer = (ImageProducer) AccessController.doPrivileged(//
                    new GetContent(c, resourceName));
            if (producer == null)
                return null;
            Toolkit tk = Toolkit.getDefaultToolkit();
            return tk.createImage(producer);
        } catch (Exception ex) {
            return null;
        }
    }

}
