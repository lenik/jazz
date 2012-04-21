package net.bodz.swt.reflect.styles.base;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventObject;

import javax.swing.border.Border;

import net.bodz.bas.err.CheckException;
import net.bodz.bas.ui.RenderException;
import net.bodz.swt.adapters.CommitAdapter;
import net.bodz.swt.adapters.CommitException;
import net.bodz.swt.adapters.ControlAdapters;
import net.bodz.swt.reflect.GUIVar;
import net.bodz.swt.reflect.GUIVarMeta;
import net.bodz.swt.reflect.SWTRenderContext;
import net.bodz.swt.reflect.SWTRenderer;
import net.bodz.swt.reflect.a.MaxLength;
import net.bodz.swt.reflect.nls.GUINLS;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class R_Text
        extends SWTRenderer {

    @Override
    public Control render(final SWTRenderContext rc, final GUIVar<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        GUIVarMeta meta = var.getMeta();
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
            Integer border = (Integer) Ns.getValue(meta, Border.class);
            if (border == null || (int) border > 0)
                style |= SWT.BORDER;
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
            final TypeParser parser;
            try {
                parser = TypeParsers.guess(type, true);
            } catch (ParseException e) {
                throw new RenderException(GUINLS.getString("R_Text.cantGuessParserForNum") + type); //$NON-NLS-1$
            }
            ControlAdapters.commit(text, new CommitAdapter(rc.interact(text)) {
                @Override
                public void commit(EventObject event)
                        throws CommitException {
                    try {
                        Object val = parser.parse(text.getText());
                        var.check(val);
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
