package net.bodz.swt.gui.style;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.gui.style.IImageData;
import net.bodz.bas.gui.style.TransparencyType;

public class SwtImageMapper
        implements IImageData {

    private ImageData imageData;

    protected SwtImageMapper() {
    }

    public SwtImageMapper(ImageData imageData) {
        if (imageData == null)
            throw new NullPointerException("imageData");
        this.imageData = imageData;
    }

    public ImageData getImageData() {
        return imageData;
    }

    public void setImageData(ImageData imageData) {
        if (imageData == null)
            throw new NullPointerException("imageData");
        this.imageData = imageData;
    }

    @Override
    public int getWidth() {
        ImageData imageData = getImageData();
        return imageData.width;
    }

    @Override
    public int getHeight() {
        ImageData imageData = getImageData();
        return imageData.height;
    }

    @Override
    public int getDepth() {
        ImageData imageData = getImageData();
        return imageData.height;
    }

    @Override
    public int getScanlinePad() {
        ImageData imageData = getImageData();
        return imageData.scanlinePad;
    }

    @Override
    public byte[] getData() {
        ImageData imageData = getImageData();
        return imageData.data;
    }

    @Override
    public int getAlpha(int x, int y) {
        ImageData imageData = getImageData();
        return imageData.getAlpha(x, y);
    }

    @Override
    public void getAlphas(int x, int y, int getWidth, byte[] alphas, int startIndex) {
        ImageData imageData = getImageData();
        imageData.getAlphas(x, y, getWidth, alphas, startIndex);
    }

    @Override
    public int getPixel(int x, int y) {
        ImageData imageData = getImageData();
        return imageData.getPixel(x, y);
    }

    @Override
    public void getPixels(int x, int y, int getWidth, byte[] pixels, int startIndex) {
        ImageData imageData = getImageData();
        imageData.getPixels(x, y, getWidth, pixels, startIndex);
    }

    @Override
    public void getPixels(int x, int y, int getWidth, int[] pixels, int startIndex) {
        ImageData imageData = getImageData();
        imageData.getPixels(x, y, getWidth, pixels, startIndex);
    }

    @Override
    public TransparencyType getTransparencyType() {
        ImageData imageData = getImageData();
        int _transparencyType = imageData.getTransparencyType();

        switch (_transparencyType) {
        case SWT.TRANSPARENCY_NONE:
            return TransparencyType.none;

        case SWT.TRANSPARENCY_MASK:
            return TransparencyType.mask;

        case SWT.TRANSPARENCY_PIXEL:
            return TransparencyType.pixel;

        case SWT.TRANSPARENCY_ALPHA:
            return TransparencyType.alpha;

        default:
            throw new UnexpectedException("Illegal transparency type: " + _transparencyType);
        }
    }

    @Override
    public IImageData getTransparencyMask() {
        ImageData imageData = getImageData();
        ImageData mask = imageData.getTransparencyMask();
        return new SwtImageMapper(mask);
    }

    @Override
    public IImageData scaledTo(int width, int height) {
        ImageData imageData = getImageData();
        ImageData result = imageData.scaledTo(width, height);
        return new SwtImageMapper(result);
    }

    @Override
    public void setAlpha(int x, int y, int alpha) {
        ImageData imageData = getImageData();
        imageData.setAlpha(x, y, alpha);
    }

    @Override
    public void setAlphas(int x, int y, int putWidth, byte[] alphas, int startIndex) {
        ImageData imageData = getImageData();
        imageData.setAlphas(x, y, putWidth, alphas, startIndex);
    }

    @Override
    public void setPixel(int x, int y, int pixelValue) {
        ImageData imageData = getImageData();
        imageData.setPixel(x, y, pixelValue);
    }

    @Override
    public void setPixels(int x, int y, int putWidth, byte[] pixels, int startIndex) {
        ImageData imageData = getImageData();
        imageData.setPixels(x, y, putWidth, pixels, startIndex);
    }

    @Override
    public void setPixels(int x, int y, int putWidth, int[] pixels, int startIndex) {
        ImageData imageData = getImageData();
        imageData.setPixels(x, y, putWidth, pixels, startIndex);
    }

    public static ImageData convert(IImageData src) {
        int width = src.getWidth();
        int height = src.getHeight();
        int depth = src.getDepth();
        // src.getPaletteData();
        int scanlinePad = src.getScanlinePad();
        byte[] data = src.getData();

        ImageData imageData = new ImageData(width, height, depth, null, scanlinePad, data);
        return imageData;
    }

    public static Image convert(Device device, IImageData src) {
        ImageData imageData = convert(src);
        Image image = new Image(device, imageData);
        return image;
    }

}
