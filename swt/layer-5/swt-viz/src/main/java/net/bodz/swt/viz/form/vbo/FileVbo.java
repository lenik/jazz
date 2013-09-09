package net.bodz.swt.viz.form.vbo;

import java.io.File;
import java.util.EventObject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import net.bodz.bas.potato.ref.IValueChangeListener;
import net.bodz.bas.potato.ref.IValueChangeSource;
import net.bodz.bas.potato.ref.ValueChangeEvent;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IValidator;
import net.bodz.bas.typer.std.ValidationException;
import net.bodz.swt.c.control.CommitAdapter;
import net.bodz.swt.c.control.CommitException;
import net.bodz.swt.c.control.OnFocusCommit;
import net.bodz.swt.c.layout.BorderLayout;
import net.bodz.swt.viz.AbstractSwtViewBuilder;
import net.bodz.swt.viz.ISwtControlStyleDeclaration;
import net.bodz.swt.viz.ISwtGUIRefEntry;
import net.bodz.swt.viz.SwtRenderContext;
import net.bodz.swt.viz.util.SwtControlStyler;

public class FileVbo
        extends AbstractSwtViewBuilder<File> {

    @Override
    public Widget buildView(final Composite parent, final ISwtGUIRefEntry<File> entry, int styleInt, IOptions options)
            throws ViewBuilderException {
        final ISwtControlStyleDeclaration styleDecl = entry.getStyle();
        final SwtRenderContext rc = options.get(SwtRenderContext.class);

        File val = (File) entry.get();
        assert val != null;
        final Composite comp = new Composite(parent, styleInt);
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

        if (styleDecl.getReadOnly() == Boolean.TRUE) {
            fileText.setEnabled(false);
            browseButton.setEnabled(false);
        } else {
            Class<?> valueType = entry.getValueType();
            final IValidator<Object> validator;
            try {
                validator = Typers.getTyper(valueType, IValidator.class);
            } catch (QueryException e) {
                throw new ViewBuilderException(tr._("Can\'t guess parser for number class: ") + valueType);
            }

            OnFocusCommit.apply(fileText, new CommitAdapter(//
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

        if (entry.query(IValueChangeSource.class) != null)
            bindProperty(entry, fileText, new IValueChangeListener() {
                @Override
                public boolean valueChange(ValueChangeEvent evt) {
                    File file = (File) entry.get();
                    assert file != null;
                    fileText.setText(String.valueOf(file));
                    return true;
                }
            });
        SwtControlStyler.apply(comp, styleDecl);
        return comp;
    }

}
