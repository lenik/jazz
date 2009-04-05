package net.bodz.swt.dialogs;

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;

public class TIFFImageType extends _ImageType {

    @Override
    public String getName() {
        return "TIFF";
    }

    @Override
    public String getExtension() {
        return "tif";
    }

    @Override
    public void save(ImageData imageData, OutputStream out, Object params)
            throws IOException {
        if (imageData == null)
            throw new NullPointerException("imageData");
        ImageLoader loader = new ImageLoader();
        loader.data = new ImageData[] { imageData };
        _save(loader, out, SWT.IMAGE_TIFF);
    }

}
