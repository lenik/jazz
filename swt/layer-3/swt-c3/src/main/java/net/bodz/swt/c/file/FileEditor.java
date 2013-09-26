package net.bodz.swt.c.file;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * @test {@link FileEditorTest}
 */
public class FileEditor
        extends Composite {

    private Text pathText;
    private Button browseButton;

    private FileSelector fileSelector;

    public FileEditor(Composite parent, int style) {
        super(parent, style);
        setLayout(new GridLayout(2, false));

        pathText = new Text(this, SWT.BORDER);
        pathText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));

        fileSelector = new FileSelector();

        browseButton = new Button(this, SWT.NONE);
        browseButton.setText("...");
        browseButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String orig = pathText.getText();
                String path = fileSelector.select(getShell(), orig);
                if (path != null)
                    pathText.setText(path);
            }
        });
    }

    public File getFile() {
        String path = pathText.getText();
        return new File(path);
    }

    public void setFile(File file) {
        String path = file.getPath();
        pathText.setText(path);
    }

    public FileSelector getFileSelector() {
        return fileSelector;
    }

    public void setFileSelector(FileSelector fileSelector) {
        if (fileSelector == null)
            throw new NullPointerException("fileSelector");
        this.fileSelector = fileSelector;
    }

}
