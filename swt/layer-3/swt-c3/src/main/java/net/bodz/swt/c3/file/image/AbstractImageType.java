package net.bodz.swt.c3.file.image;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.bodz.swt.c3.dialog.ParametersComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Composite;

public abstract class AbstractImageType
        implements ImageType {

    @Override
    public ImageData load(InputStream in)
            throws IOException {
        ImageData data = new ImageData(in);
        return data;
    }

    @Override
    public ParametersComposite createParametersComposite(Composite parent, int style) {
        return null;
    }

    static void _save(ImageLoader loader, OutputStream out, int format)
            throws IOException {
        if (out == null)
            throw new NullPointerException("out");
        try {
            loader.save(out, format);
        } catch (SWTException e) {
            if (e.code == SWT.ERROR_IO)
                throw new IOException(e.getMessage(), e);
            throw e;
        }
    }

}
