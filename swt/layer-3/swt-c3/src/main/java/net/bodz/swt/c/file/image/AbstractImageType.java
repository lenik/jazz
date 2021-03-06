package net.bodz.swt.c.file.image;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Composite;

import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.swt.c.dialog.ParametersComposite;

public abstract class AbstractImageType
        implements ImageType, II18nCapable {

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
