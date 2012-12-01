package net.bodz.swt.viz.builtin;

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

import net.bodz.bas.gui.viz.RenderException;
import net.bodz.bas.model.meta.MaxLength;
import net.bodz.bas.potato.ref.IRefDescriptor;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.potato.ref.IValueChangeListener;
import net.bodz.bas.potato.ref.ValueChangeEvent;
import net.bodz.swt.viz.SWTRenderContext;
import net.bodz.swt.viz.SWTRenderer;
import net.bodz.swt.viz.SwtVizStyleClass;

public class R_Date
        extends SWTRenderer {

    protected String format(Date date) {
        return String.valueOf(date);
    }

    @Override
    public Control render(final SWTRenderContext rc, final IRefEntry<?> entry, SwtVizStyleClass style,
            Composite parent, int style)
            throws RenderException, SWTException {

        IRefDescriptor descriptor = entry.getDescriptor();

        boolean readOnly = !descriptor.isWritable();
        Date date = (Date) entry.get();

        if (readOnly) {
            final Label dateLabel = new Label(parent, style);
            dateLabel.setText(format(date));
            if (descriptor.isValueChangeSource())
                bindProperty(entry, dateLabel, new IValueChangeListener() {
                    @Override
                    public boolean valueChange(ValueChangeEvent evt) {
                        Date date = (Date) entry.get();
                        dateLabel.setText(format(date));
                        return true;
                    }
                });
            return dateLabel;

        } else {

            Border _border = descriptor.getClass().getAnnotation(Border.class);
            if (_border != null)
                if (_border.value() > 0)
                    style |= SWT.BORDER;
            Composite holder = new Composite(parent, style);
            holder.setLayout(new GridLayout(2, false));

            final Text text = new Text(holder, style | SWT.READ_ONLY);
            text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
            MaxLength maxLength = descriptor.getAnnotation(MaxLength.class);
            if (maxLength != null) {
                int len = maxLength.value();
                text.setTextLimit(len);
            }

            text.setText(format(date));
            if (descriptor.isValueChangeSource())
                bindProperty(entry, text, new IValueChangeListener() {
                    @Override
                    public boolean valueChange(ValueChangeEvent evt) {
                        Date date = (Date) entry.get();
                        text.setText(format(date));
                        return true;
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
