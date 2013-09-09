package net.bodz.swt.c.file.image;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;

import net.bodz.swt.c.dialog.ParametersComposite;

public interface ImageType {

    /** image type name */
    String getName();

    /** preferred extension */
    String getExtension();

    ImageData load(InputStream in)
            throws IOException;

    void save(ImageData imageData, OutputStream out, Object params)
            throws IOException;

    ParametersComposite createParametersComposite(Composite parent, int style);

}
