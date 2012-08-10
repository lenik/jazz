package net.bodz.bas.c.java.beans;

import java.beans.SimpleBeanInfo;

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

    public static java.awt.Image loadImage(final Class<?> c, final String resourceName) {
        try {
            java.awt.image.ImageProducer ip = (java.awt.image.ImageProducer) java.security.AccessController
                    .doPrivileged(new java.security.PrivilegedAction<Object>() {
                        public Object run() {
                            java.net.URL url;
                            if ((url = c.getResource(resourceName)) == null) {
                                return null;
                            } else {
                                try {
                                    return url.getContent();
                                } catch (java.io.IOException ioe) {
                                    return null;
                                }
                            }
                        }
                    });
            if (ip == null)
                return null;
            java.awt.Toolkit tk = java.awt.Toolkit.getDefaultToolkit();
            return tk.createImage(ip);
        } catch (Exception ex) {
            return null;
        }
    }

}
