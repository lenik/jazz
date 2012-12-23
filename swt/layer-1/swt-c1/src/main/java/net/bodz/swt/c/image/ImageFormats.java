package net.bodz.swt.c.image;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;

public class ImageFormats {

    static Map<String, Integer> extensionMap;
    static Map<String, Integer> contentTypeMap;

    static {
        extensionMap = new HashMap<String, Integer>();
        extensionMap.put("bmp", SWT.IMAGE_BMP_RLE);
        extensionMap.put("bmp.rle", SWT.IMAGE_BMP_RLE);
        extensionMap.put("bmp.os2", SWT.IMAGE_OS2_BMP);
        extensionMap.put("ico", SWT.IMAGE_ICO);
        extensionMap.put("jpg", SWT.IMAGE_JPEG);
        extensionMap.put("jpeg", SWT.IMAGE_JPEG);
        extensionMap.put("gif", SWT.IMAGE_GIF);
        extensionMap.put("png", SWT.IMAGE_PNG);
        extensionMap.put("tif", SWT.IMAGE_TIFF);
        extensionMap.put("tiff", SWT.IMAGE_TIFF);
    }

    public static int getImageFormatForExtension(String extension) {
        Integer format = extensionMap.get(extension);
        if (format == null)
            return SWT.IMAGE_UNDEFINED;
        else
            return format;
    }

}
