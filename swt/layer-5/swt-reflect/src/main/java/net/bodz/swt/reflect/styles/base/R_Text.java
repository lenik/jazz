package net.bodz.swt.reflect.styles.base;

import static net.bodz.swt.nls.GUINLS.GUINLS;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventObject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import net.bodz.bas.err.CheckException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.ui.RenderException;
import net.bodz.bas.ui.a.Border;
import net.bodz.swt.c.control.CommitAdapter;
import net.bodz.swt.c.control.CommitException;
import net.bodz.swt.c.control.ControlAdapters;
import net.bodz.swt.reflect.SwtEntry;
import net.bodz.swt.reflect.SwtEntryMetadata1;
import net.bodz.swt.reflect.SWTRenderContext;
import net.bodz.swt.reflect.SWTRenderer;
import net.bodz.swt.reflect.a.MaxLength;

public class R_Text
        extends SWTRenderer {

    @Override
    public Control render(final SWTRenderContext rc, final SwtEntry<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        SwtEntryMetadata1 meta = var.getMetadata();
        boolean readOnly = meta.isReadOnly();
        String val = String.valueOf(var.get());
        if (readOnly) {
            final Label label = new Label(parent, style);
            label.setText(val);
            if (meta.hasPropertyChangeSupport())
                bindProperty(var, label, new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        label.setText(String.valueOf(var.get()));
                    }
                });
            return label;
        } else {
            Border _border = meta.getAnnotation(Border.class);
            if (_border != null) {
                int border = _border.value();
                if (border > 0)
                    style |= SWT.BORDER;
            }
            final Text text = new Text(parent, style);
            // Ns.getValue(meta, EchoChar.class);
            // text.setEchoChar(echo);
            MaxLength maxLength = meta.getAnnotation(MaxLength.class);
            if (maxLength != null) {
                int len = maxLength.value();
                text.setTextLimit(len);
            }
            text.setText(val);
            if (meta.hasPropertyChangeSupport())
                bindProperty(var, text, new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        text.setText(String.valueOf(var.get()));
                    }
                });
            Class<?> type = meta.getType();
            final IParser<?> parser;
            try {
                parser = Traits.getTrait(type, IParser.class);
            } catch (ParseException e) {
                throw new RenderException(GUINLS.getString("R_Text.cantGuessParserForNum") + type);
            }
            ControlAdapters.commit(text, new CommitAdapter(rc.interact(text)) {
                @Override
                public void commit(EventObject event)
                        throws CommitException {
                    try {
                        Object val = parser.parse(text.getText());
                        var.validate(val);
                        var.set(val);
                    } catch (ParseException e) {
                        throw new CommitException(e);
                    } catch (CheckException e) {
                        throw new CommitException(e);
                    }
                }
            });
            return text;
        }
    }

}
