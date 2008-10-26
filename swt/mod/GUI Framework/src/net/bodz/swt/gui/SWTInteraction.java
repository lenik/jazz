package net.bodz.swt.gui;

import java.util.Map;

import net.bodz.bas.gui._Interaction;
import net.bodz.bas.lang.err.NotImplementedException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class SWTInteraction extends _Interaction {

    private Shell parent;

    public SWTInteraction(Shell parent) {
        this.parent = parent;
    }

    @Override
    public void alert(String title, Object detail) {

        MessageBox box = new MessageBox(parent);
        box.setText(title);
        if (detail != null)
            box.setMessage(String.valueOf(detail));
        box.open();
    }

    @Override
    public boolean confirm(String title, Object detail) {
        MessageBox box = new MessageBox(parent, //
                SWT.ICON_QUESTION | SWT.YES | SWT.NO);
        box.setText(title);
        if (detail != null)
            box.setMessage(String.valueOf(detail));
        return box.open() == SWT.YES;
    }

    @Override
    public <K> K choice(String title, Object detail, Map<K, ?> candidates) {
        throw new NotImplementedException();
    }

    @Override
    public <T> T prompt(String title, Object detail, Class<T> type, T initial) {
        throw new NotImplementedException();
    }

}
