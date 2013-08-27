package net.bodz.swt.c3.file.image;

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import net.bodz.bas.tf.std.ValidationException;
import net.bodz.swt.c3.dialog.ParametersComposite;

public class BMPImageType
        extends AbstractImageType {

    @Override
    public String getName() {
        return tr._("Bitmap");
    }

    @Override
    public String getExtension() {
        return "bmp";
    }

    static class Params {

        boolean compressByRLE;

    }

    static class ParamsComp
            extends ParametersComposite {

        private Params params;

        private Button rleCompressedButton;

        private ParamsComp(Composite parent, int style, Params params) {
            super(parent, style);
            assert params != null;
            this.params = params;

            final GridLayout gridLayout = new GridLayout();
            setLayout(gridLayout);

            rleCompressedButton = new Button(this, SWT.CHECK);
            rleCompressedButton.setText(tr._("&RLE Compressed"));
            rleCompressedButton.setSelection(params.compressByRLE);
        }

        @Override
        public Object get()
                throws ValidationException {
            params.compressByRLE = rleCompressedButton.getSelection();
            return params;
        }

    }

    @Override
    public void save(ImageData imageData, OutputStream out, Object _params)
            throws IOException {
        if (imageData == null)
            throw new NullPointerException("imageData");
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
    public ParametersComposite createParametersComposite(Composite parent, int style) {
        return new ParamsComp(parent, style, new Params());
    }

}
