package net.bodz.art.installer.builtins;

import static net.bodz.art.installer.nls.PackNLS.PackNLS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import net.bodz.art.installer.IComponent;
import net.bodz.art.installer.util.Indices;
import net.bodz.art.installer.util.MissingDependancyBuffer.Entry;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.err.CreateException;
import net.bodz.swt.dialogs.SimpleDialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

public class SelectComponentsDialog
        extends SimpleDialog {

    private String[] indents;
    private IComponent[] components;

    private List list;

    public SelectComponentsDialog(Shell parent, int style, String title, String text, Collection<Entry> entries) {
        super(parent, style, title);
        setText(text);
        if (entries == null)
            throw new NullPointerException("entries");
        int size = entries.size();
        indents = new String[size];
        components = new IComponent[size];
        Iterator<Entry> iter = entries.iterator();
        for (int i = 0; i < size; i++) {
            Entry entry = iter.next();
            indents[i] = Strings.repeat(entry.level * 4, ' ');
            components[i] = entry.component;
        }
    }

    public SelectComponentsDialog(Shell parent, int style, String title, String text, IComponent... components) {
        super(parent, style, title);
        setText(text);
        if (components == null)
            throw new NullPointerException("components");
        this.components = components;
    }

    /**
     * @return <code>null</code> if canceled.
     */
    @Override
    public synchronized Collection<IComponent> open() {
        return (Collection<IComponent>) super.open(false);
    }

    @Override
    public ArrayList<IComponent> evaluate() {
        int[] indices = list.getSelectionIndices();
        ArrayList<IComponent> selected = new ArrayList<IComponent>(indices.length);
        for (int index : indices) {
            selected.add(components[index]);
        }
        return selected;
    }

    @Override
    protected void createBody(Composite parent)
            throws CreateException {
        list = new List(parent, SWT.MULTI | SWT.V_SCROLL | SWT.BORDER);
        for (int i = 0; i < components.length; i++) {
            String indent = null;
            if (indents != null)
                indent = indents[i];
            IComponent c = components[i];
            String s = indent == null ? "" : indent;
            s += c.getText();
            String doc = c.getDoc();
            if (doc != null)
                s += " - " + doc;
            list.add(s);
        }
    }

    @Override
    protected void createButtons(Composite parent)
            throws CreateException {
        addOKButton(parent);
        addCancelButton(parent);
    }

    @Override
    protected void createUserButtons(Composite parent)
            throws CreateException {
        Button selectAll = new Button(parent, SWT.NONE);
        selectAll.setText(PackNLS.getString("SelectComponentsDialog.selectAll"));
        selectAll.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                list.selectAll();
            }
        });
        Button selectInverse = new Button(parent, SWT.NONE);
        selectInverse.setText(PackNLS.getString("SelectComponentsDialog.selectInverse"));
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
