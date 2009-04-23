package net.bodz.swt.dialogs;

import java.io.IOException;
import java.io.OutputStream;

import net.bodz.swt.gui.ValidateException;
import net.bodz.swt.nls.ControlsNLS;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;

public class GIFImageType extends _ImageType {

    @Override
    public String getName() {
        return ControlsNLS.getString("GIFImageType.name"); //$NON-NLS-1$
    }

    @Override
    public String getExtension() {
        return "gif"; //$NON-NLS-1$
    }

    static class Params {
        int transparentPixel = -1;
    }

    static class ParamsComp extends ParametersComposite {

        Params params;

        private ParamsComp(Composite parent, int style, Params params) {
            super(parent, style);
            final GridLayout gridLayout = new GridLayout();
            gridLayout.numColumns = 3;
            setLayout(gridLayout);
            assert params != null;
            this.params = params;

            final Label transparentcyLabel = new Label(this, SWT.NONE);
            transparentcyLabel.setText(ControlsNLS.getString("GIFImageType.transparency")); //$NON-NLS-1$

            final Canvas colorBlock = new Canvas(this, SWT.NONE);
            final GridData gd_colorBlock = new GridData(16, 16);
            colorBlock.setLayoutData(gd_colorBlock);

            final Link selectTransLink = new Link(this, SWT.NONE);
            selectTransLink.setText(ControlsNLS.getString("GIFImageType.selectLink")); //$NON-NLS-1$
        }

        @Override
        public Params get() throws ValidateException {
            return params;
        }

    }

    @Override
    public void save(ImageData imageData, OutputStream out, Object _params) throws IOException {
        if (imageData == null)
            throw new NullPointerException("imageData"); //$NON-NLS-1$
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
