package net.bodz.dist.ins.builtins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import net.bodz.bas.lang.err.CancelException;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.types.util.Strings;
import net.bodz.dist.ins.Component;
import net.bodz.dist.ins.builtins.SelectComponentsDialogTest;
import net.bodz.dist.ins.nls.PackNLS;
import net.bodz.dist.ins.util.Indices;
import net.bodz.dist.ins.util.MissingDependancyBuffer.Entry;
import net.bodz.swt.dialogs.SimpleDialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

/**
 * @test {@link SelectComponentsDialogTest}
 */
public class SelectComponentsDialog extends SimpleDialog {

    private String[]    indents;
    private Component[] components;

    private List        list;

    public SelectComponentsDialog(Shell parent, int style, String title, String text,
            Collection<Entry> entries) {
        super(parent, style, title);
        setText(text);
        if (entries == null)
            throw new NullPointerException("entries"); //$NON-NLS-1$
        int size = entries.size();
        indents = new String[size];
        components = new Component[size];
        Iterator<Entry> iter = entries.iterator();
        for (int i = 0; i < size; i++) {
            Entry entry = iter.next();
            indents[i] = Strings.repeat(entry.level * 4, ' ');
            components[i] = entry.component;
        }
    }

    public SelectComponentsDialog(Shell parent, int style, String title, String text,
            Component... components) {
        super(parent, style, title);
        setText(text);
        if (components == null)
            throw new NullPointerException("components"); //$NON-NLS-1$
        this.components = components;
    }

    @SuppressWarnings("unchecked")
    @Override
    public synchronized Collection<Component> open() throws CancelException {
        try {
            Object result = super.open();
            return (Collection<Component>) result;
        } catch (CancelException e) {
            return null;
        }
    }

    @Override
    public void execute() {
        int[] indices = list.getSelectionIndices();
        ArrayList<Component> selected = new ArrayList<Component>(indices.length);
        for (int index : indices) {
            selected.add(components[index]);
        }
        set(selected);
    }

    @Override
    protected void createBody(Composite parent) throws CreateException {
        list = new List(parent, SWT.MULTI | SWT.V_SCROLL | SWT.BORDER);
        for (int i = 0; i < components.length; i++) {
            String indent = null;
            if (indents != null)
                indent = indents[i];
            Component c = components[i];
            String s = indent == null ? "" : indent; //$NON-NLS-1$
            s += c.getText();
            String doc = c.getDoc();
            if (doc != null)
                s += " - " + doc; //$NON-NLS-1$
            list.add(s);
        }
    }

    @Override
    protected void createButtons(Composite parent) throws CreateException {
        addOKButton(parent);
        addCancelButton(parent);
    }

    @Override
    protected void createUserButtons(Composite parent) throws CreateException {
        Button selectAll = new Button(parent, SWT.NONE);
        selectAll.setText(PackNLS.getString("SelectComponentsDialog.selectAll")); //$NON-NLS-1$
        selectAll.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                list.selectAll();
            }
        });
        Button selectInverse = new Button(parent, SWT.NONE);
        selectInverse.setText(PackNLS.getString("SelectComponentsDialog.selectInverse")); //$NON-NLS-1$
        selectInverse.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int[] selected = list.getSelectionIndices();
                Arrays.sort(selected);
                int count = list.getItemCount();
                int[] inverse = Indices.inverse(0, count, selected);
                list.deselect(selected);
                list.select(inverse);
            }
        });
    }
}
