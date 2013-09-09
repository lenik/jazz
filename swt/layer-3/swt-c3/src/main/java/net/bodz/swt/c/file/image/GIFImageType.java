package net.bodz.swt.c.file.image;

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;

import net.bodz.bas.typer.std.ValidationException;
import net.bodz.swt.c.dialog.ParametersComposite;

public class GIFImageType
        extends AbstractImageType {

    @Override
    public String getName() {
        return tr._("GIF");
    }

    @Override
    public String getExtension() {
        return "gif";
    }

    static class Params {
        int transparentPixel = -1;
    }

    static class ParamsComp
            extends ParametersComposite {

        Params params;

        private ParamsComp(Composite parent, int style, Params params) {
            super(parent, style);
            final GridLayout gridLayout = new GridLayout();
            gridLayout.numColumns = 3;
            setLayout(gridLayout);
            assert params != null;
            this.params = params;

            final Label transparentcyLabel = new Label(this, SWT.NONE);
            transparentcyLabel.setText(tr._("&Transparency: "));

            final Canvas colorBlock = new Canvas(this, SWT.NONE);
            final GridData gd_colorBlock = new GridData(16, 16);
            colorBlock.setLayoutData(gd_colorBlock);

            final Link selectTransLink = new Link(this, SWT.NONE);
            selectTransLink.setText(tr._("<a>select</a>"));
        }

        @Override
        public Params get()
                throws ValidationException {
            return params;
        }

    }

    @Override
    public void save(ImageData imageData, OutputStream out, Object _params)
            throws IOException {
        if (imageData == null)
            throw new NullPointerException("imageData");
        Params params = (Params) _params;
        if (params.transparentPixel != -1)
            imageData.transparentPixel = params.transparentPixel;
        ImageLoader loader = new ImageLoader();
        loader.data = new ImageData[] { imageData };
        _save(loader, out, SWT.IMAGE_GIF);
    }

    @Override
    public ParametersComposite createParametersComposite(Composite parent, int style) {
        return new ParamsComp(parent, style, new Params());
    }

}
