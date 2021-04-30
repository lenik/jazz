package net.bodz.swt.c.file.image;

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;

public class PNGImageType
        extends AbstractImageType {

    @Override
    public String getName() {
        return nls.tr("Portable Network Image");
    }

    @Override
    public String getExtension() {
        return "png";
    }

    static class Params {
    }

    @Override
    public void save(ImageData imageData, OutputStream out, Object _params)
            throws IOException {
        if (imageData == null)
            throw new NullPointerException("imageData");
        // Params params = (Params) _params;
        ImageLoader loader = new ImageLoader();
        loader.data = new ImageData[] { imageData };
        _save(loader, out, SWT.IMAGE_PNG);
    }

}
