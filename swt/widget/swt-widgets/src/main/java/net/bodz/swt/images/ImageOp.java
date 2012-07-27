package net.bodz.swt.images;

import net.bodz.bas.err.NotImplementedException;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;

public class ImageOp {

    public static ImageAccessor access(ImageData image) {
        PaletteData palette = image.palette;
        if (palette.isDirect) {
            RGB rgb = palette.getRGB(0x12345678);
            int magic = ((rgb.red & 0xff) << 16) | ((rgb.green & 0xff) << 8) | (rgb.blue & 0xff);
            switch (image.depth) {
            case 32:
                switch (magic) {
                case 0x345678:
                    return new SWTImageAccessor.ARGB32(image);
                case 0x563412:
                    return new SWTImageAccessor.BGRA32(image);
                }
                throw new UnsupportedOperationException();
            case 24:
                return new SWTImageAccessor.RGB24(image);
            default:
                throw new NotImplementedException("Direct: " + image.depth);
            }
        } else {
            switch (image.depth) {
            case 8:
                return new SWTImageAccessor.Index8(image);
            case 4:
                return new SWTImageAccessor.Index4(image);
            case 2:
                return new SWTImageAccessor.Index2(image);
            case 1:
                return new SWTImageAccessor.Index1(image);
            case 16:
            default:
                throw new NotImplementedException("Indexed: " + image.depth);
            }
        }
    }

    public static ImageData rgb2hsb(ImageData rgbImage) {
        int width = rgbImage.width;
        int height = rgbImage.height;
        int depth = rgbImage.depth;
        PaletteData rgbPalette = rgbImage.palette;
        PaletteData hsbPalette;
        ImageData hsbImage;
        if (rgbPalette.isDirect) {
            hsbPalette = rgbPalette; // copy?
            hsbImage = new ImageData(width, height, depth, hsbPalette);
            ImageAccessor rgbp = ImageOp.access(rgbImage);
            ImageAccessor hsbp = ImageOp.access(hsbImage);
            for (int y = 0; y < height; y++) {
                rgbp.setXY(0, y);
                hsbp.setXY(0, y);
                for (int x = 0; x < width; x++) {
                    RGB rgb = rgbp.getRGB();
                    float[] hsb = rgb.getHSB();
                    hsbp.setRed((int) (255 * hsb[0]));
                    hsbp.setGreen((int) (255 * hsb[1]));
                    hsbp.setBlue((int) (255 * hsb[2]));
                    rgbp.incX();
                    hsbp.incX();
                }
            }
        } else {
            RGB[] rgbColors = rgbPalette.colors;
            RGB[] hsbColors = new RGB[rgbColors.length];
            for (int i = 0; i < rgbColors.length; i++) {
                float[] hsbf = rgbColors[i].getHSB();
                int h = (int) (255 * hsbf[0]);
                int s = (int) (255 * hsbf[1]);
                int b = (int) (255 * hsbf[2]);
                hsbColors[i] = new RGB(h, s, b);
            }
            hsbPalette = new PaletteData(hsbColors);
            int scanlinePad = rgbImage.scanlinePad;
            byte[] data = rgbImage.data;
            hsbImage = new ImageData(width, height, depth, hsbPalette, scanlinePad, data);
        }
        return hsbImage;
    }

    public static ImageData hsb2rgb(ImageData hsbImage) {
        int width = hsbImage.width;
        int height = hsbImage.height;
        int depth = hsbImage.depth;
        PaletteData hsbPalette = hsbImage.palette;
        PaletteData rgbPalette;
        ImageData rgbImage;
        if (hsbPalette.isDirect) {
            rgbPalette = hsbPalette; // copy?
            rgbImage = new ImageData(width, height, depth, rgbPalette);
            ImageAccessor hsbp = ImageOp.access(hsbImage);
            ImageAccessor rgbp = ImageOp.access(rgbImage);
            for (int y = 0; y < height; y++) {
                hsbp.setXY(0, y);
                rgbp.setXY(0, y);
                for (int x = 0; x < width; x++) {
                    RGB hsb = hsbp.getRGB();
                    RGB rgb = new RGB(//
                            hsb.red / 255.0f, //
                            hsb.green / 255.0f, //
                            hsb.blue / 255.0f);
                    rgbp.setRGB(rgb);
                    hsbp.incX();
                    rgbp.incX();
                }
            }
        } else {
            RGB[] hsbColors = hsbPalette.colors;
            RGB[] rgbColors = new RGB[hsbColors.length];
            for (int i = 0; i < hsbColors.length; i++) {
                RGB hsb = hsbColors[i];
                RGB rgb = new RGB(//
                        hsb.red / 255.0f, //
                        hsb.green / 255.0f, //
                        hsb.blue / 255.0f);
                rgbColors[i] = rgb;
            }
            rgbPalette = new PaletteData(rgbColors);
            int scanlinePad = hsbImage.scanlinePad;
            byte[] data = hsbImage.data;
            rgbImage = new ImageData(width, height, depth, rgbPalette, scanlinePad, data);
        }
        return rgbImage;
    }

}
