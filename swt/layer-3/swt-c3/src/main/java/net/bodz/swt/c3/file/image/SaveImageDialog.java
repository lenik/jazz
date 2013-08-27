package net.bodz.swt.c3.file.image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.typer.std.ValidationException;
import net.bodz.swt.c.composite.FixSizeComposite;
import net.bodz.swt.c.composite.StackComposite;
import net.bodz.swt.c3.dialog.ParametersComposite;
import net.bodz.swt.c3.dialog.SimpleDialog;

public class SaveImageDialog
        extends SimpleDialog {

    private ImageData imageData;

    private Combo imageTypeCombo;
    private StackComposite paramsStack;
    private Text pathText;

    private ParametersComposite voidParamsComp;
    private ParametersComposite[] paramsComps;

    public SaveImageDialog(Shell parent, int style, ImageData image, String title) {
        super(parent, style, title);
        this.imageData = image;
    }

    public SaveImageDialog(Shell parent, int style, ImageData image) {
        super(parent, style, tr._("Save image as..."));
        this.imageData = image;
    }

    @Override
    public synchronized File open() {
        return (File) super.open(false);
    }

    @Override
    protected Object evaluate()
            throws ValidationException, IOException {
        String path = pathText.getText();
        File file = new File(path);
        // if (file.exists()) ;

        int typeIndex = imageTypeCombo.getSelectionIndex();
        assert typeIndex != -1;
        ParametersComposite paramsComp = paramsComps[typeIndex];
        Object params = null;
        if (paramsComp != null)
            params = paramsComp.get();

        ImageType type = imageTypes[typeIndex];
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            type.save(imageData, out, params);
            return file;
        } finally {
            if (out != null)
                try {
                    out.close();
                } catch (IOException e) {
                    throw e;
                }
        }
    }

    @Override
    protected void createBody(Composite parent)
            throws SWTException, CreateException {
        Composite composite = new Composite(parent, SWT.NONE);
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        composite.setLayout(gridLayout);

        final Label typeLabel = new Label(composite, SWT.NONE);
        typeLabel.setText(tr._("Image &Type:"));

        imageTypeCombo = new Combo(composite, SWT.READ_ONLY);
        final GridData gd_imageTypeCombo = new GridData();
        imageTypeCombo.setLayoutData(gd_imageTypeCombo);

        new Label(composite, SWT.NONE);
        new Label(composite, SWT.NONE);

        paramsStack = new StackComposite(composite, SWT.NONE);
        final GridData gd_optionsComposite = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
        paramsStack.setLayoutData(gd_optionsComposite);

        final Label pathLabel = new Label(composite, SWT.NONE);
        pathLabel.setText(tr._("File path: "));

        pathText = new Text(composite, SWT.BORDER);
        final GridData gd_pathText = new GridData(SWT.FILL, SWT.CENTER, true, false);
        pathText.setLayoutData(gd_pathText);

        final Button browseButton = new Button(composite, SWT.NONE);
        browseButton.setText(tr._("&Browse"));
        browseButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int typeIndex = imageTypeCombo.getSelectionIndex();
                assert typeIndex != -1;
                ImageType type = imageTypes[typeIndex];
                String ext = type.getExtension();
                String[] names = { type.getName() + " (*." + ext + ")" };
                String[] extensions = { "*." + ext };

                FileDialog saveDialog = new FileDialog(getParent(), SWT.SAVE);
                saveDialog.setFilterNames(names);
                saveDialog.setFilterExtensions(extensions);
                String path = saveDialog.open();
                if (path == null)
                    return;
                pathText.setText(path);
            }
        });

        setupImageTypes();
    }

    static ImageType[] imageTypes;
    static {
        imageTypes = new ImageType[] {
                //
                new JPEGImageType(), //
                new PNGImageType(), //
                new GIFImageType(), //
                new BMPImageType(), //
                new TIFFImageType(), //
        };
    }

    static class VoidParamsComp
            extends ParametersComposite {

        private VoidParamsComp(Composite parent, int style) {
            super(parent, style);
            new FixSizeComposite(parent, style, 1, 1);
        }

        @Override
        public Object get()
                throws ValidationException {
            return null;
        }

    }

    void setupImageTypes() {
        voidParamsComp = new VoidParamsComp(paramsStack, SWT.NONE);
        paramsComps = new ParametersComposite[imageTypes.length];
        for (int i = 0; i < imageTypes.length; i++) {
            ImageType type = imageTypes[i];
            imageTypeCombo.add(type.getName());
            ParametersComposite paramsComp = type.createParametersComposite(paramsStack, SWT.NONE);
            paramsComps[i] = paramsComp;
        }

        imageTypeCombo.select(0);
        showParams(0);

        imageTypeCombo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int typeIndex = imageTypeCombo.getSelectionIndex();
                showParams(typeIndex);
            }
        });
    }

    void showParams(int typeIndex) {
        if (typeIndex >= 0 && typeIndex < paramsComps.length) {
            ParametersComposite paramsComp = paramsComps[typeIndex];
            paramsStack.bringFront(paramsComp);
        } else {
            paramsStack.bringFront(voidParamsComp);
        }
        getBody().layout();
    }

}
