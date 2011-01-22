package net.bodz.swt.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

public class LooseDeviceResources extends StrictDeviceResources {

    public static boolean displayError   = false;

    static final int      errorImageSize = 10;

    private Image         errorImage;
    private ImageData     errorImageData;

    public LooseDeviceResources(Device device, StrictDeviceResources reuse) {
        super(device, reuse);
    }

    void createErrorImages() {
        if (errorImage != null)
            return;
        errorImage = new Image(device, 10, 10);
        errorImageData = errorImage.getImageData();
        GC gc = new GC(errorImage);
        gc.setBackground(getColor(SWT.COLOR_RED));
        gc.fillRectangle(0, 0, errorImageSize, errorImageSize);
        gc.dispose();
    }

    public ImageData getErrorImageData() {
        createErrorImages();
        return errorImageData;
    }

    public ImageData getErrorImageData(RuntimeException e) {
        if (displayError)
            return getErrorImageData();
        throw e;
    }

    public ImageData getErrorImageData(Exception e) {
        if (displayError)
            return getErrorImageData();
        throw new RuntimeException(e);
    }

    public Image getErrorImage() {
        createErrorImages();
        return errorImage;
    }

    public Image getErrorImage(RuntimeException e) {
        if (displayError)
            return getErrorImage();
        throw e;
    }

    public Image getErrorImage(Exception e) {
        if (displayError)
            return getErrorImage();
        throw new RuntimeException(e);
    }

    /** {@inheritDoc} */
    @Override
    public ImageData getImageData(InputStream in) {
        try {
            return super.getImageData(in);
        } catch (IOException e) {
            return getErrorImageData(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public ImageData getImageData(File file) {
        try {
            return super.getImageData(file);
        } catch (IOException e) {
            return getErrorImageData(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public ImageData getImageData(URL url) {
        try {
            return super.getImageData(url);
        } catch (IOException e) {
            return getErrorImageData(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public ImageData getImageDataRes(String classResPath) {
        try {
            return super.getImageDataRes(classResPath, 1);
        } catch (IOException e) {
            return getErrorImageData(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public ImageData getImageDataRes(String classResPath, int caller) {
        try {
            return super.getImageDataRes(classResPath, 1 + caller);
        } catch (IOException e) {
            return getErrorImageData(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public ImageData getImageDataRes(Class<?> clazz, String classResPath) {
        try {
            return super.getImageDataRes(clazz, classResPath);
        } catch (IOException e) {
            return getErrorImageData(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public ImageData getImageDataRes(ClassLoader loader, String loaderResPath) {
        try {
            return super.getImageDataRes(loader, loaderResPath);
        } catch (IOException e) {
            return getErrorImageData(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Image getImage(InputStream in) {
        try {
            return super.getImage(in);
        } catch (IOException e) {
            return getErrorImage(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Image getImage(File file) {
        try {
            return super.getImage(file);
        } catch (IOException e) {
            return getErrorImage(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Image getImage(URL url) {
        try {
            return super.getImage(url);
        } catch (IOException e) {
            return getErrorImage(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Image getImageRes(String classResPath) {
        try {
            return super.getImageRes(classResPath, 1);
        } catch (IOException e) {
            return getErrorImage(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Image getImageRes(String classResPath, int caller) {
        try {
            return super.getImageRes(classResPath, 1 + caller);
        } catch (IOException e) {
            return getErrorImage(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Image getImageRes(Class<?> clazz, String classResPath) {
        try {
            return super.getImageRes(clazz, classResPath);
        } catch (IOException e) {
            return getErrorImage(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Image getImageRes(ClassLoader loader, String loaderResPath) {
        try {
            return super.getImageRes(loader, loaderResPath);
        } catch (IOException e) {
            return getErrorImage(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public ImageData _castImageData(Object var) {
        try {
            return super._castImageData(var);
        } catch (IOException e) {
            return getErrorImageData(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Image _castImage(Object var) {
        try {
            return super._castImage(var);
        } catch (IOException e) {
            return getErrorImage(e);
        }
    }

}
