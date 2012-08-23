package net.bodz.swt.viz.style;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import net.bodz.bas.gui.a.Border;
import net.bodz.bas.gui.viz.RenderException;
import net.bodz.swt.viz.SWTRenderContext;
import net.bodz.swt.viz.SWTRenderer;
import net.bodz.swt.viz.a.MaxLength;
import net.bodz.swt.viz.core.SwtEntry;
import net.bodz.swt.viz.core.SwtEntryMetadata;

public class R_Date
        extends SWTRenderer {

    protected String format(Date date) {
        return String.valueOf(date);
    }

    @Override
    public Control render(final SWTRenderContext rc, final SwtEntry<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        SwtEntryMetadata meta = var.getMetadata();
        boolean readOnly = meta.isReadOnly();
        Date date = (Date) var.get();

        if (readOnly) {
            final Label dateLabel = new Label(parent, style);
            dateLabel.setText(format(date));
            if (meta.hasPropertyChangeSupport())
                bindProperty(var, dateLabel, new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        Date date = (Date) var.get();
                        dateLabel.setText(format(date));
                    }
                });
            return dateLabel;
        } else {
            Border _border = meta.getClass().getAnnotation(Border.class);
            if (_border != null)
                if (_border.value() > 0)
                    style |= SWT.BORDER;
            Composite holder = new Composite(parent, style);
            holder.setLayout(new GridLayout(2, false));

            final Text text = new Text(holder, style | SWT.READ_ONLY);
            text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
            MaxLength maxLength = meta.getAnnotation(MaxLength.class);
            if (maxLength != null) {
                int len = maxLength.value();
                text.setTextLimit(len);
            }

            text.setText(format(date));
            if (meta.hasPropertyChangeSupport())
                bindProperty(var, text, new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        Date date = (Date) var.get();
                        text.setText(format(date));
                    }
                });

            final Button picker = new Button(holder, SWT.NONE);
            picker.setText("...");
            picker.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    // DatePicker...
                }
            });
            return holder;
        }
    }

}