package net.bodz.swt.dialogs;

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;

public class PNGImageType extends _ImageType {

    @Override
    public String getName() {
        return "Portable Network Image";
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
        // Params params = (Params) _params;
        ImageLoader loader = new ImageLoader();
        loader.data = new ImageData[] { imageData };
        _save(loader, out, SWT.IMAGE_PNG);
    }

}
