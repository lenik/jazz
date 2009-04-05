package net.bodz.swt.dialogs;

import java.io.IOException;
import java.io.OutputStream;

import net.bodz.swt.gui.ValidateException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;

public class JPEGImageType extends _ImageType {

    @Override
    public String getName() {
        return "JPEG";
    }

    @Override
    public String getExtension() {
        return "jpg";
    }

    static class Params {
        int quantity = 75;
    }

    static class ParamsComp extends ParametersComposite {

        private Params params;

        private Scale  qualityScale;

        private ParamsComp(Composite parent, int style, Params params) {
            super(parent, style);
            assert params != null;
            this.params = params;

            final GridLayout gridLayout = new GridLayout();
            gridLayout.numColumns = 2;
            setLayout(gridLayout);

            final Label jpegQualityLabel = new Label(this, SWT.NONE);
            jpegQualityLabel.setText("JPEG &Quality:");

            qualityScale = new Scale(this, SWT.NONE);
            qualityScale.setMinimum(10);
            final GridData gd_qualityScale = new GridData(SWT.FILL, SWT.CENTER,
                    true, false);
            qualityScale.setLayoutData(gd_qualityScale);

            qualityScale.setSelection(params.quantity);
        }

        @Override
        public Object get() throws ValidateException {
            params.quantity = qualityScale.getSelection();
            return params;
        }

    }

    @Override
    public void save(ImageData imageData, OutputStream out, Object _params)
            throws IOException {
        if (imageData == null)
            throw new NullPointerException("imageData");
        // Params params = (Params) _params;
        ImageLoader loader = new ImageLoader();
        loader.data = new ImageData[] { imageData };
        _save(loader, out, SWT.IMAGE_JPEG);
    }

    @Override
    public ParametersComposite createParametersComposite(Composite parent,
            int style) {
        return new ParamsComp(parent, style, new Params());
    }

}
