package net.bodz.swt.viz.form.vbo;

import java.util.EventObject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import net.bodz.bas.gui.css3.BorderBox;
import net.bodz.bas.i18n.unit.std.LengthMeasure;
import net.bodz.bas.potato.ref.IValueChangeListener;
import net.bodz.bas.potato.ref.ValueChangeEvent;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.IValidator;
import net.bodz.swt.c3.control.CommitAdapter;
import net.bodz.swt.c3.control.CommitException;
import net.bodz.swt.c3.control.ControlAdapters;
import net.bodz.swt.viz.AbstractSwtViewBuilder;
import net.bodz.swt.viz.ISwtControlStyleDeclaration;
import net.bodz.swt.viz.ISwtGUIRefEntry;
import net.bodz.swt.viz.SwtRenderContext;

public class StringVbo
        extends AbstractSwtViewBuilder<String> {

    @Override
    public Widget buildView(Composite parent, final ISwtGUIRefEntry<String> entry, int styleInt, IOptions options)
            throws ViewBuilderException {
        final ISwtControlStyleDeclaration styleDecl = entry.getStyle();
        final SwtRenderContext rc = options.get(SwtRenderContext.class);

        boolean readOnly = styleDecl.getReadOnly() == Boolean.TRUE;

        String val = String.valueOf(entry.get());
        if (readOnly) {
            final Label label = new Label(parent, styleInt);
            label.setText(val);
            if (entry.isValueChangeSource())
                bindProperty(entry, label, new IValueChangeListener() {
                    @Override
                    public boolean valueChange(ValueChangeEvent evt) {
                        label.setText(String.valueOf(entry.get()));
                        return true;
                    }
                });
            return label;
        } else {
            BorderBox borderBox = styleDecl.getBorder();
            if (borderBox != null) {
                LengthMeasure width = borderBox.getWidth();
                if (width != null) {
                    double border = width.getValue();
                    if (border > 0)
                        styleInt |= SWT.BORDER;
                }
            }
            final Text text = new Text(parent, styleInt);
            // Ns.getValue(meta, EchoChar.class);
            // text.setEchoChar(echo);
            Integer maxLength = styleDecl.getMaxLength();
            if (maxLength != null)
                text.setTextLimit(maxLength);

            text.setText(val);
            if (entry.isValueChangeSource())
                bindProperty(entry, text, new IValueChangeListener() {
                    @Override
                    public boolean valueChange(ValueChangeEvent evt) {
                        text.setText(String.valueOf(entry.get()));
                        return true;
                    }
                });

            Class<?> type = entry.getValueType();
            final IParser<?> parser;
            final IValidator<Object> validator;
            try {
                parser = Typers.getTyper(type, IParser.class);
                validator = Typers.getTyper(type, IValidator.class);
            } catch (QueryException e) {
                throw new ViewBuilderException(tr._("Can\'t guess parser for number class: ") + type);
            }

            ControlAdapters.autocommitForFocus(text, new CommitAdapter(rc.getUserDialogs(text)) {
                @Override
                public void commit(EventObject event)
                        throws CommitException {
                    Object value;
                    try {
                        value = parser.parse(text.getText());
                        validator.validate(value);
                    } catch (Exception e) {
                        throw new CommitException(e);
                    }
                    entry.set(value);
                }
            });
            return text;
        }
    }

}
