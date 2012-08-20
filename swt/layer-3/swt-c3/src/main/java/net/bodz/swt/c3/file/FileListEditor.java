package net.bodz.swt.c3.file;

import java.io.File;

import org.eclipse.swt.widgets.Composite;

import net.bodz.swt.c3.list.AbstractListEditor;

public class FileListEditor
        extends AbstractListEditor<File> {

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
