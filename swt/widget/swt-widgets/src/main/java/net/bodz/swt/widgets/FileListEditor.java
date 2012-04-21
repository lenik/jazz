package net.bodz.swt.widgets;

import java.io.File;

import net.bodz.swt.widgets.util.FileSelector;

import org.eclipse.swt.widgets.Composite;

public class FileListEditor
        extends _ListEditor<File> {

    FileSelector fileSelector;

    public FileListEditor(Composite parent, int style) {
        super(parent, style);
        fileSelector = new FileSelector();
    }

    @Override
    protected File createObject() {
        String path = fileSelector.select(getShell(), null);
        if (path == null)
            return null;
        return new File(path);
    }

    public FileSelector getFileSelector() {
        return fileSelector;
    }

    public void setFileSelector(FileSelector fileSelector) {
        this.fileSelector = fileSelector;
    }

}
