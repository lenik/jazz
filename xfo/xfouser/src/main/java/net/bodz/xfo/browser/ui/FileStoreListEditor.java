package net.bodz.xfo.browser.ui;

import net.bodz.swt.widgets._ListEditor;

import org.eclipse.swt.widgets.Composite;

public class FileStoreListEditor extends _ListEditor<FileStore> {

    public FileStoreListEditor(Composite parent, int style) {
        super(parent, style);
    }

    @Override
    protected FileStore createObject() {
        return null;
    }

}
