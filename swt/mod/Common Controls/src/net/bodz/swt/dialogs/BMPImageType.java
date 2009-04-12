package net.bodz.swt.dialogs;

import java.io.IOException;
import java.io.OutputStream;

import net.bodz.swt.gui.ValidateException;
import net.bodz.swt.nls.ControlsNLS;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class BMPImageType extends _ImageType {

    @Override
    public String getName() {
        return ControlsNLS.getString("BMPImageType.name"); //$NON-NLS-1$
    }

    @Override
    public String getExtension() {
        return "bmp"; //$NON-NLS-1$
    }

    static class Params {

        boolean compressByRLE;

    }

    static class ParamsComp extends ParametersComposite {

        private Params params;

        private Button rleCompressedButton;

        private ParamsComp(Composite parent, int style, Params params) {
            super(parent, style);
            assert params != null;
            this.params = params;

            final GridLayout gridLayout = new GridLayout();
            setLayout(gridLayout);

            rleCompressedButton = new Button(this, SWT.CHECK);
            rleCompressedButton.setText(ControlsNLS
                    .getString("BMPImageType.rleCompressed")); //$NON-NLS-1$
            rleCompressedButton.setSelection(params.compressByRLE);
        }

        @Override
        public Object get() throws ValidateException {
            params.compressByRLE = rleCompressedButton.getSelection();
            return params;
        }

    }

    @Override
    public void save(ImageData imageData, OutputStream out, Object _params)
            throws IOException {
        if (imageData == null)
            throw new NullPointerException("imageData"); //$NON-NLS-1$
        Params params = (Params) _params;
        ImageLoader loader = new ImageLoader();
        loader.data = new ImageData[] { imageData };
        int format = SWT.IMAGE_BMP;
        if (params != null)
            if (params.compressByRLE)
                format = SWT.IMAGE_BMP_RLE;
        _save(loader, out, format);
    }

    @Override
    public ParametersComposite createParametersComposite(Composite parent,
            int style) {
        return new ParamsComp(parent, style, new Params());
    }

}
