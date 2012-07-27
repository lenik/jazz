package net.bodz.swt.util;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.swt.nls.CommonNLS;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;

public class AWTBridge {

    /**
     * @see - SWT Snippet 156
     */
    public static BufferedImage convertToAWT(ImageData data) {
        ColorModel colorModel = null;
        PaletteData palette = data.palette;
        if (palette.isDirect) {
            colorModel = new DirectColorModel(data.depth, palette.redMask, palette.greenMask, palette.blueMask);
            BufferedImage bufferedImage = new BufferedImage(//
                    colorModel, //
                    colorModel.createCompatibleWritableRaster(data.width, data.height), // raster
                    false, // is raster pre-multipled
                    null // properties
            );
            WritableRaster raster = bufferedImage.getRaster();
            // XXX - optimize... {
            int[] pixelArray = new int[3];
            for (int y = 0; y < data.height; y++) {
                for (int x = 0; x < data.width; x++) {
                    int pixel = data.getPixel(x, y);
                    RGB rgb = palette.getRGB(pixel);
                    pixelArray[0] = rgb.red;
                    pixelArray[1] = rgb.green;
                    pixelArray[2] = rgb.blue;
                    raster.setPixels(x, y, 1, 1, pixelArray);
                }
            }
            // }
            return bufferedImage;
        } else {
            RGB[] rgbs = palette.getRGBs();
            byte[] red = new byte[rgbs.length];
            byte[] green = new byte[rgbs.length];
            byte[] blue = new byte[rgbs.length];
            for (int i = 0; i < rgbs.length; i++) {
                RGB rgb = rgbs[i];
                red[i] = (byte) rgb.red;
                green[i] = (byte) rgb.green;
                blue[i] = (byte) rgb.blue;
            }
            if (data.transparentPixel != -1) {
                colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue, data.transparentPixel);
            } else {
                colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue);
            }
            BufferedImage bufferedImage = new BufferedImage(//
                    colorModel, //
                    colorModel.createCompatibleWritableRaster(data.width, data.height), //
                    false, //
                    null //
            );
            WritableRaster raster = bufferedImage.getRaster();
            // XXX - optimize... {
            int[] pixelArray = new int[1];
            for (int y = 0; y < data.height; y++) {
                for (int x = 0; x < data.width; x++) {
                    int pixel = data.getPixel(x, y);
                    pixelArray[0] = pixel;
                    raster.setPixel(x, y, pixelArray);
                }
            }
            // }
            return bufferedImage;
        }
    }

    /**
     * @see - SWT Snippet 156
     */
    public static ImageData convertToSWT(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        ColorModel cm = bufferedImage.getColorModel();
        int pixelSize = cm.getPixelSize();
        if (cm instanceof DirectColorModel) {
            DirectColorModel dcm = (DirectColorModel) cm;
            PaletteData palette = new PaletteData(dcm.getRedMask(), dcm.getGreenMask(), dcm.getBlueMask());
            ImageData data = new ImageData(width, height, pixelSize, palette);
            WritableRaster raster = bufferedImage.getRaster();
            // XXX - optimize...
            int[] pixelArray = new int[3];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    raster.getPixel(x, y, pixelArray);
                    int pixel = palette.getPixel(new RGB(pixelArray[0], pixelArray[1], pixelArray[2]));
                    data.setPixel(x, y, pixel);
                }
            }
            return data;
        } else if (cm instanceof IndexColorModel) {
            IndexColorModel icm = (IndexColorModel) cm;
            int size = icm.getMapSize();
            byte[] reds = new byte[size];
            byte[] greens = new byte[size];
            byte[] blues = new byte[size];
            icm.getReds(reds);
            icm.getGreens(greens);
            icm.getBlues(blues);
            RGB[] rgbs = new RGB[size];
            for (int i = 0; i < rgbs.length; i++) {
                rgbs[i] = new RGB(reds[i] & 0xFF, greens[i] & 0xFF, blues[i] & 0xFF);
            }
            PaletteData palette = new PaletteData(rgbs);
            ImageData data = new ImageData(width, height, pixelSize, palette);
            data.transparentPixel = icm.getTransparentPixel();
            WritableRaster raster = bufferedImage.getRaster();
            // XXX - optimize...
            int[] pixelArray = new int[1];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    raster.getPixel(x, y, pixelArray);
                    data.setPixel(x, y, pixelArray[0]);
                }
            }
            return data;
        } else if (cm instanceof ComponentColorModel) {
            ComponentColorModel ccm = (ComponentColorModel) cm;
            int[] componentSize = ccm.getComponentSize();
            for (int i = 0; i < componentSize.length; i++)
                if (componentSize[i] != 8)
                    throw new NotImplementedException(CommonNLS.getString("AWTBridge.errBits") + componentSize[i]);
            int transferType = ccm.getTransferType();
            if (transferType != DataBuffer.TYPE_BYTE)
                throw new NotImplementedException(CommonNLS.getString("AWTBridge.errTransType") + transferType);
            // XXX - ??
            PaletteData palette = new PaletteData(0x0000ff, 0x00ff00, 0xff0000);
            ImageData data = new ImageData(width, height, pixelSize, palette);
            WritableRaster raster = bufferedImage.getRaster();
            int[] pixelArray = new int[4]; // XXX???
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    raster.getPixel(x, y, pixelArray);
                    int pixel = palette.getPixel(new RGB(pixelArray[0], pixelArray[1], pixelArray[2]));
                    data.setPixel(x, y, pixel);
                }
            }
            return data;
        } else
            throw new UnsupportedOperationException(CommonNLS.getString("AWTBridge.errColorModel")
                    + cm.getClass().getName());
    }

}
