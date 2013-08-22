package net.bodz.swt.viz.form.vbo;

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

import net.bodz.bas.gui.css3.BorderBox;
import net.bodz.bas.i18n.unit.std.LengthMeasure;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.potato.ref.IValueChangeListener;
import net.bodz.bas.potato.ref.ValueChangeEvent;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.swt.viz.ISwtControlStyleDeclaration;
import net.bodz.swt.viz.SwtRenderContext;
import net.bodz.swt.viz.AbstractSwtViewBuilder;

public class DateVbo
        extends AbstractSwtViewBuilder<Date> {

    protected String format(Date date) {
        return String.valueOf(date);
    }

    @Override
    public Control buildView(final SwtRenderContext rc, final IRefEntry<Date> entry, ISwtControlStyleDeclaration style,
            Composite parent, int styleInt)
            throws ViewBuilderException, SWTException {

        boolean readOnly = style.getReadOnly() == Boolean.TRUE;
        Date date = (Date) entry.get();

        if (readOnly) {
            final Label dateLabel = new Label(parent, styleInt);
            dateLabel.setText(format(date));
            if (entry.isValueChangeSource())
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

            BorderBox borderBox = style.getBorder();
            if (borderBox != null) {
                LengthMeasure _border = borderBox.getWidth();
                if (_border != null && _border.getValue() > 0.0)
                    styleInt |= SWT.BORDER;
            }

            Composite holder = new Composite(parent, styleInt);
            holder.setLayout(new GridLayout(2, false));

            final Text text = new Text(holder, styleInt | SWT.READ_ONLY);
            text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

            Integer maxLength = style.getMaxLength();
            if (maxLength != null)
                text.setTextLimit(maxLength);

            text.setText(format(date));
            if (entry.isValueChangeSource())
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
