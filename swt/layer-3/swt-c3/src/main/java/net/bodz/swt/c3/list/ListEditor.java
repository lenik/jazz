package net.bodz.swt.c3.list;

import org.eclipse.swt.widgets.Composite;

import net.bodz.bas.gui.dialog.IUserDialog;
import net.bodz.swt.c3.ia.SwtDialog;

public class ListEditor
        extends AbstractListEditor<String> {

    private IUserDialog UI;

    public ListEditor(Composite parent, int style) {
        super(parent, style);
        UI = new SwtDialog(parent.getShell());
    }

    @Override
    protected String createObject() {
        return UI.prompt("Enter new item: ");
    }

}
