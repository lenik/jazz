package net.bodz.swt.viz.form.vbo;

import java.util.EventObject;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.potato.ref.IValueChangeListener;
import net.bodz.bas.potato.ref.IValueChangeSource;
import net.bodz.bas.potato.ref.ValueChangeEvent;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.ValidationException;
import net.bodz.swt.c.control.CommitAdapter;
import net.bodz.swt.c.control.CommitException;
import net.bodz.swt.c.control.OnFocusCommit;
import net.bodz.swt.viz.AbstractSwtViewBuilder;
import net.bodz.swt.viz.ISwtControlStyleDeclaration;
import net.bodz.swt.viz.ISwtGUIRefEntry;
import net.bodz.swt.viz.SwtRenderContext;
import net.bodz.swt.viz.util.SwtControlStyler;
import net.bodz.swt.viz.util.SwtStyleInts;

public class StringVbo
        extends AbstractSwtViewBuilder<String> {

    @Override
    public Widget buildView(Composite parent, final ISwtGUIRefEntry<String> entry, int styleInt, IOptions options)
            throws ViewBuilderException {

        final ISwtControlStyleDeclaration styleDecl = entry.getStyle();
        styleInt |= SwtStyleInts.merge(styleInt, styleDecl);

        // capitalize, uppercase, lowercase:
        // TextTransformMode textTransform = styleDecl.getTextTransform();
        boolean readOnly = styleDecl.getReadOnly() == Boolean.TRUE;

        Control control = readOnly ? buildLabel(parent, entry, styleInt, options) //
                : buildText(parent, entry, styleInt, options);

        SwtControlStyler.apply(control, styleDecl);

        return control;
    }

    Label buildLabel(Composite parent, final ISwtGUIRefEntry<String> entry, int styleInt, IOptions options)
            throws ViewBuilderException {

        final Label label = new Label(parent, styleInt);

        String value = entry.get();
        label.setText(value);

        if (entry.query(IValueChangeSource.class) != null)
            bindProperty(entry, label, new IValueChangeListener() {
                @Override
                public boolean valueChange(ValueChangeEvent evt) {
                    label.setText(String.valueOf(entry.get()));
                    return true;
                }
            });

        return label;
    }

    Text buildText(Composite parent, final ISwtGUIRefEntry<String> entry, int styleInt, IOptions options)
            throws ViewBuilderException {

        ISwtControlStyleDeclaration styleDecl = entry.getStyle();
        final Text text = new Text(parent, styleInt);

        Character echoChar = styleDecl.getEchoChar();
        if (echoChar != null)
            text.setEchoChar(echoChar);

        Integer maxLength = styleDecl.getMaxLength();
        if (maxLength != null)
            text.setTextLimit(maxLength);

        String value = entry.get();
        text.setText(value);

        IValueChangeSource valueChangeSource = entry.query(IValueChangeSource.class);
        if (valueChangeSource != null)
            bindProperty(entry, text, new IValueChangeListener() {
                @Override
                public boolean valueChange(ValueChangeEvent evt) {
                    text.setText(entry.get());
                    return true;
                }
            });

        SwtRenderContext rc = options.get(SwtRenderContext.class);
        OnFocusCommit.apply(text, new CommitAdapter(rc.getUserDialogs(text)) {
            @Override
            public void commit(EventObject event)
                    throws CommitException {
                String str = text.getText();
                try {
                    IRefEntry.fn.validateAndSet(entry, str);
                } catch (ValidationException e) {
                    throw new CommitException(e.getMessage(), e);
                }
            }
        });

        return text;
    }

}
