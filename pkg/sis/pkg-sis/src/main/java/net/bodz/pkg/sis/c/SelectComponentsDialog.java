package net.bodz.pkg.sis.c;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ListBox;
import org.eclipse.swt.widgets.Shell;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.pkg.sis.ISisComponent;
import net.bodz.pkg.sis.util.Indices;
import net.bodz.swt.c.dialog.SimpleDialog;

public class SelectComponentsDialog
        extends SimpleDialog {

    private List<ISisComponent> components;

    private ListBox listbox;

    public SelectComponentsDialog(Shell parent, int style, String title, String text, List<ISisComponent> components) {
        super(parent, style, title);
        setText(text);
        if (components == null)
            throw new NullPointerException("entries");
        this.components = components;
    }

    /**
     * @return <code>null</code> if canceled.
     */
    @SuppressWarnings("unchecked")
    @Override
    public synchronized List<ISisComponent> open() {
        return (List<ISisComponent>) super.open(false);
    }

    @Override
    public List<ISisComponent> evaluate() {
        int[] indices = listbox.getSelectionIndices();
        List<ISisComponent> selection = new ArrayList<ISisComponent>(indices.length);
        for (int index : indices) {
            selection.add(components.get(index));
        }
        return selection;
    }

    @Override
    protected void createBody(Composite parent)
            throws CreateException {
        listbox = new ListBox(parent, SWT.MULTI | SWT.V_SCROLL | SWT.BORDER);
        for (ISisComponent c : components) {
            String s = "" + c.getLabel();
            iString description = c.getDescription();
            if (description != null)
                s += " - " + description;
            listbox.add(s);
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
        selectAll.setText(nls.tr("Select &All"));
        selectAll.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                listbox.selectAll();
            }
        });
        Button selectInverse = new Button(parent, SWT.NONE);
        selectInverse.setText(nls.tr("&Inverse"));
        selectInverse.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int[] selected = listbox.getSelectionIndices();
                Arrays.sort(selected);
                int count = listbox.getItemCount();
                int[] inverse = Indices.inverse(0, count, selected);
                listbox.deselect(selected);
                listbox.select(inverse);
            }
        });
    }

}
