package net.bodz.swt.reflect.styles.base;

import static net.bodz.swt.nls.GUINLS.GUINLS;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.EventObject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;

import net.bodz.bas.err.CheckException;
import net.bodz.bas.ui.RenderException;
import net.bodz.swt.c.control.CommitAdapter;
import net.bodz.swt.c.control.CommitException;
import net.bodz.swt.c.control.ControlAdapters;
import net.bodz.swt.c.layout.BorderLayout;
import net.bodz.swt.reflect.GUIVar;
import net.bodz.swt.reflect.GUIVarMeta;
import net.bodz.swt.reflect.SWTRenderContext;
import net.bodz.swt.reflect.SWTRenderer;

public class R_File
        extends SWTRenderer {

    @Override
    public Control render(final SWTRenderContext rc, final GUIVar<?> var, final Composite parent, final int style)
            throws RenderException, SWTException {
        GUIVarMeta meta = var.getMeta();
        File val = (File) var.get();
        assert val != null;
        final Composite comp = new Composite(parent, style);
        BorderLayout layout = new BorderLayout();
        comp.setLayout(layout);
        final Text fileText = new Text(comp, SWT.BORDER);
        fileText.setText(val.getPath());
        fileText.setLayoutData(BorderLayout.CENTER);
        final Button browseButton = new Button(comp, SWT.NONE);
        browseButton.setText(GUINLS.getString("R_File.browse"));
        browseButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                FileDialog dialog = new FileDialog(parent.getShell());
                String path = dialog.open();
                if (path != null)
                    fileText.setText(path);
            }
        });
        browseButton.setLayoutData(BorderLayout.EAST);
        if (meta.isReadOnly()) {
            fileText.setEnabled(false);
            browseButton.setEnabled(false);
        } else {
            ControlAdapters.commit(fileText, new CommitAdapter(//
                    rc.interact(fileText)) {
                @Override
                public void commit(EventObject event)
                        throws CommitException {
                    File file = new File(fileText.getText());
                    try {
                        var.validate(file);
                        var.set(file);
                    } catch (CheckException e) {
                        throw new CommitException(e);
                    }
                }
            });
        }
        if (meta.hasPropertyChangeSupport())
            bindProperty(var, fileText, new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    File file = (File) var.get();
                    assert file != null;
                    fileText.setText(String.valueOf(file));
                }
            });
        rc.addEffects(comp, var);
        return comp;
    }
}
