package net.bodz.swt.c3.list;

import org.eclipse.swt.widgets.Composite;

import net.bodz.bas.gui.ia.IUserInteraction;
import net.bodz.swt.c3.ia.DialogInteraction;

public class ListEditor
        extends AbstractListEditor<String> {

    private IUserInteraction UI;

    public ListEditor(Composite parent, int style) {
        super(parent, style);
        UI = new DialogInteraction(parent.getShell());
    }

    @Override
    protected String createObject() {
        return UI.prompt("Enter new item: ");
    }

}
