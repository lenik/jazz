package net.bodz.swt.viz.builtin;

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

import net.bodz.bas.gui.viz.RenderException;
import net.bodz.bas.lang.mi.QueryException;
import net.bodz.bas.potato.ref.IRefDescriptor;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.potato.ref.IValueChangeListener;
import net.bodz.bas.potato.ref.ValueChangeEvent;
import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.IValidator;
import net.bodz.bas.traits.ValidationException;
import net.bodz.swt.c.layout.BorderLayout;
import net.bodz.swt.c3.control.CommitAdapter;
import net.bodz.swt.c3.control.CommitException;
import net.bodz.swt.c3.control.ControlAdapters;
import net.bodz.swt.viz.SWTRenderContext;
import net.bodz.swt.viz.SWTRenderer;
import net.bodz.swt.viz.SwtVizStyleClass;

public class R_File
        extends SWTRenderer {

    @Override
    public Control render(final SWTRenderContext rc, final IRefEntry<?> entry, SwtVizStyleClass stylesheet,
            final Composite parent, final int style)
            throws RenderException, SWTException {

        IRefDescriptor descriptor = entry.getDescriptor();

        File val = (File) entry.get();
        assert val != null;
        final Composite comp = new Composite(parent, style);
        BorderLayout layout = new BorderLayout();
        comp.setLayout(layout);
        final Text fileText = new Text(comp, SWT.BORDER);
        fileText.setText(val.getPath());
        fileText.setLayoutData(BorderLayout.CENTER);

        final Button browseButton = new Button(comp, SWT.NONE);
        browseButton.setText(tr._("Browse"));
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

        if (!descriptor.isWritable()) {
            fileText.setEnabled(false);
            browseButton.setEnabled(false);
        } else {
            Class<?> valueType = entry.getValueType();
            final IValidator<Object> validator;
            try {
                validator = Traits.getTrait(valueType, IValidator.class);
            } catch (QueryException e) {
                throw new RenderException(tr._("Can\'t guess parser for number class: ") + valueType);
            }

            ControlAdapters.autocommitForFocus(fileText, new CommitAdapter(//
                    rc.getUserDialogs(fileText)) {
                @Override
                public void commit(EventObject event)
                        throws CommitException {
                    File file = new File(fileText.getText());
                    try {
                        validator.validate(file);
                    } catch (ValidationException e) {
                        throw new CommitException(e);
                    }
                    entry.set(file);
                }
            });
        }

        if (descriptor.isValueChangeSource())
            bindProperty(entry, fileText, new IValueChangeListener() {
                @Override
                public boolean valueChange(ValueChangeEvent evt) {
                    File file = (File) entry.get();
                    assert file != null;
                    fileText.setText(String.valueOf(file));
                    return true;
                }
            });
        rc.addEffects(comp, stylesheet);
        return comp;
    }
}
