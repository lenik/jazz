package net.bodz.swt.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class SWTResources {

    public static final StrictDeviceResources strict;
    public static final LooseDeviceResources loose;

    static {
        Display display = Display.getCurrent();
        if (display == null)
            display = Display.getDefault();
        strict = new StrictDeviceResources(display, null);
        loose = new LooseDeviceResources(display, strict);
    }

    public static Image _castImage(Object var) {
        return loose._castImage(var);
    }

    public static ImageData _castImageData(Object var)
            throws IOException {
        return loose._castImageData(var);
    }

    public static Image getErrorImage() {
        return loose.getErrorImage();
    }

    public static Image getErrorImage(Exception e) {
        return loose.getErrorImage(e);
    }

    public static ImageData getErrorImageData() {
        return loose.getErrorImageData();
    }

    public static ImageData getErrorImageData(Exception e) {
        return loose.getErrorImageData(e);
    }

    public static ImageData getImageData(InputStream in) {
        return loose.getImageData(in);
    }

    public static ImageData getImageData(File file) {
        return loose.getImageData(file);
    }

    public static ImageData getImageData(URL url) {
        return loose.getImageData(url);
    }

    public static ImageData getImageDataRes(String classResPath) {
        return loose.getImageDataRes(classResPath, 1);
    }

    public static ImageData getImageDataRes(String classResPath, int caller) {
        return loose.getImageDataRes(classResPath, 1 + caller);
    }

    public static ImageData getImageDataRes(Class<?> clazz, String classResPath) {
        return loose.getImageDataRes(clazz, classResPath);
    }

    public static ImageData getImageDataRes(ClassLoader loader, String loaderResPath) {
        return loose.getImageDataRes(loader, loaderResPath);
    }

    public static Image getImage(ImageData imageData) {
        return loose.getImage(imageData);
    }

    public static Image getImage(InputStream in) {
        return loose.getImage(in);
    }

    public static Image getImage(File file) {
        return loose.getImage(file);
    }

    public static Image getImage(URL url) {
        return loose.getImage(url);
    }

    public static Image getImageRes(String classResPath) {
        return loose.getImageRes(classResPath, 1);
    }

    public static Image getImageRes(String classResPath, int caller) {
        return loose.getImageRes(classResPath, 1 + caller);
    }

    public static Image getImageRes(Class<?> clazz, String classResPath) {
        return loose.getImageRes(clazz, classResPath);
    }

    public static Image getImageRes(ClassLoader loader, String loaderResPath) {
        return loose.getImageRes(loader, loaderResPath);
    }

    public static Color getColor(int colorId) {
        return loose.getColor(colorId);
    }

    public static Color getColor(int red, int green, int blue) {
        return loose.getColor(red, green, blue);
    }

    public static Color getColor(RGB rgb) {
        return loose.getColor(rgb);
    }

    public static Font getFont(FontData fd) {
        return loose.getFont(fd);
    }

    public static Font getFont(String name, int height, int style) {
        return loose.getFont(name, height, style);
    }

}
