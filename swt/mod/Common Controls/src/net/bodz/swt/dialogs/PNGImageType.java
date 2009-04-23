package net.bodz.swt.dialogs;

import java.io.IOException;
import java.io.OutputStream;

import net.bodz.swt.nls.ControlsNLS;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;

public class PNGImageType extends _ImageType {

    @Override
    public String getName() {
        return ControlsNLS.getString("PNGImageType.name"); //$NON-NLS-1$
    }

    @Override
    public String getExtension() {
        return "png"; //$NON-NLS-1$
    }

    static class Params {
    }

    @Override
    public void save(ImageData imageData, OutputStream out, Object _params) throws IOException {
        if (imageData == null)
            throw new NullPointerException("imageData"); //$NON-NLS-1$
        // Params params = (Params) _params;
        ImageLoader loader = new ImageLoader();
        loader.data = new ImageData[] { imageData };
        _save(loader, out, SWT.IMAGE_PNG);
    }

}
