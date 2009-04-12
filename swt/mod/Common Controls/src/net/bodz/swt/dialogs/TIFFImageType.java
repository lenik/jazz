package net.bodz.swt.dialogs;

import java.io.IOException;
import java.io.OutputStream;

import net.bodz.swt.nls.ControlsNLS;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;

public class TIFFImageType extends _ImageType {

    @Override
    public String getName() {
        return ControlsNLS.getString("TIFFImageType.name"); //$NON-NLS-1$
    }

    @Override
    public String getExtension() {
        return "tif"; //$NON-NLS-1$
    }

    @Override
    public void save(ImageData imageData, OutputStream out, Object params)
            throws IOException {
        if (imageData == null)
            throw new NullPointerException("imageData"); //$NON-NLS-1$
        ImageLoader loader = new ImageLoader();
        loader.data = new ImageData[] { imageData };
        _save(loader, out, SWT.IMAGE_TIFF);
    }

}
