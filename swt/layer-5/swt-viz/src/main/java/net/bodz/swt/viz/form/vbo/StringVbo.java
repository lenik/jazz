package net.bodz.swt.viz.form.vbo;

import java.util.EventObject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import net.bodz.bas.gui.css3.BorderBox;
import net.bodz.bas.i18n.unit.std.LengthMeasure;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.potato.ref.IValueChangeListener;
import net.bodz.bas.potato.ref.ValueChangeEvent;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.traits.IValidator;
import net.bodz.swt.c3.control.CommitAdapter;
import net.bodz.swt.c3.control.CommitException;
import net.bodz.swt.c3.control.ControlAdapters;
import net.bodz.swt.viz.ISwtControlStyleDeclaration;
import net.bodz.swt.viz.SwtRenderContext;
import net.bodz.swt.viz.SwtViewBuilder;

public class StringVbo
        extends SwtViewBuilder<Object> {

    public Control _buildView(final SwtRenderContext rc, final IRefEntry<?> entry, ISwtControlStyleDeclaration style,
            Composite parent, int styleInt)
            throws ViewBuilderException, SWTException {

        @SuppressWarnings("unchecked")
        IRefEntry<Object> _entry = (IRefEntry<Object>) (Object) entry;

        return buildView(rc, _entry, style, parent, styleInt);
    }

    @Override
    public Control buildView(final SwtRenderContext rc, final IRefEntry<Object> entry, ISwtControlStyleDeclaration style,
            Composite parent, int styleInt)
            throws ViewBuilderException, SWTException {

        boolean readOnly = style.getReadOnly() == Boolean.TRUE;

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
            BorderBox borderBox = style.getBorder();
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
            Integer maxLength = style.getMaxLength();
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
                parser = Traits.getTrait(type, IParser.class);
                validator = Traits.getTrait(type, IValidator.class);
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
