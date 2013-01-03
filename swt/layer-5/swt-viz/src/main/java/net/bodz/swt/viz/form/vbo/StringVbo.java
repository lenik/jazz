package net.bodz.swt.viz.form.vbo;

import java.util.EventObject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import net.bodz.bas.gui.css3.Border;
import net.bodz.bas.gui.validate.MaxLength;
import net.bodz.bas.gui.viz.ViewBuilderException;
import net.bodz.bas.potato.ref.IRefDescriptor;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.potato.ref.IValueChangeListener;
import net.bodz.bas.potato.ref.ValueChangeEvent;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.traits.IValidator;
import net.bodz.swt.c3.control.CommitAdapter;
import net.bodz.swt.c3.control.CommitException;
import net.bodz.swt.c3.control.ControlAdapters;
import net.bodz.swt.viz.SwtRenderContext;
import net.bodz.swt.viz.SwtViewBuilder;
import net.bodz.swt.viz.MappedSwtVizStyleClass;

public class StringVbo
        extends SwtViewBuilder {

    @Override
    public Control buildView(final SwtRenderContext rc, final IRefEntry<?> entry, MappedSwtVizStyleClass stylesheet,
            Composite parent, int style)
            throws ViewBuilderException, SWTException {

        IRefDescriptor descriptor = entry.getDescriptor();
        boolean readOnly = !descriptor.isWritable();

        String val = String.valueOf(entry.get());
        if (readOnly) {
            final Label label = new Label(parent, style);
            label.setText(val);
            if (descriptor.isValueChangeSource())
                bindProperty(entry, label, new IValueChangeListener() {
                    @Override
                    public boolean valueChange(ValueChangeEvent evt) {
                        label.setText(String.valueOf(entry.get()));
                        return true;
                    }
                });
            return label;
        } else {
            Border _border = descriptor.getAnnotation(Border.class);
            if (_border != null) {
                int border = _border.value();
                if (border > 0)
                    style |= SWT.BORDER;
            }
            final Text text = new Text(parent, style);
            // Ns.getValue(meta, EchoChar.class);
            // text.setEchoChar(echo);
            MaxLength maxLength = descriptor.getAnnotation(MaxLength.class);
            if (maxLength != null) {
                int len = maxLength.value();
                text.setTextLimit(len);
            }
            text.setText(val);
            if (descriptor.isValueChangeSource())
                bindProperty(entry, text, new IValueChangeListener() {
                    @Override
                    public boolean valueChange(ValueChangeEvent evt) {
                        text.setText(String.valueOf(entry.get()));
                        return true;
                    }
                });

            Class<?> type = descriptor.getValueType();
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